package cn.gtcommunity.gregtinker.api.utils;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MirrorUtils
{
    @Nullable
    private static Field fField_modifiers = null;

    public MirrorUtils()
    {/**/}

    public static void writeModifiers(Field field, int modifiers)
    {
        if (fField_modifiers == null)
        {
            try
            {
                fField_modifiers = Field.class.getDeclaredField("modifiers");
                fField_modifiers.setAccessible(true);
            }
            catch (Exception var4)
            {
                throw new IllegalStateException("Failed to reflect field modifiers!", var4);
            }
        }

        try
        {
            fField_modifiers.setInt(field, modifiers);
        }
        catch (Exception var3)
        {
            throw new IllegalStateException("Failed to write field modifieres!", var3);
        }
    }

    public static <T> List<Class<? super T>> getHierarchy(Class<T> clazz)
    {
        List<Class<? super T>> result = new ArrayList();

        for(Class<? super T> current = clazz; current != null; current = current.getSuperclass())
        {
            result.add(current);
        }

        return result;
    }

    public static <T> IMethod<T> reflectMethod(Class<?> clazz, String name, Class<?>... args)
    {
        try
        {
            Method method = clazz.getDeclaredMethod(name, args);
            method.setAccessible(true);
            return new IMethod.Impl(method);
        }
        catch (NoSuchMethodException var4)
        {
            throw new MirrorException(String.format("Could not reflect method: %s/%s %s", clazz.getName(), name, Arrays.toString(args)), var4);
        }
    }

    public static <T> IField<T> reflectField(Class<?> clazz, String name)
    {
        try
        {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return new IField.Impl(field);
        }
        catch (NoSuchFieldException var3)
        {
            throw new MirrorException(String.format("Could not reflect field: %s/%s", clazz.getName(), name), var3);
        }
    }

    public static class MirrorException extends RuntimeException
    {
        MirrorException(String reason, Exception cause)
        {
            super(reason, cause);
        }
    }

    public interface IField<T>
    {
        default void set(@Nullable Object target, T value)
        {
            try
            {
                Field field = this.unwrap();
                int modifiers = field.getModifiers();
                if ((modifiers & 16) != 0) {
                    MirrorUtils.writeModifiers(field, modifiers & -17);
                }

                field.set(target, value);
            }
            catch (IllegalAccessException var5)
            {
                throw new MirrorException("Could not mutate field: " + this.unwrap(), var5);
            }
        }

        default T get(@Nullable Object target)
        {
            try
            {
                return (T) this.unwrap().get(target);
            }
            catch (IllegalAccessException var3)
            {
                throw new MirrorException("Could not read field: " + this.unwrap(), var3);
            }
        }

        Field unwrap();

        public static class Impl<T> implements IField<T>
        {
            private final Field field;

            Impl(Field field)
            {
                this.field = field;
            }

            public Field unwrap()
            {
                return this.field;
            }
        }
    }

    public interface IMethod<T>
    {
        default T invoke(@Nullable Object target, Object... args)
        {
            try
            {
                return (T) this.unwrap().invoke(target, args);
            }
            catch (InvocationTargetException | IllegalAccessException var4)
            {
                throw new MirrorException("Could not invoke method: " + this.unwrap(), var4);
            }
        }

        Method unwrap();

        public static class Impl<T> implements IMethod<T>
        {
            private final Method method;

            Impl(Method method)
            {
                this.method = method;
            }

            public Method unwrap()
            {
                return this.method;
            }
        }
    }
}

package cn.gtcommunity.gregtinker.api.utils.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public class Accrue<T> implements Consumer<T>
{
    private final Collection<T> backing;

    public Accrue(Collection<T> backing) {
        this.backing = backing;
    }

    public void accept(T t) {
        this.backing.add(t);
    }

    @SafeVarargs
    public final void acceptAll(T... values) {
        this.backing.addAll(Arrays.asList(values));
    }

    public void acceptAll(Collection<T> values) {
        this.backing.addAll(values);
    }
}

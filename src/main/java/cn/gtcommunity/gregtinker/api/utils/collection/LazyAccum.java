package cn.gtcommunity.gregtinker.api.utils.collection;

import cn.gtcommunity.gregtinker.api.utils.collection.Accrue;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface LazyAccum<T>
{
    void accumulate(Accrue<T> collector);

    default void collectTo(List<T> list) {
        accumulate(new Accrue<>(list));
    }

    default List<T> collect()
    {
        List<T> list = new ArrayList<>();
        collectTo(list);
        return list;
    }
}

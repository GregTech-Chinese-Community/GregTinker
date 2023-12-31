package cn.gtcommunity.gregtinker.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;

public class OreDictUtils
{
    @Nullable
    public static ItemStack getStack(String entry, int qty)
    {
        List<ItemStack> items = OreDictionary.getOres(entry, false);
        if (items.isEmpty())
        {
            return null;
        }
        else
        {
            ItemStack stack = items.get(0).copy();
            stack.setCount(qty);
            return stack;
        }
    }

    public static boolean exists(String entry) {
        return !OreDictionary.getOres(entry, false).isEmpty();
    }

}

package cn.gtcommunity.gregtinker.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.Optional;

public class OptUtils
{
    public OptUtils()
    {/**/}

    public static Optional<NBTTagCompound> stackTag(ItemStack stack) {
        return stack.hasTagCompound() ? Optional.of(stack.getTagCompound()) : Optional.empty();
    }

    public static <T> Optional<T> capability(ICapabilityProvider provider, Capability<T> cap) {
        return provider.hasCapability(cap, null) ? Optional.ofNullable(provider.getCapability(cap, null)) : Optional.empty();
    }
}

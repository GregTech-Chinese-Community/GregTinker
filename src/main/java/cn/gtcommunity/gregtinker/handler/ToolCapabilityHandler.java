package cn.gtcommunity.gregtinker.handler;

import cn.gtcommunity.gregtinker.GregTinker;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tinkering.ITinkerable;
import slimeknights.tconstruct.library.utils.TagUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class ToolCapabilityHandler
{
    private final ResourceLocation GT_CAP = new ResourceLocation(GregTinker.MODID, "multi_capability");
    private final Map<String, Function<ItemStack, ICapabilityProvider>> modifierCaps = new HashMap<>();

    public void addModifierCap(Modifier mod, Function<ItemStack, ICapabilityProvider> capFactory)
    {
        addModifierCap(mod.getIdentifier(), capFactory);
    }

    public void addModifierCap(String modifierId, Function<ItemStack, ICapabilityProvider> capFactory)
    {
        modifierCaps.put(modifierId, capFactory);
    }

    @SubscribeEvent
    public void onItemCapAttach(AttachCapabilitiesEvent<ItemStack> event)
    {
        ItemStack stack = event.getObject();
        if (stack.getItem() instanceof ITinkerable)
        {
            event.addCapability(GT_CAP, new GTCapProvider(stack, modifierCaps));
        }
    }

    private static class GTCapProvider implements ICapabilityProvider
    {

        private final ItemStack stack;
        private final Map<String, Function<ItemStack, ICapabilityProvider>> capFactories;
        private final Map<String, Optional<ICapabilityProvider>> capCache = new HashMap<>();

        public GTCapProvider(ItemStack stack, Map<String, Function<ItemStack, ICapabilityProvider>> capFactories)
        {
            this.stack = stack;
            this.capFactories = capFactories;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
        {
            return getCapability(capability, facing) != null;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
        {
            for (NBTBase tag : TagUtil.getModifiersTagList(stack))
            {
                if (tag instanceof NBTTagCompound)
                {
                    ModifierNBT modTag = ModifierNBT.readTag((NBTTagCompound)tag);
                    ICapabilityProvider capProvider = null;
                    Optional<ICapabilityProvider> capProviderOpt = capCache.get(modTag.identifier);
                    if (capProviderOpt != null)
                    {
                        if (capProviderOpt.isPresent())
                        {
                            capProvider = capProviderOpt.get();
                        }
                    }
                    else
                    {
                        Function<ItemStack, ICapabilityProvider> capFactory = capFactories.get(modTag.identifier);
                        if (capFactory != null)
                        {
                            capProvider = capFactory.apply(stack);
                            capCache.put(modTag.identifier, Optional.of(capProvider));
                        }
                        else
                        {
                            capCache.put(modTag.identifier, Optional.empty());
                        }
                    }
                    if (capProvider != null && capProvider.hasCapability(capability, facing))
                    {
                        return Objects.requireNonNull(capProvider.getCapability(capability, facing));
                    }
                }
            }
            return null;
        }
    }
}

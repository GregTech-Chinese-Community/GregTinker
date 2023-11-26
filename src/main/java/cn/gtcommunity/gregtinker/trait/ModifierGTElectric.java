package cn.gtcommunity.gregtinker.trait;

import cn.gtcommunity.gregtinker.GregTinker;
import cn.gtcommunity.gregtinker.api.capability.CapabilityBroker;
import cn.gtcommunity.gregtinker.api.utils.GTiLog;
import cn.gtcommunity.gregtinker.api.utils.ItemUtils;
import cn.gtcommunity.gregtinker.api.utils.OptUtils;
import cn.gtcommunity.gregtinker.trait.base.GTEnergeticModifier;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.modifiers.ModMendingMoss;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ModifierGTElectric extends ModifierTrait implements GTEnergeticModifier
{
    public static final int COLOUR = 0x0a00c6;

    public ModifierGTElectric() {
        super("gregtinker.electric", COLOUR);
        aspects.remove(aspects.lastIndexOf(ModifierAspect.freeModifier));
        addAspects(new ModifierAspect.FreeFirstModifierAspect(this, 1));
        GregTinker.proxy.getToolCapHandler().addModifierCap(this, s -> new CapabilityBroker()
                .with(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, new ElectricToolBuffer(s)));
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @Override
//    public boolean canApplyCustom(ItemStack stack) {
//        return !PowerWrapper.isPowered(stack) || isToolWithTrait(stack);
//    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onToolTips(ItemTooltipEvent event)
    {
        if (isToolWithTrait(event.getItemStack()))
        {
            IElectricItem electricItem = event.getItemStack().getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (electricItem != null)
            {
                event.getToolTip().add(I18n.format("metaitem.generic.electric_item.tooltip",
                        electricItem.getCharge(),
                        electricItem.getMaxCharge(),
                        GTValues.VNF[electricItem.getTier()]));
            }
        }
    }

    @Override
    public boolean canApplyTogether(IToolMod otherModifier) {
        return !(otherModifier instanceof ModMendingMoss);
    }
    
    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        return doDamageReduction(tool, entity, newDamage, 300);
    }

    public static int doDamageReduction(ItemStack tool, EntityLivingBase user, int damage, double unitCost) {
        if (damage > 0) {
            return OptUtils.capability(tool, GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM)
                    .map(e -> e.discharge((long)Math.floor(damage * unitCost), 999, true,false,false) != 0 ? 0 : damage)
                    .orElse(damage);
        }
        return damage;
    }

    @Override
    public int getPriority() {
        return 25;
    }

    public static class ElectricToolBuffer implements IElectricItem {

        private final ItemStack stack;

        protected final List<BiConsumer<ItemStack, Long>> listeners = new ArrayList<>();

        public ElectricToolBuffer(ItemStack stack) {
            this.stack = stack;
        }

        public void setTier(int tier)
        {
            ItemUtils.getOrCreateTag(stack).setInteger("Tier", tier);
        }

        public void setCharge(long change)
        {
            //noinspection ConstantConditions
            ItemUtils.getOrCreateTag(stack).setLong("Charge", change);
            listeners.forEach(l -> l.accept(stack, change));
        }

        public void setMaxChargeOverride(long maxCharge)
        {
            //noinspection ConstantConditions
            ItemUtils.getOrCreateTag(stack).setLong("MaxCharge", maxCharge);
            listeners.forEach(l -> l.accept(stack, maxCharge));
        }

        public void setProvideChargeExternally(long canProvideCharge)
        {
            ItemUtils.getOrCreateTag(stack).setLong("ProvideCharge", canProvideCharge);
        }

        @Override
        public boolean canProvideChargeExternally() {
            return OptUtils.stackTag(stack).map(t -> t.getBoolean("ProvideCharge")).orElse(false);
        }

        @Override
        public boolean chargeable() {
            return true;
        }

        @Override
        public void addChargeListener(BiConsumer<ItemStack, Long> chargeListener)
        {
            listeners.add(chargeListener);
        }

        @Override
        public long charge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean simulate) {
            if (stack.getCount() != 1) {
                return 0L;
            }
            if ((chargeable() || amount == Long.MAX_VALUE) && (chargerTier >= getTier()) && amount > 0L) {
                long canReceive = getMaxCharge() - getCharge();
                if (!ignoreTransferLimit) {
                    amount = Math.min(amount, getTransferLimit());
                }
                long charged = Math.min(amount, canReceive);
                if (!simulate) {
                    setCharge(getCharge() + charged);
                }
                return charged;
            }
            return 0;
        }

        @Override
        public long discharge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean externally, boolean simulate) {
            if (stack.getCount() != 1) {
                return 0L;
            }
            if ((canProvideChargeExternally() || !externally || amount == Long.MAX_VALUE) && (chargerTier >= getTier()) && amount > 0L) {
                if (!ignoreTransferLimit) {
                    amount = Math.min(amount, getTransferLimit());
                }
                long charge = getCharge();
                long discharged = Math.min(amount, charge);
                if (!simulate) {
                    setCharge(charge - discharged);
                }
                return discharged;
            }
            return 0;
        }

        @Override
        public long getTransferLimit()
        {
            return GTValues.V[getTier()];
        }

        @Override
        public long getMaxCharge()
        {
            return /*OptUtils.stackTag(stack).map(t -> t.getLong("MaxCharge")).orElse(0L)*/1000L;
        }

        @Override
        public long getCharge()
        {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound == null)
                return 0;
            if (tagCompound.getBoolean("Infinite"))
                return getMaxCharge();
            return Math.min(tagCompound.getLong("Charge"), getMaxCharge());
        }

        public void setInfiniteCharge(boolean infiniteCharge)
        {
            //noinspection ConstantConditions
            ItemUtils.getOrCreateTag(stack).setBoolean("Infinite", infiniteCharge);
            listeners.forEach(l -> l.accept(stack, getMaxCharge()));
        }

        @Override
        public int getTier() {
            return /*OptUtils.stackTag(stack).map(t -> t.getInteger("Tier")).orElse(0);*/2;
        }
    }
}

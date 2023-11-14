package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitIrradiation extends AbstractTrait
{

    public TraitIrradiation()
    {
        super("irradiation", 223255000);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical)
    {
        int level = -1;
        PotionEffect potionEffect = target.getActivePotionEffect(MobEffects.WITHER);
        if (potionEffect != null)
        {
            level = potionEffect.getAmplifier();
        }

        level = Math.min(4, level + 1);
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, level));
        super.onHit(tool, player, target, damage, isCritical);
    }
}

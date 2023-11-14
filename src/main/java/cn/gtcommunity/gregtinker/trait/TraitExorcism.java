package cn.gtcommunity.gregtinker.trait;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;

public class TraitExorcism extends AbstractTrait
{
    private static float bonusDamage = 10.0F;

    public TraitExorcism() {
        super("exorcism", 16777215);
    }

    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
    {
        if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
        {
            newDamage += bonusDamage;
        }

        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
    {
        if (wasHit && !target.isDead && target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
        {
            target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 0, false, true));
        }

    }

    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag)
    {
        String loc = Util.translate("modifier.%s.extra", this.getIdentifier());
        return ImmutableList.of(Util.translateFormatted(loc, Util.df.format(bonusDamage)));
    }
}

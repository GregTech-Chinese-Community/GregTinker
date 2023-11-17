package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitEvenlyMatched  extends AbstractTrait
{

    public TraitEvenlyMatched()
    {
        super("evenly_matched", 0xE6E6FA);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
    {
        float difference = player.getHealth() - target.getHealth();

        if (difference <= 0)
        {
            newDamage += ((random.nextInt(3) + 2) / 10F) * (Math.abs(difference) / target.getHealth()) * Math.min(target.getMaxHealth(), 500);
        }
        else
        {
            newDamage = Math.max(2, newDamage - ((random.nextInt(3) + 2) / 10F) * (difference / player.getHealth()) * player.getMaxHealth());
        }

        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }
}

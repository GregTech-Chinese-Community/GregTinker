package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBloodSucking  extends AbstractTrait
{

    public TraitBloodSucking() {
        super("blood_sucking", 0x8B0000);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
    {
        super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);

        if (player.isDead) return;

        player.heal(damageDealt * 0.4F);
    }
}

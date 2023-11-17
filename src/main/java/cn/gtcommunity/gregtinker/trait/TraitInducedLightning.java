package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitInducedLightning extends AbstractTrait
{
    private final float chance = 0.025F;

    public TraitInducedLightning()
    {
        super("induced_lightning", 0xE6E6FA);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);

        if (!wasHit) return;

        if (!target.getEntityWorld().canSeeSky(target.getPosition())) return;

        if (chance > random.nextFloat())
        {
            target.getEntityWorld().addWeatherEffect(new EntityLightningBolt(target.getEntityWorld(), target.posX, target.posY, target.posZ, false));
        }
    }
}

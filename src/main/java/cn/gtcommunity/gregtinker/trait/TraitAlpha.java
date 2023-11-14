package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;

public class TraitAlpha extends AbstractTrait
{
    public TraitAlpha()
    {
        super("alpha", 201204230);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        if (!(isSelected && entity.ticksExisted % 20 == 0 && entity instanceof EntityPlayer && !entity.isDead)) return;

        List<EntityLivingBase> entityLivings = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ).grow(10, 10, 10));

        causeDamage((EntityLivingBase) entity);

        for (EntityLivingBase entityLiving : entityLivings)
        {
            causeDamage(entityLiving);
        }
    }

    protected void causeDamage(EntityLivingBase target)
    {
        attackEntitySecondary(DamageSource.MAGIC, 1, target, true, false);
    }
}

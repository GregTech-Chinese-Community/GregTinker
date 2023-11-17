package cn.gtcommunity.gregtinker.trait;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitCorrosionResistance extends AbstractTrait
{
    private boolean update = false;
    public TraitCorrosionResistance()
    {
        super("corrosion_resistance", 0xE5E4E2);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
    {
        super.afterBlockBreak(tool, world, state, pos, player, wasEffective);
        if (ToolHelper.getHarvestLevelStat(tool) > state.getBlock().getHarvestLevel(state)) update = true;
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);
        if (wasHit && target.getMaxHealth() <= damageDealt) update = true;
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity)
    {
        if (update)
        {
            newDamage = 0;
            update = false;
        }
        return super.onToolDamage(tool, damage, newDamage, entity);
    }
}

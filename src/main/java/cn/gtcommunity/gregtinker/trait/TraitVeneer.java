package cn.gtcommunity.gregtinker.trait;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TraitVeneer extends AbstractTraitLeveled
{
    public TraitVeneer(int levels)
    {
        super("veneer", String.valueOf(levels), 0xFFC000, 2, 1);
    }

    @Override
    public void applyModifierEffect(NBTTagCompound rootCompound)
    {
        NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
        int modifiers = toolTag.getInteger("FreeModifiers") + 2 * this.levels;
        toolTag.setInteger("FreeModifiers", Math.max(0, modifiers));
        TagUtil.setToolTag(rootCompound, toolTag);
    }
}

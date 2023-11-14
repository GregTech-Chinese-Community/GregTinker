package cn.gtcommunity.gregtinker.trait;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.modifiers.ModReinforced;

public class TraitUnbreakable extends AbstractTrait
{
    public TraitUnbreakable() {
        super("unbreakable", TextFormatting.DARK_GRAY);
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag)
    {
        super.applyEffect(rootCompound, modifierTag);
        rootCompound.setBoolean(ModReinforced.TAG_UNBREAKABLE, true);
    }
}

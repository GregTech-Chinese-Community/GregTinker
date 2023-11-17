package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitSupertight extends AbstractTrait
{
    public TraitSupertight()
    {
        super("supertight", 0x702963);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity)
    {
        float durability = (float) ToolHelper.getCurrentDurability(tool);
        float maxDurability = (float)ToolHelper.getMaxDurability(tool);

        float chance = durability / maxDurability < 0.33F ? 0.75F : 0.25F;

        if (chance > random.nextFloat()) newDamage =  0;

        return super.onToolDamage(tool, damage, newDamage, entity);
    }
}

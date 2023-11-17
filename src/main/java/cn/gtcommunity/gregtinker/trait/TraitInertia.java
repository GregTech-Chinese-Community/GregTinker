package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitInertia extends AbstractTrait
{
    private final float chance = 0.45F;
    public TraitInertia()
    {
        super("inertia", 0x7393B3);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
    {
        if (chance > random.nextFloat()) newDamage *= 2;

        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

    @SubscribeEvent
    public void onToolBuilding(TinkerEvent.OnItemBuilding event)
    {
        if (TinkerUtil.hasTrait(event.tag, this.getIdentifier()))
        {
            ToolNBT data = TagUtil.getToolStats(event.tag);
            data.attackSpeedMultiplier *= 0.8F;
            TagUtil.setToolTag(event.tag, data.get());
        }

    }
}

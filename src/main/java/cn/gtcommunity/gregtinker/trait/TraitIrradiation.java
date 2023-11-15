package cn.gtcommunity.gregtinker.trait;

import cn.gtcommunity.gregtinker.api.utils.GTiLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitIrradiation extends AbstractTrait
{

    public boolean update = false;

    public TraitIrradiation()
    {
        super("irradiation", 223255000);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);

        if (!(entity instanceof EntityPlayer player)) return;

        if (update && isSelected)
        {
            ToolHelper.healTool(tool, 5, player);
            update = false;
        }
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical)
    {
        super.onHit(tool, player, target, damage, isCritical);
        int level = -1;
        PotionEffect potionEffect = target.getActivePotionEffect(MobEffects.WITHER);
        if (potionEffect != null)
        {
            level = potionEffect.getAmplifier();
        }

        level = Math.min(4, level + 1);
        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 240, level));
        NBTTagCompound tagCompound = target.getEntityData();
        if (tagCompound.hasKey("markWither")) return;
        tagCompound.setBoolean("markWither", true);
    }

    @SubscribeEvent
    public void onEntityDead(LivingDeathEvent event)
    {
        if (!event.getSource().damageType.equals("wither")) return;

        if (!event.getEntityLiving().getEntityData().getBoolean("markWither")) return;

        update = true;
    }
}

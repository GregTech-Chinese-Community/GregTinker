package cn.gtcommunity.gregtinker.trait;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;

public class TraitExorcism extends AbstractTrait
{
    private static float bonusDamage = 10.0F;

    public TraitExorcism() {
        super("exorcism", 0xFFF8DC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
    }

    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
    {
        if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
        {
            newDamage += bonusDamage;
        }

        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
    {
        if (wasHit && !target.isDead && target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
        {
            target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 0, false, true));
        }

    }

    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag)
    {
        String loc = Util.translate("modifier.%s.extra", this.getIdentifier());
        return ImmutableList.of(Util.translateFormatted(loc, Util.df.format(bonusDamage)));
    }

    @SubscribeEvent
    public void onEntitySetTarget(LivingSetAttackTargetEvent event)
    {
        if (!(event.getTarget() instanceof EntityPlayer player)) return;

        if (!event.getEntityLiving().isEntityUndead()) return;

        ItemStack[] inventory = new ItemStack[] {player.getHeldItemMainhand(), player.getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                EntityLiving entity = (EntityLiving) event.getEntityLiving();
                entity.setAttackTarget(null);
                return;
            }
        }
    }
}

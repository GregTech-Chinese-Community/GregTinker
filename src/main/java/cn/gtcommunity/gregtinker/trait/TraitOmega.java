package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitOmega extends AbstractTrait
{
    public TraitOmega()
    {
        super("omega", 165420420);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        attackEntitySecondary(DamageSource.MAGIC, damage, player, true, false);
        super.onHit(tool, player, target, damage, isCritical);
    }

    @SubscribeEvent
    public void onPlayerDamaged(LivingHurtEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer player)) return;

        if (!event.getSource().damageType.equals("mob")) return;

        ItemStack[] inventory = new ItemStack[] {player.getHeldItemMainhand(), player.getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                event.setAmount(0);
                return;
            }
        }
    }
}

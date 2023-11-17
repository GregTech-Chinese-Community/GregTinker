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
        super("omega", 0xFFFFF0);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerDamaged(LivingHurtEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer player)) return;

        if (!(event.getSource().getTrueSource() instanceof EntityLivingBase)) return;

        if (isToolHeld(player))
        {
            event.setAmount(0);
        }
    }

    @SubscribeEvent
    public void onEntityDamaged(LivingHurtEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer) return;

        if (!(event.getSource().getTrueSource() instanceof EntityPlayer player)) return;

        if (isToolHeld(player))
        {
            attackEntitySecondary(DamageSource.MAGIC, event.getAmount(), player, true, false);
        }
    }

    public boolean isToolHeld(EntityPlayer player)
    {
        ItemStack[] inventory = new ItemStack[] {player.getHeldItemMainhand(), player.getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                return true;
            }
        }
        return false;
    }
}

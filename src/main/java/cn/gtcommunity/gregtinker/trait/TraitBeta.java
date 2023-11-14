package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBeta extends AbstractTrait
{
    public TraitBeta()
    {
        super("beta", 128128000);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
    {
        newDamage *= 2;
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }

    @SubscribeEvent
    public void onPlayerDamaged(LivingHurtEvent event)
    {
        if (!(event.getEntityLiving() instanceof EntityPlayer player)) return;

        ItemStack[] inventory = new ItemStack[] {player.getHeldItemMainhand(), player.getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                event.setAmount(2 * event.getAmount());
                return;
            }
        }
    }
}

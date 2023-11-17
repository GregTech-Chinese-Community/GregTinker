package cn.gtcommunity.gregtinker.trait;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitMaliciousPerception  extends AbstractTrait
{

    public TraitMaliciousPerception()
    {
        super("malicious_perception", 0xC1E1C1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntitySetTarget(LivingSetAttackTargetEvent event)
    {
        if (!(event.getTarget() instanceof EntityPlayer player)) return;

        ItemStack[] inventory = new ItemStack[] {player.getHeldItemMainhand(), player.getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.GLOWING, 400, 0, false, false));
                return;
            }
        }
    }
}

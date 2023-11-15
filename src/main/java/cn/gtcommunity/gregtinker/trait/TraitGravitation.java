package cn.gtcommunity.gregtinker.trait;

import gregtech.api.GTValues;
import gregtech.integration.baubles.BaublesModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nonnull;
import java.util.List;

public class TraitGravitation extends AbstractTrait
{
    public TraitGravitation()
    {
        super("gravitation", TextFormatting.DARK_GRAY);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        if (isSelected && !entity.isSneaking() && entity.ticksExisted % 10 == 0 && entity instanceof EntityPlayer player)
        {

            List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ).grow(16, 16, 16));

            boolean didMoveEntity = false;
            for (EntityItem itemEntity : items)
            {
                if (itemEntity.isDead)
                {
                    continue;
                }

                NBTTagCompound itemTag = itemEntity.getEntityData();
                if (itemTag.hasKey("PreventRemoteMovement"))
                {
                    continue;
                }

                if (itemEntity.getThrower().equals(entity.getName()) && itemEntity.pickupDelay > 0)
                {
                    continue;
                }

                EntityPlayer closest = world.getClosestPlayerToEntity(itemEntity, 4);
                if (closest != null && closest != entity)
                {
                    continue;
                }

                if (!world.isRemote)
                {
                    if (itemEntity.pickupDelay > 0)
                    {
                        itemEntity.pickupDelay = 0;
                    }
                    itemEntity.motionX = itemEntity.motionY = itemEntity.motionZ = 0;
                    itemEntity.setPosition(entity.posX - 0.2 + (world.rand.nextDouble() * 0.4), entity.posY - 0.6, entity.posZ - 0.2 + (world.rand.nextDouble() * 0.4));
                    didMoveEntity = true;
                }
            }

            if (didMoveEntity)
            {
                world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 2F));
            }

            List<EntityXPOrb> xp = world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX, entity.posY, entity.posZ).grow(4, 4, 4));

            for (EntityXPOrb orb : xp)
            {
                if (!world.isRemote && !orb.isDead)
                {
                    if (orb.delayBeforeCanPickup == 0)
                    {
                        if (MinecraftForge.EVENT_BUS.post(new PlayerPickupXpEvent(player, orb)))
                        {
                            continue;
                        }
                        world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.8F));
                        player.onItemPickup(orb, 1);
                        player.addExperience(orb.xpValue);
                        orb.setDead();
                        didMoveEntity = true;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onItemToss(@Nonnull ItemTossEvent event)
    {
        if (event.getPlayer() == null) return;

        ItemStack[] inventory = new ItemStack[] {event.getPlayer().getHeldItemMainhand(), event.getPlayer().getHeldItemOffhand()};

        for (ItemStack item : inventory)
        {
            if (isToolWithTrait(item))
            {
                event.getEntityItem().setPickupDelay(100);
                return;
            }
        }
    }
}

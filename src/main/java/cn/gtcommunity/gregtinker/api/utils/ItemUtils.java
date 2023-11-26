package cn.gtcommunity.gregtinker.api.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ItemUtils
{
    public static final short WILDCARD_META = Short.MAX_VALUE;

    public ItemUtils() {
    }

    public static boolean matchesWithWildcard(ItemStack a, ItemStack b) {
        boolean var10000;
        label39: {
            if (a.getItem().equals(b.getItem()) && (a.getMetadata() == 32767 || b.getMetadata() == 32767 || a.getMetadata() == b.getMetadata())) {
                if (a.hasTagCompound()) {
                    if (b.hasTagCompound() && b.getTagCompound().equals(a.getTagCompound())) {
                        break label39;
                    }
                } else if (!b.hasTagCompound()) {
                    break label39;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public static String getColouredName(ItemStack stack) {
        return stack.getItem().getForgeRarity(stack).getColor() + stack.getDisplayName();
    }

    public static NBTTagCompound getOrCreateTag(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            stack.setTagCompound(tag = new NBTTagCompound());
        }

        return tag;
    }

    @SideOnly(Side.CLIENT)
    public static void getStackTooltip(ItemStack stack, List<String> tooltip, @Nullable EntityPlayer player, ITooltipFlag tooltipFlags) {
        List<String> itemTooltip = stack.getTooltip(player, tooltipFlags);
        if (itemTooltip.isEmpty()) {
            tooltip.add(stack.getItem().getForgeRarity(stack).getColor() + stack.getDisplayName());
        } else {
            Iterator<String> iter = itemTooltip.iterator();
            tooltip.add(stack.getItem().getForgeRarity(stack).getColor() + iter.next());

            while(iter.hasNext()) {
                tooltip.add(TextFormatting.GRAY + iter.next());
            }

        }
    }

    @SideOnly(Side.CLIENT)
    public static void getStackTooltip(ItemStack stack, List<String> tooltip, ITooltipFlag tooltipFlags) {
        getStackTooltip(stack, tooltip, Minecraft.getMinecraft().player, tooltipFlags);
    }

    public static ItemStack getItemForBlock(IBlockState state) {
        Block block = state.getBlock();
        Item item = Item.getItemFromBlock(block);
        return new ItemStack(item, 1, item.getMetadata(block.getMetaFromState(state)));
    }
}

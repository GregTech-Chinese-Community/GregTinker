package cn.gtcommunity.gregtinker.mixin.minecraft;

import cn.gtcommunity.gregtinker.client.utils.GTChargeBarRenderer;
import cn.gtcommunity.gregtinker.trait.GTinkerTraits;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

import javax.annotation.Nullable;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem
{
    @Inject(
            method = "Lnet/minecraft/client/renderer/RenderItem;renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;showDurabilityBar(Lnet/minecraft/item/ItemStack;)Z",
                    remap = false
            )
    )
    private void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, @Nullable String text, CallbackInfo ci)
    {
        if (TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), GTinkerTraits.MODIFIER_GT_ELECTRIC.getIdentifier())) {
            GTChargeBarRenderer.renderBarsItem(stack, xPosition, yPosition);
        }
    }
}

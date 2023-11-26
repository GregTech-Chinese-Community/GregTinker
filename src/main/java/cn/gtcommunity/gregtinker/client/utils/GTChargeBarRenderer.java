package cn.gtcommunity.gregtinker.client.utils;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GTChargeBarRenderer
{
    private static final double BAR_W = 13d;

    private static final Color colorShadow = new Color(0, 0, 0, 255);
    private static final Color colorBG = new Color(0x0E, 0x01, 0x16, 255);

    private static final Color colorBarLeftEnergy = new Color(0, 101, 178, 255);
    private static final Color colorBarRightEnergy = new Color(217, 238, 255, 255);
    private static final Color colorBarLeftDepleted = new Color(122, 0, 0, 255);
    private static final Color colorBarRightDepleted = new Color(255, 27, 27, 255);

    public static void render(double level, int xPosition, int yPosition, int offset, boolean shadow, Color left, Color right, boolean doDepletedColor) {
        double width = level * BAR_W;
        if (doDepletedColor && level <= 0.25) {
            left = colorBarLeftDepleted;
            right = colorBarRightDepleted;
        }

        GlStateManager.enableLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        drawShadow(worldrenderer, xPosition + 2, yPosition + 13 - offset, 13, shadow ? 2 : 1);
        drawGrad(worldrenderer, xPosition + 2, yPosition + 13 - offset, (BAR_W + width) / 2, left, right);
        drawBG(worldrenderer, xPosition + 2 + (int) BAR_W, yPosition + 13 - offset, BAR_W - width);

        tessellator.draw();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }

    private static void drawGrad(BufferBuilder renderer, int x, int y, double width, Color left, Color right) {
        renderer.pos(x, y, 0.0D).color(left.getRed(), left.getGreen(), left.getBlue(), left.getAlpha()).endVertex();
        renderer.pos(x, y + (double) 1, 0.0D).color(left.getRed(), left.getGreen(), left.getBlue(), left.getAlpha()).endVertex();
        renderer.pos(x + width, y + (double) 1, 0.0D).color(right.getRed(), right.getGreen(), right.getBlue(), right.getAlpha()).endVertex();
        renderer.pos(x + width, y, 0.0D).color(right.getRed(), right.getGreen(), right.getBlue(), right.getAlpha()).endVertex();
    }

    private static void drawShadow(BufferBuilder renderer, int x, int y, double width, double height) {
        renderer.pos(x, y, 0.0D).color(colorShadow.getRed(), colorShadow.getGreen(), colorShadow.getBlue(), colorShadow.getAlpha()).endVertex();
        renderer.pos(x, y + height, 0.0D).color(colorShadow.getRed(), colorShadow.getGreen(), colorShadow.getBlue(), colorShadow.getAlpha()).endVertex();
        renderer.pos(x + width, y + height, 0.0D).color(colorShadow.getRed(), colorShadow.getGreen(), colorShadow.getBlue(), colorShadow.getAlpha()).endVertex();
        renderer.pos(x + width, y, 0.0D).color(colorShadow.getRed(), colorShadow.getGreen(), colorShadow.getBlue(), colorShadow.getAlpha()).endVertex();
    }

    private static void drawBG(BufferBuilder renderer, int x, int y, double width) {
        renderer.pos(x - width, y, 0.0D).color(colorBG.getRed(), colorBG.getGreen(), colorBG.getBlue(), colorBG.getAlpha()).endVertex();
        renderer.pos(x - width, y + (double) 1, 0.0D).color(colorBG.getRed(), colorBG.getGreen(), colorBG.getBlue(), colorBG.getAlpha()).endVertex();
        renderer.pos(x, y + (double) 1, 0.0D).color(colorBG.getRed(), colorBG.getGreen(), colorBG.getBlue(), colorBG.getAlpha()).endVertex();
        renderer.pos(x, y, 0.0D).color(colorBG.getRed(), colorBG.getGreen(), colorBG.getBlue(), colorBG.getAlpha()).endVertex();
    }

    public static void renderBarsItem(ItemStack stack, int xPosition, int yPosition) {
        IElectricItem electricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null) {
            renderElectricBar(electricItem.getCharge(), electricItem.getMaxCharge(), xPosition, yPosition, true);
        }
    }

    private static void renderElectricBar(long charge, long maxCharge, int xPosition, int yPosition, boolean renderedDurability) {
        if (charge > 0 && maxCharge > 0) {
            double level = (double) charge / (double) maxCharge;
            render(level, xPosition, yPosition, renderedDurability ? 2 : 0, true, colorBarLeftEnergy, colorBarRightEnergy, true);
        }
    }

    private GTChargeBarRenderer() {
    }
}

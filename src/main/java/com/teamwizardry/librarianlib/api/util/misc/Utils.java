package com.teamwizardry.librarianlib.api.util.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.text.WordUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by Saad on 4/9/2016.
 */
public class Utils {

    public static void drawNormalItemStack(final ItemStack itemStack, final int x, final int y) {
        if (itemStack != null) {
            GlStateManager.enableRescaleNormal();
            RenderHelper.enableGUIStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(itemStack, x, y);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
        }
    }

    public static ArrayList<String> padString(String string, int stringSize) {
        ArrayList<String> lines = new ArrayList<>();
        if (string != null)
            for (String line : WordUtils.wrap(string, stringSize).split("\n")) lines.add(line.trim());
        return lines;
    }

    public static void drawLine2D(int x1, int y1, int x2, int y2, int width, Color color) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.color(color.r, color.g, color.b, 1F);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(width);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(x1, y1, 0);
        GL11.glVertex3f(x2, y2, 0);
        GL11.glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawLine3D(BlockPos pos1, BlockPos pos2, Color color) {
        GlStateManager.pushMatrix();

        GL11.glLineWidth(1);

        GlStateManager.disableTexture2D();
        GlStateManager.color(color.r, color.g, color.b, 0.7f);
        GlStateManager.translate(0.5, 0.7, 0.5);

        VertexBuffer vb = Tessellator.getInstance().getBuffer();
        vb.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        vb.pos(pos2.getX() - pos1.getX(), pos2.getY() - pos1.getY(), pos2.getZ() - pos1.getZ()).endVertex();
        vb.pos(0, 0, 0).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.enableTexture2D();

        GlStateManager.popMatrix();
    }

    public static boolean isInside(int currentX, int currentY, int x, int y, int size) {
        return currentX >= x && currentX < x + size && currentY >= y && currentY < y + size;
    }

    public static boolean isInside(int currentX, int currentY, int x, int y, int width, int height) {
        return currentX >= x && currentX < x + width && currentY >= y && currentY < y + height;
    }
}
package Andrew6rant.tiered;

import com.anthonyhilyard.iceberg.Loader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.*;
import com.anthonyhilyard.iceberg.util.GuiHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;

import java.util.List;
import org.lwjgl.opengl.GL11;

public class Tooltip {
    // Thanks to Grend for permission to use the Iceberg Library and LegendaryTooltips code for the tooltips in this mod
    private static final Identifier TEXTURE_TOOLTIP_BORDERS = new Identifier("tiered", "textures/gui/tooltips.png");

    public static void drawBorder(MatrixStack matrixStack, int x, int y, int width, int height, ItemStack item, List<? extends TooltipComponent> lines, TextRenderer font, int frameLevel, boolean comparison) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE_TOOLTIP_BORDERS);

        // We have to bind the texture to be able to query it
        MinecraftClient mc = MinecraftClient.getInstance();
        AbstractTexture borderTexture = mc.getTextureManager().getTexture(TEXTURE_TOOLTIP_BORDERS);
        borderTexture.bindTexture();

        int textureWidth = 256;
        int textureHeight = 256;

        matrixStack.push();
        matrixStack.translate(0, 0, 410.0);

        // top left corner
        DrawableHelper.drawTexture(matrixStack, x-10, y-10, (frameLevel / 4) * 64, (frameLevel * 32) % textureHeight, 16, 16, textureWidth, textureHeight);

        // top right corner
        DrawableHelper.drawTexture(matrixStack, x+width-6, y-10, 112+(frameLevel / 4) * 64, (frameLevel * 32) % textureHeight, 16, 16, textureWidth, textureHeight);

        // bottom left corner
        DrawableHelper.drawTexture(matrixStack, x-10, y+height-6, (frameLevel / 4) * 64, 16+(frameLevel * 32) % textureHeight, 16, 16, textureWidth, textureHeight);

        // bottom right corner
        DrawableHelper.drawTexture(matrixStack, x+width-6, y+height-6, 112+(frameLevel / 4) * 64, 16+(frameLevel * 32) % textureHeight, 16, 16, textureWidth, textureHeight);

        if (width >= 64) {
            // top middle
            DrawableHelper.drawTexture(matrixStack, x + (width / 2) - 32, y - 12, 32 + (frameLevel / 4) * 64, (frameLevel * 32) % textureHeight, 64, 16, textureWidth, textureHeight);

            // bottom middle
            DrawableHelper.drawTexture(matrixStack, x + (width / 2) - 32, y + height - 4, 32 + (frameLevel / 4) * 64, (frameLevel * 32) % textureHeight + 16, 64, 16, textureWidth, textureHeight);
        }
        if (height >= 48) {
            // left side
            DrawableHelper.drawTexture(matrixStack, x-12, y+(height/2)-16, 16+(frameLevel / 4) * 64, (frameLevel * 32) % textureHeight, 16, 32, textureWidth, textureHeight);

            // right side
            DrawableHelper.drawTexture(matrixStack, x+width-4, y+(height/2)-16, 96+(frameLevel / 4) * 64, (frameLevel * 32) % textureHeight, 16, 32, textureWidth, textureHeight);
        }

        matrixStack.pop();
    }
}

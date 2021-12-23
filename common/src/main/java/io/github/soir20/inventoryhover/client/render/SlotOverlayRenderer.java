package io.github.soir20.inventoryhover.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;

public class SlotOverlayRenderer {

    public static void render(ResourceLocation location, Slot hoveredSlot, PoseStack matrixStack) {
        Minecraft.getInstance().getTextureManager().bind(location);
        GuiComponent.blit(matrixStack, hoveredSlot.x, hoveredSlot.y, 0, 0, 16, 16, 16, 16);
    }

}

package io.github.soir20.inventoryhover.fabric.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.soir20.inventoryhover.client.render.SlotOverlayRenderer;
import io.github.soir20.inventoryhover.fabric.client.config.ClientConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.soir20.inventoryhover.Entrypoint.OVERLAY_LOCATION;

@SuppressWarnings("unused")
@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {
    @Shadow
    protected Slot hoveredSlot;

    @ModifyArgs(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;IIIF)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;" +
                    "fillGradient(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"))
    private void replaceHighlightColor(Args args) {
        if (AutoConfig.getConfigHolder(ClientConfig.class).getConfig().defaultHighlight()) {
            return;
        }

        args.set(5, 0);
        args.set(6, 0);
    }

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;IIIF)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;" +
                    "renderLabels(Lcom/mojang/blaze3d/vertex/PoseStack;II)V"))
    private void onRender(PoseStack matrixStack, int mouseX, int mouseY,
                          float partialTicks, CallbackInfo callbackInfo) {
        if (hoveredSlot == null) {
            return;
        }

        SlotOverlayRenderer.render(OVERLAY_LOCATION, hoveredSlot, matrixStack);
    }

}

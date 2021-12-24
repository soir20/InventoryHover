package io.github.soir20.inventoryhover.forge.client.event;

import io.github.soir20.inventoryhover.client.render.SlotOverlayRenderer;
import io.github.soir20.inventoryhover.forge.client.config.ClientConfig;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.Logger;

import static io.github.soir20.inventoryhover.forge.EntrypointForge.OVERLAY_LOCATION;
import static java.util.Objects.requireNonNull;

@SuppressWarnings("unused")
public class GUIDrawEventSubscriber {
    private final ClientConfig CONFIG;
    private final Logger LOGGER;

    public GUIDrawEventSubscriber(ClientConfig config, Logger logger) {
        CONFIG = requireNonNull(config, "Config cannot be null");
        LOGGER = requireNonNull(logger, "Logger cannot be null");
    }

    @SubscribeEvent
    public void backgroundDrawEvent(final GuiContainerEvent.DrawBackground event) {
        if (CONFIG.defaultHighlight()) {
            return;
        }

        AbstractContainerScreen<?> screen = event.getGuiContainer();
        try {
            ObfuscationReflectionHelper.setPrivateValue(
                    AbstractContainerScreen.class, screen, 0, "slotColor"
            );
        } catch (ObfuscationReflectionHelper.UnableToAccessFieldException err) {
            LOGGER.error("Unable to access slot color field. Default overlay will be shown!");
        } catch (ObfuscationReflectionHelper.UnableToFindFieldException err) {
            LOGGER.error("Unable to find slot color field. Default overlay will be shown!");
        }
    }

    @SubscribeEvent
    public void foregroundDrawEvent(final GuiContainerEvent.DrawForeground event) {
        AbstractContainerScreen<?> screen = event.getGuiContainer();
        if (screen.getSlotUnderMouse() == null) {
            return;
        }

        SlotOverlayRenderer.render(OVERLAY_LOCATION, screen.getSlotUnderMouse(), event.getMatrixStack());
    }
}

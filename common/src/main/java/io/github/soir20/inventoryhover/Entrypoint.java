package io.github.soir20.inventoryhover;

import net.minecraft.resources.ResourceLocation;

public interface Entrypoint {
    String MODID = "inventoryhover";
    ResourceLocation OVERLAY_LOCATION = new ResourceLocation(
            MODID, "textures/slot-overlay.png"
    );
}

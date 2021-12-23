package io.github.soir20.inventoryhover.forge.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    private final ForgeConfigSpec.ConfigValue<Boolean> disableColorHighlight;

    public ClientConfig(ForgeConfigSpec.Builder configBuilder) {
        configBuilder.push("category1");
        disableColorHighlight = configBuilder.comment("desc").define("Disable default highlight", false);
        configBuilder.pop();
    }

    public boolean disableColorHighlight() {
        return disableColorHighlight.get();
    }

}

package io.github.soir20.inventoryhover.forge.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    private final ForgeConfigSpec.ConfigValue<Boolean> DEFAULT_COLOR_HIGHLIGHT;

    public ClientConfig(ForgeConfigSpec.Builder configBuilder) {
        configBuilder.push("vanilla");
        DEFAULT_COLOR_HIGHLIGHT = configBuilder
                .worldRestart()
                .comment("Whether to enable the vanilla slot hover overlay/highlight. May require world restart.")
                .define("default-overlay", true);
        configBuilder.pop();
    }

    public boolean defaultHighlight() {
        return DEFAULT_COLOR_HIGHLIGHT.get();
    }

    public void setDefaultHighlight(boolean isEnabled) {
        DEFAULT_COLOR_HIGHLIGHT.set(isEnabled);
    }

}

package io.github.soir20.inventoryhover.forge.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    private final ForgeConfigSpec.ConfigValue<Boolean> DEFAULT_COLOR_HIGHLIGHT;

    public ClientConfig(ForgeConfigSpec.Builder configBuilder) {
        configBuilder.push("category1");
        DEFAULT_COLOR_HIGHLIGHT = configBuilder.worldRestart().comment("desc").define("Default highlight", true);
        configBuilder.pop();
    }

    public boolean defaultHighlight() {
        return DEFAULT_COLOR_HIGHLIGHT.get();
    }

    public void setDefaultHighlight(boolean isEnabled) {
        DEFAULT_COLOR_HIGHLIGHT.set(isEnabled);
    }

}

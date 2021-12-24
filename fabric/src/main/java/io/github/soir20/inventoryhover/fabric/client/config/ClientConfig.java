package io.github.soir20.inventoryhover.fabric.client.config;

import io.github.soir20.inventoryhover.fabric.EntrypointFabric;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = EntrypointFabric.MODID)
public class ClientConfig implements ConfigData {
    private boolean defaultColorHighlight = true;

    public boolean defaultHighlight() {
        return defaultColorHighlight;
    }
}

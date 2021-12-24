package io.github.soir20.inventoryhover.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.soir20.inventoryhover.Entrypoint;
import io.github.soir20.inventoryhover.fabric.client.config.ClientConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class EntrypointFabric implements Entrypoint, ClientModInitializer, ModMenuApi {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(ClientConfig.class, Toml4jConfigSerializer::new);
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> AutoConfig.getConfigScreen(ClientConfig.class, parent).get();
    }
}

package io.github.soir20.inventoryhover.forge;

import io.github.soir20.inventoryhover.Entrypoint;
import io.github.soir20.inventoryhover.forge.client.config.ClientConfig;
import io.github.soir20.inventoryhover.forge.client.event.GUIDrawEventSubscriber;
import io.github.soir20.inventoryhover.forge.client.gui.ConfigGUI;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;

@Mod(EntrypointForge.MODID)
public class EntrypointForge implements Entrypoint {
    public EntrypointForge() {
        Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpecPair.getRight());
        MinecraftForge.EVENT_BUS.register(new GUIDrawEventSubscriber(clientSpecPair.getLeft(), LogManager.getLogger()));

        ModLoadingContext.get().registerExtensionPoint(
                ExtensionPoint.CONFIGGUIFACTORY,
                () -> (client, screen) -> new ConfigGUI(clientSpecPair.getLeft(), clientSpecPair.getRight(), screen)
        );
    }
}

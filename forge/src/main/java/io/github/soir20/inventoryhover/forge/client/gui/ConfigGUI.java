package io.github.soir20.inventoryhover.forge.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.soir20.inventoryhover.forge.EntrypointForge;
import io.github.soir20.inventoryhover.forge.client.config.ClientConfig;
import net.minecraft.client.BooleanOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.ForgeConfigSpec;

import static java.util.Objects.requireNonNull;

public final class ConfigGUI extends Screen {
    private final ClientConfig CONFIG;
    private final ForgeConfigSpec CONFIG_SPEC;
    private final Screen PARENT_SCREEN;
    private OptionsList options;

    public ConfigGUI(ClientConfig config, ForgeConfigSpec configSpec, Screen parentScreen) {
        super(new TranslatableComponent(EntrypointForge.MODID + ".config.title"));
        CONFIG = requireNonNull(config, "Config cannot be null");
        CONFIG_SPEC = requireNonNull(configSpec, "Config spec cannot be null");
        PARENT_SCREEN = requireNonNull(parentScreen, "Parent screen cannot be null");
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int titleHeight = 8;

        renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, titleHeight, 0xffffff);
        options.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        CONFIG_SPEC.save();
        requireNonNull(minecraft, "Client cannot be null").setScreen(PARENT_SCREEN);
    }

    @Override
    protected void init() {
        int verticalMargin = 32;
        int itemHeight = 25;

        options = new OptionsList(
                requireNonNull(minecraft, "Client cannot be null"),
                width, height,
                verticalMargin, height - verticalMargin,
                itemHeight
        );

        options.addBig(new BooleanOption(
                EntrypointForge.MODID + ".config.defaultoverlay",
                unused -> CONFIG.defaultHighlight(),
                (unused, isEnabled) -> CONFIG.setDefaultHighlight(isEnabled)
        ));

        int buttonHOffset  = width / 2 - 100;
        int buttonVOffset = height - 27;
        int buttonWidth = 200;
        int buttonHeight = 20;
        this.addButton(new Button(
                buttonHOffset, buttonVOffset,
                buttonWidth, buttonHeight,
                CommonComponents.GUI_DONE, (button) -> onClose())
        );

        this.children.add(options);
    }
}
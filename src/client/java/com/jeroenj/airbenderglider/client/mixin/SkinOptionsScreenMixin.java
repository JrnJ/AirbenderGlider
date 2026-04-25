package com.jeroenj.airbenderglider.client.mixin;

import com.jeroenj.airbenderglider.client.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.SkinCustomizationScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SkinCustomizationScreen.class)
public abstract class SkinOptionsScreenMixin extends OptionsSubScreen {
    public SkinOptionsScreenMixin(Screen screen, Options options, Component component) {
        super(screen, options, component);
    }

    @Override
    public void onClose() {
        super.onClose();
        ConfigManager.save();
    }

    @Inject(at = @At(value = "TAIL"), method = "addOptions")
    private void addOptions(CallbackInfo ci) {
        List<AbstractWidget> list = new ArrayList<>();

        list.add(CycleButton.onOffBuilder(ConfigManager.configData.renderAirbenderGliderPole)
                .create(Component.translatable("options.airbender-glider.render.pole"),
                        (button, enabled) -> {
                            ConfigManager.configData.renderAirbenderGliderPole = enabled;
                        }));

        this.list.addSmall(list);
    }
}

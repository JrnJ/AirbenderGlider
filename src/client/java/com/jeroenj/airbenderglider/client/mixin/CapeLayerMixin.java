package com.jeroenj.airbenderglider.client.mixin;

import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Inject(at = @At(value = "HEAD"), method = "hasLayer", cancellable = true)
    private void hasLayer(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, CallbackInfoReturnable<Boolean> cir) {
        // If the Airbender Glider is active, render the cape
        if (layerType == EquipmentClientInfo.LayerType.WINGS) {
            cir.setReturnValue(false);
        }
    }
}

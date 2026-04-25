package com.jeroenj.airbenderglider.client.mixin;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerCapeModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerCapeModel.class)
public class PlayerCapeModelMixin {
    @Shadow
    @Final
    private ModelPart cape;

    @Inject(at = @At(value = "HEAD"), method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", cancellable = true)
    private void setupAnim(AvatarRenderState state, CallbackInfo ci) {
        if (state.isFallFlying) {
            cape.setRotation((float)Math.toRadians(-3.0), (float)Math.toRadians(180.0), (float)Math.toRadians(0.0));
            ci.cancel();
        }
    }
}

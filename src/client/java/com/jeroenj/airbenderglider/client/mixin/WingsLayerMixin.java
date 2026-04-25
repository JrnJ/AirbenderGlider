package com.jeroenj.airbenderglider.client.mixin;

import com.jeroenj.airbenderglider.AirbenderGlider;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WingsLayer.class)
public abstract class WingsLayerMixin<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    @Unique
    private static final Identifier AIR_BENDER_GLIDER_TEXTURE = AirbenderGlider.id("textures/air_bender_glider/air_bender_glider.png");

    public WingsLayerMixin(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Inject(at = @At(value = "HEAD"), method = "getPlayerElytraTexture", cancellable = true)
    private static void getPlayerElytraTexture(HumanoidRenderState state, CallbackInfoReturnable<Identifier> cir) {
        cir.setReturnValue(AIR_BENDER_GLIDER_TEXTURE);
    }
}

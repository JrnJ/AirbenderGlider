package com.jeroenj.airbenderglider.client.mixin;

import com.jeroenj.airbenderglider.client.config.ConfigManager;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraModel.class)
public class ElytraModelMixin extends EntityModel<HumanoidRenderState> {
    @Shadow
    @Final
    private ModelPart leftWing;
    @Shadow
    @Final
    private ModelPart rightWing;
    @Unique
    private static final String AIR_BENDER_GLIDER = "air_bender_glider";

    @Unique
    private ModelPart main;

    @Unique
    private ModelPart pole;

    protected ElytraModelMixin(ModelPart modelPart) {
        super(modelPart);
    }

    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void init(ModelPart modelPart, CallbackInfo ci) {
        this.leftWing.visible = false;
        this.rightWing.visible = false;

        //
        this.main = this.root.getChild("main");
        this.pole = this.root.getChild("pole");
    }

    @Inject(at = @At(value = "HEAD"), method = "createLayer", cancellable = true)
    private static void createLayer(CallbackInfoReturnable<LayerDefinition> cir) {
        cir.setReturnValue(createMyLayer());
    }

    @Unique
    private static LayerDefinition createMyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Dummy
        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.ZERO);

        // Actual
        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -7.0F, 0.2F, 2.0F, 54.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 8.0F));

        PartDefinition wing = main.addOrReplaceChild("wing", CubeListBuilder.create(), PartPose.offset(0.0F, 32.0F, -4.0F));

        PartDefinition rightWing = wing.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(-1.0F, -31.5F, 4.5F));

        PartDefinition rightBeam1 = rightWing.addOrReplaceChild("rightBeam1", CubeListBuilder.create().texOffs(0, 44).addBox(-30.0F, 0.0F, -0.5F, 30.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-30.0F, 1.0F, 0.0F, 30.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition rightBeam2 = rightWing.addOrReplaceChild("rightBeam2", CubeListBuilder.create().texOffs(6, 46).addBox(-25.0F, 0.0F, -0.5F, 25.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.1F, 0.0F, 0.0F, -0.7854F));

        PartDefinition rightBeam3 = rightWing.addOrReplaceChild("rightBeam3", CubeListBuilder.create().texOffs(6, 50).addBox(-22.0F, 0.0F, -0.45F, 22.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -1.2217F));

        PartDefinition leftWing = wing.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(1.0F, -31.5F, 4.5F));

        PartDefinition leftBeam1 = leftWing.addOrReplaceChild("leftBeam1", CubeListBuilder.create().texOffs(0, 42).addBox(0.0F, 0.0F, -0.5F, 30.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition wing_r1 = leftBeam1.addOrReplaceChild("wing_r1", CubeListBuilder.create().texOffs(0, 21).addBox(-15.0F, -10.5F, 0.0F, 30.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 11.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition leftBeam2 = leftWing.addOrReplaceChild("leftBeam2", CubeListBuilder.create().texOffs(6, 48).addBox(0.0F, 0.0F, -0.5F, 25.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.1F, 0.0F, 0.0F, 0.7854F));

        PartDefinition leftBeam3 = leftWing.addOrReplaceChild("leftBeam3", CubeListBuilder.create().texOffs(6, 52).addBox(0.0F, 0.0F, -0.45F, 22.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 1.2217F));

        PartDefinition wing2 = main.addOrReplaceChild("wing2", CubeListBuilder.create(), PartPose.offset(0.0F, 32.0F, -4.0F));

        PartDefinition rightWing2 = wing2.addOrReplaceChild("rightWing2", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, 4.5F));

        PartDefinition rightBeam1_2 = rightWing2.addOrReplaceChild("rightBeam1_2", CubeListBuilder.create().texOffs(58, 46).addBox(-12.0F, 0.0F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 50).addBox(-12.0F, 1.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition rightBeam2_2 = rightWing2.addOrReplaceChild("rightBeam2_2", CubeListBuilder.create().texOffs(30, 54).addBox(-10.0F, 0.0F, -0.4F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition rightBeam3_2 = rightWing2.addOrReplaceChild("rightBeam3_2", CubeListBuilder.create().texOffs(58, 48).addBox(-9.0F, 0.0F, -0.45F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.1345F));

        PartDefinition leftWing2 = wing2.addOrReplaceChild("leftWing2", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 4.5F));

        PartDefinition leftBeam1_2 = leftWing2.addOrReplaceChild("leftBeam1_2", CubeListBuilder.create().texOffs(30, 58).addBox(0.0F, 0.0F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition wing_r2 = leftBeam1_2.addOrReplaceChild("wing_r2", CubeListBuilder.create().texOffs(6, 54).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition leftBeam2_2 = leftWing2.addOrReplaceChild("leftBeam2_2", CubeListBuilder.create().texOffs(30, 56).addBox(0.0F, 0.0F, -0.4F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition leftBeam3_2 = leftWing2.addOrReplaceChild("leftBeam3_2", CubeListBuilder.create().texOffs(56, 58).addBox(0.0F, 0.0F, -0.45F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

        PartDefinition pole = partdefinition.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(6, 67).addBox(1.1472F, -21.3704F, -3.75F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 4.0F, 0.0F, 0.0F, -0.6109F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Inject(at = @At(value = "HEAD"), method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", cancellable = true)
    private void setupAnim(HumanoidRenderState state, CallbackInfo ci) {
        if (state.entityType != EntityType.ARMOR_STAND) {
            this.main.visible = state.isFallFlying;
            if (ConfigManager.configData.renderAirbenderGliderPole) {
                this.pole.visible = !state.isFallFlying;
            } else {
                this.pole.visible = false;
            }

            if (state.isFallFlying) {
                this.main.xRot = (float)Math.toRadians(-11.0);
            }
        } else {
            this.pole.visible = false;
        }

        // Below
        ci.cancel();
    }
}

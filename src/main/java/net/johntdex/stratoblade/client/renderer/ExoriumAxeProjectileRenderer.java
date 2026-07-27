package net.johntdex.stratoblade.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.client.model.ExoriumAxeModel;
import net.johntdex.stratoblade.entity.ExoriumAxeProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ExoriumAxeProjectileRenderer extends EntityRenderer<ExoriumAxeProjectileEntity> {
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, "textures/entity/exorium_axe/exorium_axe_3d.png");
    private final ExoriumAxeModel<ExoriumAxeProjectileEntity> model;

    public ExoriumAxeProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ExoriumAxeModel<>(context.bakeLayer(ExoriumAxeModel.LAYER_LOCATION));
    }

    @Override
    public void render(ExoriumAxeProjectileEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        if (entity.isGrounded() && entity.groundedOffset != null) {
            poseStack.mulPose(Axis.YP.rotationDegrees(entity.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(entity.groundedOffset.x));
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees((entity.tickCount + partialTicks) * 40.0F));
        }

        VertexConsumer vc = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(entity)), false, false);
        // Centering offset in the model's own frame, applied BEFORE the rotations above.
        // This model's root is PartPose.offset(0, 11.0495, -0.5779) with the handle spanning
        // -0.0495 -> 12.95, so the geometry sits roughly 9.5-24px from the pivot:
        //   0.6F  -> axe head on the hitbox (head cubes are near ~9.5px)
        //   1.05F -> whole model centred (midpoint ~16.75px / 16)
        // The root also carries a small Z offset, so a slight Z here may help.
        poseStack.translate(0.0F, 0.9F, 0.3F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ExoriumAxeProjectileEntity entity) {
        return TEXTURE;
    }
}

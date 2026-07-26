package net.johntdex.stratoblade.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.client.model.HatchetModel;
import net.johntdex.stratoblade.entity.HatchetProjectileEntity;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class HatchetProjectileRenderer extends EntityRenderer<HatchetProjectileEntity> {
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, "textures/entity/hatchet/hatchet.png");
    private final HatchetModel<HatchetProjectileEntity> model;

    public HatchetProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new HatchetModel<>(context.bakeLayer(HatchetModel.LAYER_LOCATION));
    }

    @Override
    public void render(HatchetProjectileEntity entity, float entityYaw, float partialTicks,
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
        // Centering offset, applied in the model's own frame BEFORE the rotations above.
        // The model's geometry sits 14-24px from its root (PartPose.offset(0, 24, 0)), so after
        // the flip below it hangs 0.875-1.5 blocks off the pivot. Cancelling that here centres it
        // for EVERY orientation, which is why no per-face offsets are needed.
        //   0.9F   -> blade sits on the hitbox (head is at ~14-15px)
        //   1.1875F-> whole model centred on the hitbox (midpoint 19px / 16)
        poseStack.translate(0.0F, 1.1875F, 0.0F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HatchetProjectileEntity entity) {
        return TEXTURE;
    }
}
package net.johntdex.stratoblade.client.model;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ExoriumAxeModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, "exorium_axe"), "main");
	private final ModelPart axe;

	public ExoriumAxeModel(ModelPart root) {
		this.axe = root.getChild("axe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition axe = partdefinition.addOrReplaceChild("axe", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.0495F, -0.4221F, 2.0F, 13.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 11.0495F, -0.5779F));

		PartDefinition cube_r1 = axe.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 13).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -3.7652F, 1.006F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r2 = axe.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 19).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -2.6843F, 2.1856F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r3 = axe.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -1.2344F, 1.3996F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r4 = axe.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 17).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.2463F, 0.8433F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r5 = axe.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -0.3739F, 0.3152F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r6 = axe.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.5899F, -1.0119F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r7 = axe.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.0495F, -0.4221F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r8 = axe.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(8, 15).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -1.7995F, 0.5779F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r9 = axe.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -0.0495F, 0.5779F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		axe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}

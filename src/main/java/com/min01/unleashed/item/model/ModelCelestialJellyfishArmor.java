package com.min01.unleashed.item.model;

import com.min01.unleashed.BossesUnleashed;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class ModelCelestialJellyfishArmor<T extends LivingEntity> extends HumanoidModel<T>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BossesUnleashed.MODID, "celestial_jellyfish_armor"), "main");
	public final ModelPart helmet;
	public final ModelPart right_arm;
	public final ModelPart left_arm;
	public final ModelPart chestplate;
	public final ModelPart leggings;
	public final ModelPart r_leg;
	public final ModelPart l_leg;
	public final ModelPart right_feet;
	public final ModelPart left_feet;

	public ModelCelestialJellyfishArmor(ModelPart root) 
	{
		super(root);
		this.helmet = root.getChild("helmet");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.chestplate = root.getChild("chestplate");
		this.leggings = root.getChild("leggings");
		this.r_leg = root.getChild("r_leg");
		this.l_leg = root.getChild("l_leg");
		this.right_feet = root.getChild("right_feet");
		this.left_feet = root.getChild("left_feet");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition helmet = partdefinition.addOrReplaceChild("helmet", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		helmet.addOrReplaceChild("h", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition chestplate = partdefinition.addOrReplaceChild("chestplate", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		chestplate.addOrReplaceChild("bod", CubeListBuilder.create().texOffs(0, 18).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 13.0F, 5.0F, new CubeDeformation(0.1F))
		.texOffs(60, 41).addBox(-2.5F, 1.5F, 2.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition leggings = partdefinition.addOrReplaceChild("leggings", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		leggings.addOrReplaceChild("legg", CubeListBuilder.create().texOffs(68, 9).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 13.0F, 5.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		right_arm.addOrReplaceChild("r_arm", CubeListBuilder.create().texOffs(44, 54).mirror().addBox(0.0F, -3.5F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(22, 54).mirror().addBox(0.0F, -0.5F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(48, 18).mirror().addBox(-1.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		left_arm.addOrReplaceChild("l_arm", CubeListBuilder.create().texOffs(28, 18).mirror().addBox(-3.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 54).mirror().addBox(-5.0F, -0.5F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 0).addBox(-5.0F, -3.5F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition r_leg = partdefinition.addOrReplaceChild("r_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		r_leg.addOrReplaceChild("rr_leg", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition l_leg = partdefinition.addOrReplaceChild("l_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		l_leg.addOrReplaceChild("ll_leg", CubeListBuilder.create().texOffs(40, 36).mirror().addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition right_feet = partdefinition.addOrReplaceChild("right_feet", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		right_feet.addOrReplaceChild("r_feet", CubeListBuilder.create().texOffs(20, 36).mirror().addBox(-2.5F, -0.99F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 9).mirror().addBox(-2.5F, 9.01F, 2.5F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_feet = partdefinition.addOrReplaceChild("left_feet", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		left_feet.addOrReplaceChild("l_feet", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-2.5F, -0.99F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(60, 36).mirror().addBox(-2.5F, 9.01F, 2.5F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) 
	{
		this.helmet.copyFrom(this.head);
		this.chestplate.copyFrom(this.body);
		this.leggings.copyFrom(this.body);
		this.right_arm.copyFrom(this.rightArm);
		this.left_arm.copyFrom(this.leftArm);
		this.r_leg.copyFrom(this.rightLeg);
		this.l_leg.copyFrom(this.leftLeg);
		this.right_feet.copyFrom(this.rightLeg);
		this.left_feet.copyFrom(this.leftLeg);

		helmet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leggings.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		r_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		l_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_feet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_feet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
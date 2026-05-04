package com.min01.unleashed.entity.model;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.animation.MadLumberjackAnimation;
import com.min01.unleashed.entity.animation.MadLumberjackAnimation2;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.misc.SmoothAnimationState;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class MadLumberjackModel extends HierarchicalModel<MadLumberjackEntity>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "mad_lumberjack"), "main");
	public final ModelPart root;
	public final ModelPart bone;
	public final ModelPart bone2;
	public final ModelPart bone3;
	public final ModelPart bone18;
	public final ModelPart bone9;
	public final ModelPart bone10;
	public final ModelPart bone11;
	public final ModelPart axe;
	public final ModelPart shotgun;

	public MadLumberjackModel(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.bone = this.root.getChild("bone");
		this.bone2 = this.bone.getChild("bone2");
		this.bone3 = this.bone2.getChild("bone3");
		this.bone9 = this.bone3.getChild("bone9");
		this.bone18 = this.bone3.getChild("bone18");
		this.bone10 = this.bone9.getChild("bone10");
		this.bone11 = this.bone10.getChild("bone11");
		this.axe = this.bone11.getChild("axe");
		this.shotgun = this.bone11.getChild("shotgun");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 40).addBox(-17.0F, -40.0F, -9.0F, 26.0F, 11.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 4.0F));

		PartDefinition bone15 = bone.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(58, 66).mirror().addBox(-11.0F, -4.0F, -4.5F, 14.0F, 19.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, -33.0F, -6.0F));

		bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(162, 0).mirror().addBox(-6.0F, -2.0F, -6.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(116, 113).mirror().addBox(-6.0F, 12.0F, -12.0F, 11.0F, 6.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 15.0F, 5.0F));

		PartDefinition bone14 = bone.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(58, 66).addBox(-3.0F, -4.0F, -4.5F, 14.0F, 19.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -33.0F, -6.0F));

		bone14.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(162, 0).addBox(-5.0F, -2.0F, -6.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(116, 113).addBox(-5.0F, 12.0F, -12.0F, 11.0F, 6.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 15.0F, 5.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(82, 40).addBox(-10.0F, -11.0F, -7.0F, 20.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -40.0F, 0.0F));

		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -20.0F, -12.0F, 34.0F, 21.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition bone9 = bone3.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(56, 132).mirror().addBox(-9.0F, 0.0F, -4.0F, 9.0F, 22.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(200, 10).addBox(-11.0F, -9.0F, -5.0F, 13.0F, 17.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, -12.0F, -5.0F));

		PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(172, 57).addBox(-4.0F, -3.0F, -5.5F, 7.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 22.0F, 3.0F));

		PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(172, 104).addBox(-2.0F, 0.0F, -6.0F, 5.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 15.0F, 0.0F));

		bone11.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(131, 183).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.0F, 4.0F));

		bone11.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(131, 193).addBox(-0.5F, -0.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 3.5F, -5.5F));

		bone11.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(130, 171).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -4.5F));

		bone11.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(130, 171).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -1.5F));

		bone11.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(130, 171).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 1.5F));

		PartDefinition axe = bone11.addOrReplaceChild("axe", CubeListBuilder.create().texOffs(65, 159).addBox(-2.0F, -2.0F, -38.0F, 4.0F, 4.0F, 93.0F, new CubeDeformation(0.0F))
		.texOffs(64, 206).addBox(-3.0F, -5.0F, -33.0F, 6.0F, 10.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(2, 217).addBox(-0.5F, 10.0F, -35.0F, 1.0F, 10.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(0, 191).addBox(-0.5F, 5.0F, -33.0F, 1.0F, 5.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 9.0F, -1.0F));

		axe.addOrReplaceChild("axePos", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -22.0F));

		bone11.addOrReplaceChild("shotgun", CubeListBuilder.create().texOffs(166, 217).addBox(-2.0F, -1.0F, -10.25F, 4.0F, 4.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(126, 210).addBox(-4.0F, 2.0F, -13.25F, 8.0F, 34.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(100, 239).addBox(-4.0F, 36.0F, -13.25F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(166, 225).addBox(0.0F, 3.0F, -10.25F, 0.0F, 13.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, -0.75F));

		PartDefinition bone6 = bone3.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(56, 132).addBox(0.0F, 0.0F, -4.0F, 9.0F, 22.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(106, 0).addBox(-2.0F, -9.0F, -5.0F, 13.0F, 17.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(17.0F, -12.0F, -5.0F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(96, 171).addBox(-3.0F, -3.0F, -5.5F, 7.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 22.0F, 3.0F));

		PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(172, 85).addBox(-3.0F, 0.0F, -6.0F, 5.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 15.0F, 0.0F));

		bone8.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(130, 171).mirror().addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.0F, -4.5F));

		bone8.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(130, 171).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -1.5F));

		bone8.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(130, 171).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 1.5F));

		bone8.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(131, 183).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 4.0F));

		bone8.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(131, 193).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 3.5F, -5.5F));

		PartDefinition bone18 = bone3.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 100).addBox(-7.5F, -11.0F, -12.0F, 15.0F, 15.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(48, 170).addBox(-6.0F, 0.5F, -10.5F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(116, 88).addBox(-7.0F, -10.5F, -11.5F, 14.0F, 11.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.5F, -12.5F));

		bone18.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 195).addBox(-12.5F, -7.5F, 0.0F, 25.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, -4.0F, 0.0F, 0.7854F, 0.0F));

		bone18.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 195).addBox(-12.5F, -7.5F, 0.0F, 25.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, -4.0F, 0.0F, 2.3562F, 0.0F));

		bone18.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(144, 136).addBox(-7.0F, 2.5F, -11.5F, 14.0F, 9.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 153).addBox(-7.0F, -0.5F, -11.5F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 170).addBox(-6.0F, -3.5F, -10.5F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(MadLumberjackEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.shotgun.visible = entity.isGun() && entity.getPhase() < 2;
		this.axe.visible = !entity.isGun() && entity.getPhase() < 2;
		
		UnleashedClientUtil.animateHead(this.bone18, netHeadYaw, headPitch);

		entity.axeIdleAnimationState.animateIdle(this, MadLumberjackAnimation.AXE_IDLE, ageInTicks, limbSwingAmount, 2.5F, entity.gunWalkAnimationState, entity.zombieWalkAnimationState, entity.zombieSprintAnimationState);
		entity.gunIdleAnimationState.animateIdle(this, MadLumberjackAnimation.GUN_IDLE, ageInTicks, limbSwingAmount, 2.5F, entity.gunWalkAnimationState, entity.zombieWalkAnimationState, entity.zombieSprintAnimationState);
		entity.zombieIdleAnimationState.animateIdle(this, MadLumberjackAnimation.ZOMBIE_IDLE, ageInTicks, limbSwingAmount, 2.5F, entity.gunWalkAnimationState, entity.zombieWalkAnimationState, entity.zombieSprintAnimationState);
		
		entity.axeAttack1AnimationState.animate(this, MadLumberjackAnimation.AXE_ATTACK1, ageInTicks);
		entity.axeAttack2AnimationState.animate(this, MadLumberjackAnimation.AXE_ATTACK2, ageInTicks);
		entity.axeAttack3AnimationState.animate(this, MadLumberjackAnimation.AXE_ATTACK3, ageInTicks);
		entity.shotgunShotAnimationState.animate(this, MadLumberjackAnimation.SHOTGUN_SHOT, ageInTicks);
		entity.axeChangeAnimationState.animate(this, MadLumberjackAnimation.AXE_CHANGE, ageInTicks);
		entity.shotgunChangeAnimationState.animate(this, MadLumberjackAnimation.SHOTGUN_CHANGE, ageInTicks);
		entity.zombieAttack1AnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK1, ageInTicks);
		entity.zombieAttack2AnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK2, ageInTicks);
		entity.zombieAttack2SuccessAnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK2_SUCCESS, ageInTicks);
		entity.zombieAttack3AnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK3, ageInTicks);
		entity.zombieAttack4LeftAnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK4_LEFT, ageInTicks);
		entity.zombieAttack4RightAnimationState.animate(this, MadLumberjackAnimation2.ZOMBIE_ATTACK4_RIGHT, ageInTicks);
		
		SmoothAnimationState.animateWalk(this, MadLumberjackAnimation.AXE_WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F, entity.gunWalkAnimationState, entity.zombieWalkAnimationState, entity.zombieSprintAnimationState, 
				entity.axeAttack1AnimationState, entity.axeAttack2AnimationState, 
				entity.axeAttack3AnimationState, entity.shotgunShotAnimationState, 
				entity.axeChangeAnimationState, entity.shotgunChangeAnimationState, 
				entity.zombieAttack1AnimationState, entity.zombieAttack2AnimationState, 
				entity.zombieAttack2SuccessAnimationState, entity.zombieAttack3AnimationState, 
				entity.zombieAttack4LeftAnimationState, entity.zombieAttack4RightAnimationState);
		entity.gunWalkAnimationState.animateWalkWithFactor(this, MadLumberjackAnimation.GUN_WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		entity.zombieWalkAnimationState.animateWalkWithFactor(this, MadLumberjackAnimation.ZOMBIE_WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		entity.zombieSprintAnimationState.animateWalkWithFactor(this, MadLumberjackAnimation2.ZOMBIE_SPRINT, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		//entity.axeSprintAnimationState.animateWalkWithFactor(this, MadLumberjackAnimation.AXE_SPRINT, limbSwing, limbSwingAmount, 1.0F, 1.0F);
		//entity.gunSprintAnimationState.animateWalkWithFactor(this, MadLumberjackAnimation.GUN_SPRINT, limbSwing, limbSwingAmount, 1.0F, 1.0F);
	}
	
	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
package com.min01.unleashed.entity.model;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.animation.CelestialJellyfishAnimation;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
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
import net.minecraft.util.Mth;

public class ModelCelestialJellyfish extends HierarchicalModel<EntityCelestialJellyfish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BossesUnleashed.MODID, "celestial_jellyfish"), "main");
	private final ModelPart root;
	private final ModelPart jellyfish;
	private final ModelPart umbrella;
	private final ModelPart top_umbrella;
	private EntityCelestialJellyfish entity;

	public ModelCelestialJellyfish(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.jellyfish = this.root.getChild("jellyfish");
		this.umbrella = this.jellyfish.getChild("umbrella");
		this.top_umbrella = this.umbrella.getChild("top_umbrella");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition jellyfish = root.addOrReplaceChild("jellyfish", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition umbrella = jellyfish.addOrReplaceChild("umbrella", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));

		umbrella.addOrReplaceChild("top_umbrella", CubeListBuilder.create().texOffs(0, 59).addBox(-21.0F, -21.0F, -21.0F, 42.0F, 21.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		umbrella.addOrReplaceChild("core", CubeListBuilder.create().texOffs(152, 122).addBox(-14.0F, -23.0F, -14.0F, 28.0F, 24.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition outer_umbrella = umbrella.addOrReplaceChild("outer_umbrella", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -11.0F, -24.0F, 48.0F, 11.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(0, 122).addBox(-19.0F, -23.0F, -19.0F, 38.0F, 30.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition tentacle = outer_umbrella.addOrReplaceChild("tentacle", CubeListBuilder.create().texOffs(204, 204).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.0F, 0.0F, 0.0F));

		PartDefinition tentacle2 = tentacle.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(0, 220).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		tentacle2.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(260, -4).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		PartDefinition tentacle4 = outer_umbrella.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(204, 238).addBox(-17.0F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 22.0F));

		PartDefinition tentacle5 = tentacle4.addOrReplaceChild("tentacle5", CubeListBuilder.create().texOffs(0, 254).addBox(-17.0F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		tentacle5.addOrReplaceChild("tentacle6", CubeListBuilder.create().texOffs(260, 30).addBox(-17.0F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		PartDefinition tentacle7 = outer_umbrella.addOrReplaceChild("tentacle7", CubeListBuilder.create().texOffs(204, 238).addBox(-13.5F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, -22.0F));

		PartDefinition tentacle8 = tentacle7.addOrReplaceChild("tentacle8", CubeListBuilder.create().texOffs(0, 254).addBox(-17.0F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 30.0F, 0.0F));

		tentacle8.addOrReplaceChild("tentacle9", CubeListBuilder.create().texOffs(260, 30).addBox(-17.0F, 0.0F, 0.0F, 34.0F, 30.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		PartDefinition tentacle10 = outer_umbrella.addOrReplaceChild("tentacle10", CubeListBuilder.create().texOffs(204, 204).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(22.0F, 0.0F, 0.0F));

		PartDefinition tentacle11 = tentacle10.addOrReplaceChild("tentacle11", CubeListBuilder.create().texOffs(0, 220).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		tentacle11.addOrReplaceChild("tentacle12", CubeListBuilder.create().texOffs(260, -4).addBox(0.0F, 0.0F, -17.0F, 0.0F, 30.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

		PartDefinition tentacles = jellyfish.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition large_tentacle = tentacles.addOrReplaceChild("large_tentacle", CubeListBuilder.create().texOffs(168, 64).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(264, 129).addBox(-5.0F, -4.5F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.5F, -8.0F));

		PartDefinition large_tentacle2 = large_tentacle.addOrReplaceChild("large_tentacle2", CubeListBuilder.create().texOffs(264, 94).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));

		PartDefinition large_tentacle3 = large_tentacle2.addOrReplaceChild("large_tentacle3", CubeListBuilder.create().texOffs(204, 268).addBox(-4.0F, -0.25F, -4.0F, 8.0F, 25.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.25F, 0.0F));

		large_tentacle3.addOrReplaceChild("large_tentacle4", CubeListBuilder.create().texOffs(236, 268).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.25F, 0.0F));

		PartDefinition large_tentacle5 = tentacles.addOrReplaceChild("large_tentacle5", CubeListBuilder.create().texOffs(168, 64).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(264, 129).addBox(-5.0F, -4.5F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.5F, 8.0F));

		PartDefinition large_tentacle6 = large_tentacle5.addOrReplaceChild("large_tentacle6", CubeListBuilder.create().texOffs(264, 94).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));

		PartDefinition large_tentacle7 = large_tentacle6.addOrReplaceChild("large_tentacle7", CubeListBuilder.create().texOffs(204, 268).addBox(-4.0F, -0.25F, -4.0F, 8.0F, 25.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.25F, 0.0F));

		large_tentacle7.addOrReplaceChild("large_tentacle8", CubeListBuilder.create().texOffs(236, 268).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.25F, 0.0F));

		PartDefinition large_tentacle9 = tentacles.addOrReplaceChild("large_tentacle9", CubeListBuilder.create().texOffs(168, 64).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(264, 129).addBox(-5.0F, -4.5F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 0.5F, -8.0F));

		PartDefinition large_tentacle10 = large_tentacle9.addOrReplaceChild("large_tentacle10", CubeListBuilder.create().texOffs(264, 94).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));

		PartDefinition large_tentacle11 = large_tentacle10.addOrReplaceChild("large_tentacle11", CubeListBuilder.create().texOffs(204, 268).addBox(-4.0F, -0.25F, -4.0F, 8.0F, 25.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.25F, 0.0F));

		large_tentacle11.addOrReplaceChild("large_tentacle12", CubeListBuilder.create().texOffs(236, 268).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.25F, 0.0F));

		PartDefinition large_tentacle13 = tentacles.addOrReplaceChild("large_tentacle13", CubeListBuilder.create().texOffs(168, 64).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(264, 129).addBox(-5.0F, -4.5F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 0.5F, 8.0F));

		PartDefinition large_tentacle14 = large_tentacle13.addOrReplaceChild("large_tentacle14", CubeListBuilder.create().texOffs(264, 94).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 0.0F));

		PartDefinition large_tentacle15 = large_tentacle14.addOrReplaceChild("large_tentacle15", CubeListBuilder.create().texOffs(204, 268).addBox(-4.0F, -0.25F, -4.0F, 8.0F, 25.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.25F, 0.0F));

		large_tentacle15.addOrReplaceChild("large_tentacle16", CubeListBuilder.create().texOffs(236, 268).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.25F, 0.0F));

		PartDefinition star2 = jellyfish.addOrReplaceChild("star2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -24.0F, -11.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star3 = jellyfish.addOrReplaceChild("star3", CubeListBuilder.create(), PartPose.offset(-6.0F, -26.0F, 2.0F));

		star3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star4 = jellyfish.addOrReplaceChild("star4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -25.0F, -4.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star5 = jellyfish.addOrReplaceChild("star5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -24.0F, -7.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star6 = jellyfish.addOrReplaceChild("star6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star6.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -24.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star7 = jellyfish.addOrReplaceChild("star7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star7.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -25.0F, 7.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star8 = jellyfish.addOrReplaceChild("star8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star8.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -25.0F, 10.0F, 1.5708F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star9", CubeListBuilder.create().texOffs(168, 59).addBox(-7.0F, -26.0F, 16.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition star10 = jellyfish.addOrReplaceChild("star10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star10.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, -22.5F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star11 = jellyfish.addOrReplaceChild("star11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star11.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(168, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -24.0F, 13.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition star12 = jellyfish.addOrReplaceChild("star12", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star12.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -24.5F, 6.0F, 0.0F, -1.5708F, 0.0F));

		jellyfish.addOrReplaceChild("star13", CubeListBuilder.create().texOffs(168, 59).addBox(-1.0F, -26.0F, -15.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition star14 = jellyfish.addOrReplaceChild("star14", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star14.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -13.5F, 11.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star15 = jellyfish.addOrReplaceChild("star15", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star15.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, -8.5F, -3.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star16 = jellyfish.addOrReplaceChild("star16", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star16.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.5F, -19.5F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star17 = jellyfish.addOrReplaceChild("star17", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star17.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.5F, -8.5F, 6.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star18 = jellyfish.addOrReplaceChild("star18", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star18.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -12.5F, -10.0F, 0.0F, -1.5708F, 0.0F));

		jellyfish.addOrReplaceChild("star19", CubeListBuilder.create().texOffs(168, 59).addBox(7.0F, -14.0F, -15.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition star20 = jellyfish.addOrReplaceChild("star20", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		star20.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, -20.5F, -13.0F, 0.0F, -1.5708F, 0.0F));

		jellyfish.addOrReplaceChild("star21", CubeListBuilder.create().texOffs(168, 59).addBox(-8.5F, -2.5F, -4.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.5F, -20.5F, -13.0F));

		PartDefinition star22 = jellyfish.addOrReplaceChild("star22", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star22.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.0F, 3.0F, 17.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star23 = jellyfish.addOrReplaceChild("star23", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star23.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-28.0F, 0.0F, 10.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star24 = jellyfish.addOrReplaceChild("star24", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star24.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.0F, 8.0F, 13.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star25 = jellyfish.addOrReplaceChild("star25", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star25.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-30.0F, 16.0F, 15.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star26 = jellyfish.addOrReplaceChild("star26", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star26.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-30.0F, 9.0F, 4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star27 = jellyfish.addOrReplaceChild("star27", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star27.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.0F, 12.0F, 22.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition star28 = jellyfish.addOrReplaceChild("star28", CubeListBuilder.create(), PartPose.offset(14.5F, -20.5F, -13.0F));

		star28.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(168, 59).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.0F, 6.0F, 23.0F, 0.0F, -1.5708F, 0.0F));

		jellyfish.addOrReplaceChild("star29", CubeListBuilder.create().texOffs(168, 59).addBox(-21.5F, 4.5F, 31.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.5F, -20.5F, -13.0F));

		jellyfish.addOrReplaceChild("star30", CubeListBuilder.create().texOffs(168, 59).addBox(-5.5F, 5.5F, 28.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.5F, -20.5F, -13.0F));

		jellyfish.addOrReplaceChild("star31", CubeListBuilder.create().texOffs(168, 59).addBox(-2.5F, 12.5F, 28.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.5F, -20.5F, -13.0F));

		jellyfish.addOrReplaceChild("star32", CubeListBuilder.create().texOffs(168, 59).addBox(-15.5F, 0.5F, -5.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(14.5F, -20.5F, -13.0F));

		jellyfish.addOrReplaceChild("star33", CubeListBuilder.create().texOffs(168, 59).addBox(-11.0F, -11.0F, -19.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star34", CubeListBuilder.create().texOffs(168, 59).addBox(15.0F, -6.0F, -17.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star35", CubeListBuilder.create().texOffs(168, 59).addBox(-1.0F, -8.0F, -19.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star36", CubeListBuilder.create().texOffs(168, 59).addBox(-7.0F, -18.0F, -15.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star37", CubeListBuilder.create().texOffs(168, 59).addBox(-1.0F, -20.0F, 17.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star38", CubeListBuilder.create().texOffs(168, 59).addBox(2.0F, -11.0F, 16.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		jellyfish.addOrReplaceChild("star39", CubeListBuilder.create().texOffs(168, 59).addBox(-11.0F, -8.0F, 17.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(EntityCelestialJellyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.entity = entity;
		this.root().getAllParts().forEach(ModelPart::resetPose);
		boolean isHitTime = entity.isHitTime();
		boolean isFirstPhase = !entity.isAlive() && !entity.isSecondPhase();
		if(!isHitTime && entity.isAlive())
		{
			UnleashedClientUtil.animateHead(this.root, netHeadYaw, headPitch + 90.0F);
		}
		entity.swimAnimationState.animate(this, CelestialJellyfishAnimation.JELLYFISH_SWIM, ageInTicks);
		this.root.visible = entity.isVisible();
		this.top_umbrella.visible = !entity.isFinalPhase();
		if(isFirstPhase)
		{
			float pitch = headPitch + 90.0F;
			float xRot = Mth.lerp(Math.min(entity.phaseTime / 20.0F, 1.0F), pitch, pitch < 0.0F ? -65.0F : 65.0F);
			this.root.xRot = (float) Math.toRadians(xRot);
		}
		if(isHitTime)
		{
			this.root.y -= 95;
		}
	}
	
	@Override
	public ModelPart root() 
	{
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		if(this.entity != null && this.entity.isClone())
		{
			red = 0.25F;
			green = 0.25F;
			alpha = 0.25F;
		}
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
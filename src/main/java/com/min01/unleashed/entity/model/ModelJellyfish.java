package com.min01.unleashed.entity.model;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.animation.JellyfishAnimation;
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

public class ModelJellyfish extends HierarchicalModel<EntityCelestialJellyfish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BossesUnleashed.MODID, "jellyfish"), "main");
	private final ModelPart root;
	private final ModelPart jellyfish;

	public ModelJellyfish(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.jellyfish = this.root.getChild("jellyfish");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		root.addOrReplaceChild("jellyfish", CubeListBuilder.create().texOffs(42, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, 3.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(-5.0F, 4.0F, -5.0F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(EntityCelestialJellyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		UnleashedClientUtil.animateHead(this.jellyfish, netHeadYaw, headPitch + 90.0F);
		this.animateWalk(JellyfishAnimation.JELLYFISH_SWIM, limbSwing, limbSwingAmount, 3.5F, 3.5F);
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
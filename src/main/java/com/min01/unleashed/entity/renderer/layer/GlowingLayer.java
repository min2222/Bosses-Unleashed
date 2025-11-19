package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.misc.UnleashedRenderType;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M>
{
	protected M model;
	protected ResourceLocation texture;
	
	public GlowingLayer(RenderLayerParent<T, M> renderer, M model, ResourceLocation texture)
	{
		super(renderer);
		this.model = model;
		this.texture = texture;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float p_117358_)
	{
		this.coloredGlowingModelCopyLayerRender(this.getParentModel(), this.model, this.texture, poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, p_117358_, 1.0F, 1.0F, 1.0F);
	}
	
	public void coloredGlowingModelCopyLayerRender(M model1, M model2, ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float p_117372_, float r, float g, float b)
	{
		if(!entity.isInvisible())
		{
			model1.copyPropertiesTo(model2);
			model2.prepareMobModel(entity, limbSwing, limbSwingAmount, p_117372_);
			this.renderColoredGlowingModel(model2, texture, poseStack, bufferSource, packedLight, entity, r, g, b);
		}
	}

	public void renderColoredGlowingModel(M model, ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float r, float g, float b)
	{
		VertexConsumer consumer = bufferSource.getBuffer(this.getRenderType(texture));
		int overlayCoords = entity instanceof LivingEntity living ? LivingEntityRenderer.getOverlayCoords(living, 0.0F) : OverlayTexture.NO_OVERLAY;
		model.renderToBuffer(poseStack, consumer, packedLight, overlayCoords, r, g, b, this.getAlpha(entity));
	}
	
	public RenderType getRenderType(ResourceLocation texture)
	{
		return UnleashedRenderType.eyesFix(texture);
	}
	
	public float getAlpha(T entity)
	{
		return 1.0F;
	}
}

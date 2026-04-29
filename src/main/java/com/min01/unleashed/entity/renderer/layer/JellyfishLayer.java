package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.entity.living.CelestialJellyfishEntity;
import com.min01.unleashed.entity.model.JellyfishModel;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

public class JellyfishLayer extends GlowingLayer<CelestialJellyfishEntity, JellyfishModel>
{
	public JellyfishLayer(RenderLayerParent<CelestialJellyfishEntity, JellyfishModel> renderer, JellyfishModel model, ResourceLocation texture) 
	{
		super(renderer, model, texture);
	}

	@Override
	public void renderColoredGlowingModel(JellyfishModel model, ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CelestialJellyfishEntity entity, float r, float g, float b) 
	{
		super.renderColoredGlowingModel(model, texture, poseStack, bufferSource, packedLight, entity, 0.5F, 0.5F, 0.5F);
	}
}

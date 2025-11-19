package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.model.ModelCelestialJellyfish;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

public class CelestialJellyfishLayer extends GlowingLayer<EntityCelestialJellyfish, ModelCelestialJellyfish>
{
	public CelestialJellyfishLayer(RenderLayerParent<EntityCelestialJellyfish, ModelCelestialJellyfish> renderer, ModelCelestialJellyfish model, ResourceLocation texture) 
	{
		super(renderer, model, texture);
	}

	@Override
	public void renderColoredGlowingModel(ModelCelestialJellyfish model, ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, EntityCelestialJellyfish entity, float r, float g, float b) 
	{
		if(entity.isAlive())
		{
			super.renderColoredGlowingModel(model, texture, poseStack, bufferSource, packedLight, entity, 0.3F, 0.3F, 0.3F);
		}
	}
}

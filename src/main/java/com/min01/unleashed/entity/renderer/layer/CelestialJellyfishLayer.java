package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.entity.living.CelestialJellyfishEntity;
import com.min01.unleashed.entity.model.CelestialJellyfishModel;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

public class CelestialJellyfishLayer extends GlowingLayer<CelestialJellyfishEntity, CelestialJellyfishModel>
{
	public CelestialJellyfishLayer(RenderLayerParent<CelestialJellyfishEntity, CelestialJellyfishModel> renderer, CelestialJellyfishModel model, ResourceLocation texture) 
	{
		super(renderer, model, texture);
	}

	@Override
	public void renderColoredGlowingModel(CelestialJellyfishModel model, ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CelestialJellyfishEntity entity, float r, float g, float b) 
	{
		if(entity.isAlive())
		{
			super.renderColoredGlowingModel(model, texture, poseStack, bufferSource, packedLight, entity, 0.3F, 0.3F, 0.3F);
		}
	}
}

package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.model.ModelJellyfish;
import com.min01.unleashed.entity.renderer.layer.JellyfishLayer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JellyfishRenderer extends MobRenderer<EntityCelestialJellyfish, ModelJellyfish>
{
	public JellyfishRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelJellyfish(p_174304_.bakeLayer(ModelJellyfish.LAYER_LOCATION)), 0.0F);
		this.addLayer(new JellyfishLayer(this, this.model, new ResourceLocation(BossesUnleashed.MODID, "textures/entity/jellyfish.png")));
	}
	
	@Override
	protected void scale(EntityCelestialJellyfish p_114046_, PoseStack p_114047_, float p_114048_)
	{
		float f = p_114046_.getSwelling(p_114048_);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		p_114047_.scale(f2, f3, f2);
	}
	
	@Override
	protected int getBlockLightLevel(EntityCelestialJellyfish p_114496_, BlockPos p_114497_)
	{
		return 15;
	}
	
	@Override
	protected RenderType getRenderType(EntityCelestialJellyfish p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCelestialJellyfish p_115812_)
	{
		return new ResourceLocation(BossesUnleashed.MODID, "textures/entity/jellyfish.png");
	}
}

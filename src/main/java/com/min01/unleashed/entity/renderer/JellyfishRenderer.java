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
	public JellyfishRenderer(Context pContext) 
	{
		super(pContext, new ModelJellyfish(pContext.bakeLayer(ModelJellyfish.LAYER_LOCATION)), 0.0F);
		this.addLayer(new JellyfishLayer(this, this.model, ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/jellyfish.png")));
	}
	
	@Override
	protected void scale(EntityCelestialJellyfish pLivingEntity, PoseStack pPoseStack, float pPartialTickTime)
	{
		float f = pLivingEntity.getSwelling(pPartialTickTime);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		pPoseStack.scale(f2, f3, f2);
	}
	
	@Override
	protected int getBlockLightLevel(EntityCelestialJellyfish pEntity, BlockPos pPos) 
	{
		return 15;
	}
	
	@Override
	protected RenderType getRenderType(EntityCelestialJellyfish pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(pLivingEntity));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCelestialJellyfish pEntity)
	{
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/jellyfish.png");
	}
}

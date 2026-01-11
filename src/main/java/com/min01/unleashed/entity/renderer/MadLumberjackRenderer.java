package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.EntityMadLumberjack;
import com.min01.unleashed.entity.model.ModelMadLumberjack;
import com.min01.unleashed.entity.renderer.layer.MadLumberjackLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MadLumberjackRenderer extends MobRenderer<EntityMadLumberjack, ModelMadLumberjack>
{
	public MadLumberjackRenderer(Context pContext) 
	{
		super(pContext, new ModelMadLumberjack(pContext.bakeLayer(ModelMadLumberjack.LAYER_LOCATION)), 0.5F);
		this.addLayer(new MadLumberjackLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMadLumberjack pEntity) 
	{
		if(pEntity.getPhase() >= 2)
		{
			return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/infected_brute.png");
		}
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/mad_lumberjack.png");
	}
}

package com.min01.unleashed.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NoneRenderer<T extends Entity> extends EntityRenderer<T>
{
	public NoneRenderer(Context p_174008_)
	{
		super(p_174008_);
	}
	
	@Override
	public void render(T p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		
	}

	@Override
	public ResourceLocation getTextureLocation(T p_114482_)
	{
		return null;
	}
}

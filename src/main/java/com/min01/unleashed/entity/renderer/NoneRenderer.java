package com.min01.unleashed.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NoneRenderer<T extends Entity> extends EntityRenderer<T>
{
	public NoneRenderer(Context pContext) 
	{
		super(pContext);
	}
	
	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		
	}
	
	@Override
	public ResourceLocation getTextureLocation(T pEntity)
	{
		return null;
	}
}

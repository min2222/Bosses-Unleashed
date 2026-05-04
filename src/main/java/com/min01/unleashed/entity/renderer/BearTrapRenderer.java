package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.model.BearTrapModel;
import com.min01.unleashed.entity.projectile.BearTrapEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BearTrapRenderer extends EntityRenderer<BearTrapEntity>
{
	private final BearTrapModel model;
	public BearTrapRenderer(Context pContext) 
	{
		super(pContext);
		this.model = new BearTrapModel(pContext.bakeLayer(BearTrapModel.LAYER_LOCATION));
	}
	
	@Override
	public void render(BearTrapEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		float yRot = Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot());
		
		pPoseStack.pushPose();
		pPoseStack.mulPose(Axis.YP.rotationDegrees(yRot));
		pPoseStack.scale(-1.0F, -1.0F, 1.0F);
		pPoseStack.translate(0.0F, -1.5F, 0.0F);
		this.model.setupAnim(pEntity, 0, 0, pPartialTicks + pEntity.tickCount, 0, 0);
		this.model.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		pPoseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(BearTrapEntity pEntity) 
	{
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/bear_trap.png");
	}
}

package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.model.CelestialOrbModel;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CelestialOrbRenderer extends EntityRenderer<EntityCelestialOrb>
{
	private final CelestialOrbModel model;
	public CelestialOrbRenderer(Context pContext) 
	{
		super(pContext);
		this.model = new CelestialOrbModel(pContext.bakeLayer(CelestialOrbModel.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityCelestialOrb pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		float xRot = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
		float yRot = Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot());
		
		pPoseStack.pushPose();
		pPoseStack.mulPose(Axis.YP.rotationDegrees(yRot));
		pPoseStack.mulPose(Axis.XP.rotationDegrees(xRot));
		pPoseStack.scale(-1.0F, -1.0F, 1.0F);
		pPoseStack.scale(3.0F, 3.0F, 3.0F);
		pPoseStack.translate(0.0F, -1.5F, 0.0F);
		this.model.renderToBuffer(pPoseStack, pBuffer.getBuffer(UnleashedRenderType.eyesNoAlpha(this.getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		pPoseStack.popPose();
		
		if(pEntity.hasTrail())
		{
			pPoseStack.pushPose();
			Vec3 pos = pEntity.getPosition(pPartialTicks);
			pPoseStack.translate(-pos.x, -pos.y + 0.5F, -pos.z);
	        Vec3 color = Vec3.fromRGB24(8364799);
	        UnleashedClientUtil.renderTrail(pEntity, pPartialTicks, pPoseStack, pBuffer, (float)color.x, (float)color.y, (float)color.z, 0.8F, 8, 0.8F);
	        pPoseStack.popPose();
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCelestialOrb pEntity) 
	{
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/celestial_orb.png");
	}
}

package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.CelestialJellyfishEntity;
import com.min01.unleashed.entity.model.CelestialJellyfishModel;
import com.min01.unleashed.entity.renderer.layer.CelestialJellyfishLayer;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.min01.unleashed.util.UnleashedUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishRenderer extends MobRenderer<CelestialJellyfishEntity, CelestialJellyfishModel>
{
	private final JellyfishRenderer renderer;
	
	public CelestialJellyfishRenderer(Context pContext)
	{
		super(pContext, new CelestialJellyfishModel(pContext.bakeLayer(CelestialJellyfishModel.LAYER_LOCATION)), 0.0F);
		this.addLayer(new CelestialJellyfishLayer(this, this.model, ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/celestial_jellyfish.png")));
		this.renderer = new JellyfishRenderer(pContext);
	}
	
	@Override
	public void render(CelestialJellyfishEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		if(pEntity.isTransform())
		{
			pEntity.afterImage.render(pPoseStack, pBuffer, RenderType.eyes(this.getTextureLocation(pEntity)), pPackedLight, pPartialTicks, 0.5F);
			pEntity.afterImage.render(pPoseStack, pBuffer, RenderType.entityTranslucent(this.getTextureLocation(pEntity)), pPackedLight, pPartialTicks, 0.5F);
			super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
			
			if(pEntity.showWarning())
			{
		        double y = Mth.lerp((double)pPartialTicks, pEntity.yOld, pEntity.getY());
		        float scale = (float) pEntity.getBoundingBox().inflate(1.85F).getSize() / 2.0F;
		    	Vec3 camPos = UnleashedClientUtil.MC.gameRenderer.getMainCamera().getPosition();
		        pPoseStack.pushPose();
                BlockPos groundPos = UnleashedUtil.getGroundPos(pEntity.level, pEntity.getX(), pEntity.getY(), pEntity.getZ()).above();
                pPoseStack.translate(0, -(y - camPos.y), 0);
                pPoseStack.translate(0, groundPos.getY() - camPos.y, 0);
                pPoseStack.translate(0, 0.01F, 0);
                pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.getYHeadRot())));
                pPoseStack.scale(1, scale * 2.0F, 1);
                pPoseStack.translate(0, scale, 0);
                UnleashedClientUtil.drawQuad(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/misc/white.png"))), scale, pPackedLight, new Vec3(1.0F, 0.0F, 0.0F), 0.5F);
                pPoseStack.popPose();
			}
		}
		else
		{
			this.renderer.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
		}
	}
	
	@Override
	protected int getBlockLightLevel(CelestialJellyfishEntity pEntity, BlockPos pPos) 
	{
		return 15;
	}
	
	@Override
	protected RenderType getRenderType(CelestialJellyfishEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(pLivingEntity));
	}
	
	@Override
	public ResourceLocation getTextureLocation(CelestialJellyfishEntity pEntity)
	{
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/celestial_jellyfish.png");
	}
}

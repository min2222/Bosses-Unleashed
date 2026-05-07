package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.entity.model.MadLumberjackModel;
import com.min01.unleashed.entity.renderer.layer.MadLumberjackLayer;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdatePosArrayPacket;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class MadLumberjackRenderer extends MobRenderer<MadLumberjackEntity, MadLumberjackModel>
{
	public MadLumberjackRenderer(Context pContext) 
	{
		super(pContext, new MadLumberjackModel(pContext.bakeLayer(MadLumberjackModel.LAYER_LOCATION)), 1.5F);
		this.addLayer(new MadLumberjackLayer(this));
	}
	
	@Override
	public void render(MadLumberjackEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
		Vec3 axePos = UnleashedClientUtil.getWorldPosition(pEntity, this.model.root(), new Vec3(0.0F, pEntity.yBodyRot, 0.0F), "bone", "bone2", "bone3", "bone9", "bone10", "bone11", "axe", "axePos");
		Vec3 leftHandPos = UnleashedClientUtil.getWorldPosition(pEntity, this.model.root(), new Vec3(0.0F, pEntity.yBodyRot, 0.0F), "bone", "bone2", "bone3", "bone6", "bone7", "bone8");
		Vec3 rightHandPos = UnleashedClientUtil.getWorldPosition(pEntity, this.model.root(), new Vec3(0.0F, pEntity.yBodyRot, 0.0F), "bone", "bone2", "bone3", "bone9", "bone10", "bone11");

		pEntity.posArray[0] = axePos;
		pEntity.posArray[1] = leftHandPos;
		pEntity.posArray[2] = rightHandPos;
		UnleashedNetwork.sendToServer(new UpdatePosArrayPacket(pEntity.getUUID(), axePos, 0));
		UnleashedNetwork.sendToServer(new UpdatePosArrayPacket(pEntity.getUUID(), leftHandPos, 1));
		UnleashedNetwork.sendToServer(new UpdatePosArrayPacket(pEntity.getUUID(), rightHandPos, 2));
		
		if(pEntity.isAnimationPlaying(4))
		{
			boolean flag = pEntity.getAnimationTick() <= 40 && pEntity.getAnimationTick() >= 37;
			boolean flag1 = pEntity.getAnimationTick() <= 22 && pEntity.getAnimationTick() >= 19;
			int startTick = flag ? 40 : 22;
			if(flag || flag1)
			{
				pPoseStack.pushPose();
				pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - Mth.lerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot)));
				pPoseStack.scale(-1.0F, -1.0F, 1.0F);
				pPoseStack.translate(0.0F, -1.5F, 0.0F);
				this.model.root.translateAndRotate(pPoseStack);
				this.model.bone.translateAndRotate(pPoseStack);
				this.model.bone2.translateAndRotate(pPoseStack);
				this.model.bone3.translateAndRotate(pPoseStack);
				this.model.bone9.translateAndRotate(pPoseStack);
				this.model.bone10.translateAndRotate(pPoseStack);
				this.model.bone11.translateAndRotate(pPoseStack);
				this.model.shotgun.translateAndRotate(pPoseStack);
				pPoseStack.translate(0.0F, 4.0F, -0.5F);
				UnleashedClientUtil.drawQuad(pPoseStack, pBuffer.getBuffer(UnleashedRenderType.eyesNoAlpha(this.getShotgunFireTexture(pEntity, startTick))), 1.5F, LightTexture.FULL_BRIGHT, Vec3.fromRGB24(16777215), 1.0F);
				pPoseStack.scale(1.0F, -1.0F, -1.0F);
				pPoseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
				UnleashedClientUtil.drawQuad(pPoseStack, pBuffer.getBuffer(UnleashedRenderType.eyesNoAlpha(this.getShotgunFireTexture(pEntity, startTick))), 1.5F, LightTexture.FULL_BRIGHT, Vec3.fromRGB24(16777215), 1.0F);
				pPoseStack.popPose();
			}
		}
	}
	
	public ResourceLocation getShotgunFireTexture(MadLumberjackEntity pEntity, int startTick) 
	{
		float passedTick = startTick - pEntity.getAnimationTick();
		float lerp = Mth.lerp((float) passedTick / 3.0F, 0, 3);
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/shotgun_fire" + (int) lerp + ".png");
	}

	@Override
	public ResourceLocation getTextureLocation(MadLumberjackEntity pEntity) 
	{
		if(pEntity.getPhase() >= 2)
		{
			return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/infected_brute.png");
		}
		return ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/mad_lumberjack.png");
	}
}

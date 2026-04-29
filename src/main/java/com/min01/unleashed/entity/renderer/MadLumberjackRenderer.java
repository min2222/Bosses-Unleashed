package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.entity.model.MadLumberjackModel;
import com.min01.unleashed.entity.renderer.layer.MadLumberjackLayer;
import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdatePosArrayPacket;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class MadLumberjackRenderer extends MobRenderer<MadLumberjackEntity, MadLumberjackModel>
{
	public MadLumberjackRenderer(Context pContext) 
	{
		super(pContext, new MadLumberjackModel(pContext.bakeLayer(MadLumberjackModel.LAYER_LOCATION)), 0.5F);
		this.addLayer(new MadLumberjackLayer(this));
	}
	
	@Override
	public void render(MadLumberjackEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) 
	{
		super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
		Vec3 axePos = UnleashedClientUtil.getWorldPosition(pEntity, this.model.root(), new Vec3(0.0F, pEntity.yBodyRot, 0.0F), "bone", "bone2", "bone3", "bone9", "bone10", "bone11", "axe", "axePos");
		pEntity.posArray[0] = axePos;
		UnleashedNetwork.sendToServer(new UpdatePosArrayPacket(pEntity.getUUID(), axePos, 0));
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

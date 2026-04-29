package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.entity.model.MadLumberjackModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MadLumberjackLayer extends RenderLayer<MadLumberjackEntity, MadLumberjackModel>
{
	public MadLumberjackLayer(RenderLayerParent<MadLumberjackEntity, MadLumberjackModel> renderer)
	{
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, MadLumberjackEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if(entity.getPhase() >= 2)
		{
			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/infected_brute.png");
			RenderType cutout = RenderType.entityCutoutNoCull(texture);
			this.coloredGlowingModelCopyLayerRender(this.getParentModel(), this.getParentModel(), cutout, poseStack, bufferIn, packedLightIn, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, 1.0F, 1.0F, 1.0F, 1.0F);
			
			poseStack.pushPose();
			poseStack.scale(1.001F, 1.001F, 1.001F);
			
			ResourceLocation alphaTexture = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/mad_lumberjack_alpha.png");
			RenderType alpha = RenderType.dragonExplosionAlpha(alphaTexture);
			this.coloredGlowingModelCopyLayerRender(this.getParentModel(), this.getParentModel(), alpha, poseStack, bufferIn, packedLightIn, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, 1.0F, 1.0F, 1.0F, 0.5F);
			
			ResourceLocation decalTexture = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/entity/mad_lumberjack.png");
			RenderType decal = RenderType.entityDecal(decalTexture);
			this.coloredGlowingModelCopyLayerRender(this.getParentModel(), this.getParentModel(), decal, poseStack, bufferIn, packedLightIn, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();
		}
	}
	
	public void coloredGlowingModelCopyLayerRender(MadLumberjackModel model1, MadLumberjackModel model2, RenderType renderType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, MadLumberjackEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float r, float g, float b, float a)
	{
		if(!entity.isInvisible())
		{
			model1.copyPropertiesTo(model2);
			model2.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
			this.renderColoredGlowingModel(model2, renderType, poseStack, bufferSource, packedLight, entity, r, g, b, a);
		}
	}

	public void renderColoredGlowingModel(MadLumberjackModel model, RenderType renderType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, MadLumberjackEntity entity, float r, float g, float b, float a)
	{
		VertexConsumer consumer = bufferSource.getBuffer(renderType);
		int overlayCoords = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
		model.renderToBuffer(poseStack, consumer, packedLight, overlayCoords, r, g, b, a);
	}
}

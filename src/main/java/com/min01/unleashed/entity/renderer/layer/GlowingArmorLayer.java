package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public abstract class GlowingArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends HumanoidArmorLayer<T, M, A>
{
	private final A innerModel;
	private final A outerModel;
	public final ResourceLocation armorLocation;
	public final ResourceLocation layerLocation;
	   
	public GlowingArmorLayer(RenderLayerParent<T, M> renderer, A innerModel, A outerModel, String armorLocation, String layerLocation) 
	{
		super(renderer, innerModel, outerModel, UnleashedClientUtil.MC.getModelManager());
		this.innerModel = innerModel;
		this.outerModel = outerModel;
		this.armorLocation = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, armorLocation);
		this.layerLocation = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, layerLocation);
	}
	
	@Override
	public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
	{
		this.renderArmorPiece(pPoseStack, pBuffer, pLivingEntity, EquipmentSlot.CHEST, pPackedLight, this.getArmorModel(EquipmentSlot.CHEST));
		this.renderArmorPiece(pPoseStack, pBuffer, pLivingEntity, EquipmentSlot.LEGS, pPackedLight, this.getArmorModel(EquipmentSlot.LEGS));
		this.renderArmorPiece(pPoseStack, pBuffer, pLivingEntity, EquipmentSlot.FEET, pPackedLight, this.getArmorModel(EquipmentSlot.FEET));
		this.renderArmorPiece(pPoseStack, pBuffer, pLivingEntity, EquipmentSlot.HEAD, pPackedLight, this.getArmorModel(EquipmentSlot.HEAD));
	}
	
	public abstract void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel);
	
	public void render(ItemStack stack, PoseStack poseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel)
	{
		ArmorItem armor = (ArmorItem) stack.getItem();
		if(armor.getEquipmentSlot() == pSlot) 
		{
			this.getParentModel().copyPropertiesTo(pModel);
			this.setPartVisibility(pModel, pSlot);
			Model model = this.getArmorModelHook(pLivingEntity, stack, pSlot,  pModel);
			this.renderModel(poseStack, pBuffer, pPackedLight, model, 1.0F, 1.0F, 1.0F, this.armorLocation, this.layerLocation);
	        if(stack.hasFoil()) 
	        {
	        	this.renderGlint(poseStack, pBuffer, pPackedLight, model);
	        }
		}
	}
	
	public boolean usesInnerModel(EquipmentSlot slot) 
	{
		return slot == EquipmentSlot.LEGS;
	}
	
	public A getArmorModel(EquipmentSlot slot) 
	{
		return (A)(this.usesInnerModel(slot) ? this.innerModel : this.outerModel);
	}
	
	private void renderGlint(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, net.minecraft.client.model.Model pModel) 
	{
		pModel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.armorEntityGlint()), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public void renderModel(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, net.minecraft.client.model.Model pModel, float pRed, float pGreen, float pBlue, ResourceLocation armorResource, ResourceLocation eyeResource)
	{
		VertexConsumer consumer = pBuffer.getBuffer(RenderType.entityTranslucent(armorResource));
		pModel.renderToBuffer(pPoseStack, consumer, pPackedLight, OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
		
		VertexConsumer eyeConsumer = pBuffer.getBuffer(UnleashedRenderType.eyesFix(eyeResource));
		pModel.renderToBuffer(pPoseStack, eyeConsumer, LightTexture.FULL_BLOCK, OverlayTexture.NO_OVERLAY, pRed, pGreen, pBlue, 1.0F);
	}
}
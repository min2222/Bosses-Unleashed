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
		this.armorLocation = new ResourceLocation(BossesUnleashed.MODID, armorLocation);
		this.layerLocation = new ResourceLocation(BossesUnleashed.MODID, layerLocation);
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117097_, int p_117098_, T p_117099_, float p_117100_, float p_117101_, float p_117102_, float p_117103_, float p_117104_, float p_117105_) 
	{
		this.renderArmorPiece(poseStack, p_117097_, p_117099_, EquipmentSlot.CHEST, p_117098_, this.getArmorModel(EquipmentSlot.CHEST));
		this.renderArmorPiece(poseStack, p_117097_, p_117099_, EquipmentSlot.LEGS, p_117098_, this.getArmorModel(EquipmentSlot.LEGS));
		this.renderArmorPiece(poseStack, p_117097_, p_117099_, EquipmentSlot.FEET, p_117098_, this.getArmorModel(EquipmentSlot.FEET));
		this.renderArmorPiece(poseStack, p_117097_, p_117099_, EquipmentSlot.HEAD, p_117098_, this.getArmorModel(EquipmentSlot.HEAD));
	}
	
	public abstract void renderArmorPiece(PoseStack poseStack, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_);
	
	public void render(ItemStack stack, PoseStack poseStack, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_)
	{
		ArmorItem armoritem = (ArmorItem) stack.getItem();
		if(armoritem.getEquipmentSlot() == p_117122_) 
		{
			this.getParentModel().copyPropertiesTo(p_117124_);
			this.setPartVisibility(p_117124_, p_117122_);
			Model model = this.getArmorModelHook(p_117121_, stack, p_117122_, p_117124_);
			this.renderModel(poseStack, p_117120_, p_117123_, model, 1.0F, 1.0F, 1.0F, this.armorLocation, this.layerLocation);
	        if(stack.hasFoil()) 
	        {
	        	this.renderGlint(poseStack, p_117120_, p_117123_, model);
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
	
	public void renderGlint(PoseStack p_289673_, MultiBufferSource p_289654_, int p_289649_, net.minecraft.client.model.Model p_289659_)
	{
		p_289659_.renderToBuffer(p_289673_, p_289654_.getBuffer(RenderType.armorEntityGlint()), p_289649_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource, ResourceLocation eyeResource)
	{
		VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(armorResource));
		p_117112_.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, p_117114_, p_117115_, p_117116_, 1.0F);
		
		VertexConsumer eyeConsumer = bufferSource.getBuffer(UnleashedRenderType.eyesFix(eyeResource));
		p_117112_.renderToBuffer(poseStack, eyeConsumer, LightTexture.FULL_BLOCK, OverlayTexture.NO_OVERLAY, p_117114_, p_117115_, p_117116_, 1.0F);
	}
}
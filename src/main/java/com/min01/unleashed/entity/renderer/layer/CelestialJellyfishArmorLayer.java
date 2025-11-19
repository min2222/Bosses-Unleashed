package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.item.CelestialJellyfishArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CelestialJellyfishArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends GlowingArmorLayer<T, M, A>
{
	public CelestialJellyfishArmorLayer(RenderLayerParent<T, M> renderer, A innerModel, A outerModel) 
	{
		super(renderer, innerModel, outerModel, "textures/armor/celestial_jellyfish_armor.png", "textures/armor/celestial_jellyfish_armor.png");
	}
	
	@Override
	public void renderArmorPiece(PoseStack poseStack, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_) 
	{
		ItemStack stack = p_117121_.getItemBySlot(p_117122_);
		if(stack.getItem() instanceof CelestialJellyfishArmorItem) 
		{
			this.render(stack, poseStack, p_117120_, p_117121_, p_117122_, p_117123_, p_117124_);
		}
	}
	
	@Override
	public void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Model p_117112_, float p_117114_, float p_117115_, float p_117116_, ResourceLocation armorResource, ResourceLocation eyeResource) 
	{
		super.renderModel(poseStack, bufferSource, packedLight, p_117112_, 0.8F, 0.8F, 0.8F, armorResource, eyeResource);
	}
}
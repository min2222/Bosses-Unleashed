package com.min01.unleashed.entity.renderer.layer;

import com.min01.unleashed.item.CelestialJellyfishArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
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
	public void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel) 
	{
		ItemStack stack = pLivingEntity.getItemBySlot(pSlot);
		if(stack.getItem() instanceof CelestialJellyfishArmorItem) 
		{
			this.render(stack, pPoseStack, pBuffer, pLivingEntity, pSlot, pPackedLight, pModel);
		}
	}
	
	@Override
	public void renderModel(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, net.minecraft.client.model.Model pModel, float pRed, float pGreen, float pBlue, ResourceLocation armorResource, ResourceLocation eyeResource)
	{
		super.renderModel(pPoseStack, pBuffer, pPackedLight, pModel, 0.8F, 0.8F, 0.8F, armorResource, eyeResource);
	}
}
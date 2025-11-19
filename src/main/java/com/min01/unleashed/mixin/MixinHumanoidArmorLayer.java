package com.min01.unleashed.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.min01.unleashed.item.IGlowingArmor;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(HumanoidArmorLayer.class)
public class MixinHumanoidArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>>
{
	@Inject(at = @At("HEAD"), method = "renderArmorPiece", cancellable = true)
	private void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, T p_117121_, EquipmentSlot p_117122_, int p_117123_, A p_117124_, CallbackInfo ci)
	{
		ItemStack stack = p_117121_.getItemBySlot(p_117122_);
		if(stack.getItem() instanceof IGlowingArmor)
		{
			ci.cancel();
		}
	}
}
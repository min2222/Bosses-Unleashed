package com.min01.unleashed.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IShaderEffectItem
{
	default String getEffetName(LivingEntity entity, ItemStack stack)
	{
		return "";
	}
	
	default boolean shouldApplyEffect(LivingEntity entity, ItemStack stack)
	{
		return true;
	}
	
	default int getEffectTickCount(LivingEntity entity, ItemStack stack)
	{
		return 0;
	}
	
	default float getEffectScale(LivingEntity entity, ItemStack stack)
	{
		return 0;
	}
}

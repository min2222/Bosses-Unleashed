package com.min01.unleashed.capabilities;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

@AutoRegisterCapability
public interface IDashCapability extends ICapabilitySerializable<CompoundTag>
{
	ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "dash");
	
	void tick(LivingEntity entity);
	
	boolean isDash();
	
	void setDashTick(int tick);
	
	int getDashTick();
}

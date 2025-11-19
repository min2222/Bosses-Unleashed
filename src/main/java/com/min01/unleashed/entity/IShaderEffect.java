package com.min01.unleashed.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public interface IShaderEffect 
{
	default String getEffetName()
	{
		return "";
	}
	
	default boolean shouldApplyEffect()
	{
		return true;
	}
	
	default boolean cameraRotation()
	{
		return false;
	}
	
	default Vec3 getEffectPosition(Entity entity)
	{
		return entity.position();
	}
	
	default int getEffectTickCount()
	{
		return 0;
	}
	
	default float getEffectScale()
	{
		return 0;
	}
	
	default float getOffset()
	{
		return 0;
	}
}

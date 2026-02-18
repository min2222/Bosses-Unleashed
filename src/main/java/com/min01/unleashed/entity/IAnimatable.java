package com.min01.unleashed.entity;

import net.minecraft.world.phys.Vec3;

public interface IAnimatable
{
	void setAnimationPlaying(boolean value);
	
	boolean isAnimationPlaying();
	
	void setAnimationTick(int value);
	
	int getAnimationTick();
	
	void setStopMoveTick(int value);
	
	int getStopMoveTick();
	
	boolean canMove();
	
	void setStopLookTick(int value);
	
	int getStopLookTick();
	
	boolean canLook();
	
	void moveToTarget();

	void lookAtTarget();
	
	Vec3[] getPosArray();
	
	default float maxSwimTurnX()
	{
		return 85.0F;
	}
	
	default float maxSwimTurnY()
	{
		return 10.0F;
	}
	
	default float maxFlyTurnX()
	{
		return 85.0F;
	}
	
	default float maxFlyTurnY()
	{
		return 10.0F;
	}
	
	default float maxMoveTurnY()
	{
		return 90.0F;
	}
	
	default float maxBodyTurnY()
	{
		return 75.0F;
	}
}

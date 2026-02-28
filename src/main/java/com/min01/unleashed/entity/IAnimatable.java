package com.min01.unleashed.entity;

import net.minecraft.world.phys.Vec2;
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
	
	default int getMoveInterval()
	{
		return 60;
	}
	
	default int getSwimInterval()
	{
		return 60;
	}
	
	default int getFlyInterval()
	{
		return 60;
	}
	
	default Vec2 getMoveRadius()
	{
		return new Vec2(10, 7);
	}
	
	default Vec2 getSwimRadius()
	{
		return new Vec2(10, 7);
	}
	
	default Vec2 getFlyRadius()
	{
		return new Vec2(10, 7);
	}
	
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

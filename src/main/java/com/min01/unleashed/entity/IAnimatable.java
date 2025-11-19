package com.min01.unleashed.entity;

public interface IAnimatable
{
	void setUsingSkill(boolean value);
	
	boolean isUsingSkill();
	
	void setAnimationTick(int value);
	
	int getAnimationTick();
	
	boolean canMove();
	
	void setCanMove(boolean value);
}

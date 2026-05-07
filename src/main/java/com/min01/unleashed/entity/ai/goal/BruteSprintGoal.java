package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.MadLumberjackEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class BruteSprintGoal extends Goal
{
	public final MadLumberjackEntity mob;
	
	public BruteSprintGoal(MadLumberjackEntity mob) 
	{
		this.mob = mob;
	}
	
	@Override
	public void start() 
	{
		this.mob.setSprinting(true);
	}
	
	@Override
	public boolean canUse() 
	{
		if(this.mob.getTarget() == null || !this.mob.getTarget().isAlive())
		{
			return false;
		}
		return this.mob.distanceTo(this.mob.getTarget()) >= 15.0F && this.mob.getPhase() >= 2;
	}
	
	@Override
	public boolean canContinueToUse()
	{
		return this.canUse();
	}
	
	@Override
	public void stop() 
	{
		this.mob.setSprinting(false);
	}
}

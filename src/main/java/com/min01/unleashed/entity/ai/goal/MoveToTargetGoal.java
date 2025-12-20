package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.IAnimatable;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class MoveToTargetGoal<T extends Mob & IAnimatable> extends Goal
{
	private final T mob;
	
	public MoveToTargetGoal(T mob) 
	{
		this.mob = mob;
	}
	
	@Override
	public boolean canUse() 
	{
		return this.mob.canMove() && this.mob.getTarget() != null && this.mob.getTarget().isAlive();
	}
	
	@Override
	public void start() 
	{
		if(this.mob.getTarget() != null)
		{
			this.mob.moveToTarget();
		}
	}
	
	@Override
	public void tick() 
	{
		if(this.mob.getTarget() != null)
		{
			this.mob.moveToTarget();
		}
	}
	
	@Override
	public boolean requiresUpdateEveryTick() 
	{
		return true;
	}
	
	@Override
	public void stop() 
	{
		this.mob.getNavigation().stop();
	}
}

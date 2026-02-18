package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.IAnimatable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public abstract class AbstractAnimationGoal<T extends Mob & IAnimatable> extends Goal
{
	public int skillWarmupDelay;
	public int nextSkillTickCount;
	public final T mob;
	
	public AbstractAnimationGoal(T mob) 
	{
		this.mob = mob;
	}
	
    @Override
    public boolean canUse() 
    {
    	LivingEntity target = this.mob.getTarget();
    	if(target != null && target.isAlive()) 
    	{
    		if(this.mob.isAnimationPlaying())
    		{
    			return false;
    		}
    		else 
    		{
    			return this.mob.tickCount >= this.nextSkillTickCount;
    		}
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    @Override
    public boolean canContinueToUse() 
    {
    	return this.mob.getAnimationTick() > 0;
    }
    
    @Override
    public void start()
    {
    	this.mob.setAggressive(true);
    	this.mob.setAnimationPlaying(true);
    	this.skillWarmupDelay = this.adjustedTickDelay(this.getSkillWarmupTime());
    	this.mob.setAnimationTick(this.getSkillUsingTime());
    	if(this.stopMovingWhenStart())
    	{
        	this.mob.setStopMoveTick(this.getSkillUsingTime());
        	this.mob.getNavigation().stop();
    	}
    }
    
    public boolean stopMovingWhenStart()
    {
    	return true;
    }
    
	@Override
	public void stop()
	{
		this.mob.setStopMoveTick(0);
		this.mob.setAggressive(false);
    	this.mob.setAnimationPlaying(false);
    	this.nextSkillTickCount = this.mob.tickCount + this.getSkillUsingInterval();
	}
	
    @Override
    public void tick() 
    {
    	--this.skillWarmupDelay;
    	if(this.skillWarmupDelay == 0) 
    	{
    		this.performSkill();
    	}
    }

    public void performSkill()
    {
    	
    }

    public int getSkillWarmupTime()
    {
    	return 20;
    }
    
    public abstract int getSkillUsingTime();

    public abstract int getSkillUsingInterval();
}

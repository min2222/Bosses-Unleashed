package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.IAnimatable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public abstract class AbstractAnimationSkillGoal<T extends Mob & IAnimatable> extends Goal
{
	protected int skillWarmupDelay;
	protected int nextSkillTickCount;
	
    @Override
    public boolean canUse() 
    {
    	LivingEntity target = this.getMob().getTarget();
    	if(target != null && target.isAlive()) 
    	{
    		if(this.getMob().isUsingSkill())
    		{
    			return false;
    		}
    		else 
    		{
    			return this.getMob().tickCount >= this.nextSkillTickCount;
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
    	return this.getMob().getAnimationTick() > 0;
    }
    
    @Override
    public void start()
    {
    	if(this.stopMovingWhenStart())
    	{
        	this.getMob().setCanMove(false);
        	this.getMob().getNavigation().stop();
    	}
    	
    	this.getMob().setAggressive(true);
    	this.getMob().setUsingSkill(true);
    	this.skillWarmupDelay = this.adjustedTickDelay(this.getSkillWarmupTime());
    	this.getMob().setAnimationTick(this.getSkillUsingTime());
    	this.nextSkillTickCount = this.getMob().tickCount + this.getSkillUsingInterval();
    }
    
    public boolean stopMovingWhenStart()
    {
    	return true;
    }
    
	@Override
	public void stop()
	{
		if(this.stopMovingWhenStart())
		{
			this.getMob().setCanMove(true);
		}
		this.getMob().setAggressive(false);
    	this.getMob().setUsingSkill(false);
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

    protected abstract void performSkill();

    protected int getSkillWarmupTime()
    {
    	return 20;
    }
    
    protected abstract int getSkillUsingTime();

    protected abstract int getSkillUsingInterval();
    
    public abstract T getMob();
}

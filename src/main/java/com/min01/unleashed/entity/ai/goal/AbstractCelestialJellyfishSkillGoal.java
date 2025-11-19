package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

public abstract class AbstractCelestialJellyfishSkillGoal extends BasicAnimationSkillGoal<EntityCelestialJellyfish>
{
	public AbstractCelestialJellyfishSkillGoal(EntityCelestialJellyfish mob)
	{
		super(mob);
	}
	
	@Override
	public boolean canUse()
	{
		if(this.mob.isFinalPhase())
		{
			return false;
		}
		if(this.mob.getTarget() == null || !this.mob.getTarget().isAlive() || this.mob.isUsingSkill())
		{
			return false;
		}
		if(this.mob.isSecondPhase() && this instanceof CelestialJellyfishDashGoal && this.mob.goal == null)
		{
			return false;
		}
		boolean flag = this.mob.isTransform() && this.mob.getHitTime() <= 0 && !this.mob.isTeleport() && this.mob.getAnimationState() == 0 && this.mob.goal == null;
		return (super.canUse() && flag) || this.mob.goal == this.getClass();
	}
	
	@Override
	public boolean canContinueToUse()
	{
		if(this.mob.getTarget() == null || !this.mob.getTarget().isAlive() || !this.mob.isAlive())
		{
			return false;
		}
		return super.canContinueToUse();
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		if(this.mob.goal == this.getClass())
		{
			this.mob.goal = null;
		}
	}
}

package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

public abstract class AbstractCelestialJellyfishGoal extends AbstractAnimationGoal<EntityCelestialJellyfish>
{
	public AbstractCelestialJellyfishGoal(EntityCelestialJellyfish mob)
	{
		super(mob);
	}
	
	@Override
	public boolean canUse()
	{
		if(this.mob.getAnimationState() == 4 && this.mob.getAnimationTick() <= 1000)
		{
			return false;
		}
		if(!this.mob.isTransform() || this.mob.getPhase() == 3 || !this.mob.isAlive())
		{
			return false;
		}
		return super.canUse() || this.mob.goal == this.getClass();
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		if(this.mob.getTarget() != null && !this.mob.getTarget().isAlive())
		{
			this.nextSkillTickCount = 0;
		}
		if(this.mob.goal == this.getClass())
		{
			this.mob.goal = null;
		}
	}
	
	public void onTeleport()
	{
		
	}
	
	public void onTeleportEnd()
	{
		
	}
}

package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

public class CelestialJellyfishCloneShootOrbGoal extends AbstractCelestialJellyfishGoal
{
	public CelestialJellyfishCloneShootOrbGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.isClone() && this.mob.getOwner() != null;
	}

	@Override
	public void performSkill() 
	{
		this.mob.goal = CelestialJellyfishShootOrbGoal.class;
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
	}

	@Override
	public int getSkillUsingTime() 
	{
		return 60;
	}
	
	@Override
	public int getSkillWarmupTime()
	{
		return 60;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return Integer.MAX_VALUE;
	}
}

package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.CelestialJellyfishEntity;

public class CelestialJellyfishCloneShootOrbGoal extends AbstractCelestialJellyfishGoal
{
	public CelestialJellyfishCloneShootOrbGoal(CelestialJellyfishEntity mob) 
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
		this.mob.setTeleporting(true);
		this.mob.goal = CelestialJellyfishShootOrbGoal.class;
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

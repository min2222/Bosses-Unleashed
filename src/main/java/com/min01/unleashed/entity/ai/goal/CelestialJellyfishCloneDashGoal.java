package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

public class CelestialJellyfishCloneDashGoal extends AbstractCelestialJellyfishSkillGoal
{
	public CelestialJellyfishCloneDashGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(2);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.isClone() && this.mob.getOwner() == null;
	}

	@Override
	protected void performSkill() 
	{
		this.mob.goal = CelestialJellyfishDashGoal.class;
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 60;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 60;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 1000;
	}
}

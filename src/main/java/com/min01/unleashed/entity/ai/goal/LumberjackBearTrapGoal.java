package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.entity.projectile.BearTrapEntity;

public class LumberjackBearTrapGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackBearTrapGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		//TODO deploy animation
	}
	
	@Override
	public boolean stopMovingWhenStart()
	{
		return false;
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() == 1;
	}
	
	@Override
	public void performSkill() 
	{
		BearTrapEntity trap = new BearTrapEntity(UnleashedEntities.BEAR_TRAP.get(), this.mob.level);
		trap.setPos(this.mob.position());
		trap.setOwner(this.mob);
		this.mob.level.addFreshEntity(trap);
	}

	@Override
	public int getSkillUsingTime()
	{
		return 1;
	}
	
	@Override
	public int getSkillWarmupTime() 
	{
		return 1;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 400;
	}
}

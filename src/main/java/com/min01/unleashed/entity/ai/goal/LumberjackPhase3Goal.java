package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.util.UnleashedUtil;

public class LumberjackPhase3Goal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackPhase3Goal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setPhase(2);
		//TODO phase3;
	}
	
	@Override
	public boolean canUse() 
	{
		if(this.mob.isAnimationPlaying())
		{
			return false;
		}
		return this.mob.getPhase() == 1 && this.mob.getHealth() <= UnleashedUtil.percent(this.mob.getMaxHealth(), 40.0F);
	}

	@Override
	public int getSkillUsingTime()
	{
		return 40;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 1000;
	}
}

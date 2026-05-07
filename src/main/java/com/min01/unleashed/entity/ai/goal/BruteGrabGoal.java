package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.util.UnleashedUtil;

public class BruteGrabGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public BruteGrabGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(8);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() >= 2 && this.mob.distanceTo(this.mob.getTarget()) >= 15.0F && this.mob.getRandom().nextInt(4) == 0;
	}
	
	@Override
	public void performSkill() 
	{
		if(this.mob.getTarget() != null)
		{
			UnleashedUtil.dashToward(this.mob, this.mob.distanceTo(this.mob.getTarget()) / 4.0F, 0.25F);
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 40;
	}
	
	@Override
	public int getSkillWarmupTime() 
	{
		return 15;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 20;
	}
}

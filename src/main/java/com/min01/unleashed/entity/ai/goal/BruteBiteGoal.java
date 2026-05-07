package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.living.MadLumberjackEntity;

import net.minecraft.world.damagesource.DamageSource;

public class BruteBiteGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public BruteBiteGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(10);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() >= 2 && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 12)
		{
			if(this.mob.getTarget() != null && this.mob.isWithinMeleeAttackRange(this.mob.getTarget()))
			{
				DamageSource source = this.mob.damageSources().mobAttack(this.mob);
				this.mob.getTarget().hurt(source, 15.0F);
			}
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 20;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 10;
	}
}

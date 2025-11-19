package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.IAnimatable;

import net.minecraft.world.entity.Mob;

public abstract class BasicAnimationSkillGoal<T extends Mob & IAnimatable> extends AbstractAnimationSkillGoal<T>
{
	public T mob;
	
	public BasicAnimationSkillGoal(T mob) 
	{
		this.mob = mob;
	}

	@Override
	public T getMob() 
	{
		return this.mob;
	}
}

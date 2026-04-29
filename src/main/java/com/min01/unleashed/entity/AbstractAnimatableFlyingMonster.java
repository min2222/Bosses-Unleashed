package com.min01.unleashed.entity;

import com.min01.unleashed.misc.MobClassification;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class AbstractAnimatableFlyingMonster extends AbstractAnimatableMonster
{
	public AbstractAnimatableFlyingMonster(EntityType<? extends Monster> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		this.switchControl(this.isFlying());
	}
	
	@Override
	public MobClassification getMobClassification() 
	{
		return MobClassification.AIR;
	}
}

package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.entity.living.MadLumberjackEntity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BruteSweepGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public BruteSweepGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(this.mob.getRandom().nextBoolean() ? 11 : 12);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() >= 2 && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F && this.mob.getRandom().nextInt(2) == 0;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 22)
		{
			int index = this.mob.getAnimationState() == 12 ? 2 : 1;
			Vec3 pos = this.mob.posArray[index];
			if(pos != null)
			{
				float size = 3.0F;
				DamageSource source = this.mob.damageSources().mobAttack(this.mob);
				List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos), t -> t != this.mob && !t.isAlliedTo(this.mob));
				list.forEach(t -> 
				{
					t.hurt(source, 15.0F);
				});
			}
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 30;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 10;
	}
}

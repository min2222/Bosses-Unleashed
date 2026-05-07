package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.entity.CameraShakeEntity;
import com.min01.unleashed.entity.living.MadLumberjackEntity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BruteSmashGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public BruteSmashGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(7);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() >= 2 && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F && this.mob.getRandom().nextInt(6) == 0;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 20)
		{
			Vec3 pos = this.mob.posArray[1];
			if(pos != null)
			{
				float size = 3.0F;
				DamageSource source = this.mob.damageSources().mobAttack(this.mob);
				List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos), t -> t != this.mob && !t.isAlliedTo(this.mob));
				list.forEach(t -> 
				{
					t.hurt(source, 20.0F);
				});
			}
			Vec3 pos1 = this.mob.posArray[2];
			if(pos1 != null)
			{
				float size = 3.0F;
				DamageSource source = this.mob.damageSources().mobAttack(this.mob);
				List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos1), t -> t != this.mob && !t.isAlliedTo(this.mob));
				list.forEach(t -> 
				{
					t.hurt(source, 20.0F);
				});
			}
			CameraShakeEntity.cameraShake(this.mob.level, pos1, 40.0F, 0.1F, 0, 20);
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 40;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 10;
	}
}

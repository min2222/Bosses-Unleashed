package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.entity.CameraShakeEntity;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LumberjackAxeAttack2Goal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackAxeAttack2Goal(MadLumberjackEntity mob) 
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
		return super.canUse() && this.mob.getPhase() < 2 && !this.mob.isGun() && this.mob.distanceTo(this.mob.getTarget()) <= 4.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 58)
		{
			if(this.mob.getTarget() != null && UnleashedUtil.isWithinMeleeAttackRange(this.mob, this.mob.getTarget(), 3.5F))
			{
				this.mob.getTarget().knockback(2.0F, this.mob.getX() - this.mob.getTarget().getX(), this.mob.getZ() - this.mob.getTarget().getZ());
				this.mob.getTarget().hurtMarked = true;
			}
		}
		if(this.mob.getAnimationTick() == 30)
		{
			UnleashedUtil.dashToward(this.mob, 2.5F);
			this.mob.setStopLookTick(this.mob.getAnimationTick());
		}
		if(this.mob.getAnimationTick() == 24)
		{
			Vec3 pos = this.mob.posArray[0];
			if(pos != null)
			{
				float size = 8.0F;
				DamageSource source = this.mob.damageSources().mobAttack(this.mob);
				List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos), t -> t != this.mob && !t.isAlliedTo(this.mob));
				list.forEach(t -> 
				{
					t.hurt(source, 15.0F);
					UnleashedUtil.disableShield(t, source, 40);
				});
				CameraShakeEntity.cameraShake(this.mob.level, pos, 30.0F, 0.05F, 0, 20);
			}
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 70;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 100;
	}
}

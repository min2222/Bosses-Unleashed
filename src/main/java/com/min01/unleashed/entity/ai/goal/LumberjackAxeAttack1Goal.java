package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.entity.CameraShakeEntity;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LumberjackAxeAttack1Goal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackAxeAttack1Goal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(1);
		this.mob.setStopLookTick(this.getSkillUsingTime());
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() < 2 && !this.mob.isGun() && this.mob.distanceTo(this.mob.getTarget()) <= 8.0F && this.mob.distanceTo(this.mob.getTarget()) >= 4.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 30)
		{
			Vec3 pos = this.mob.posArray[0];
			if(pos != null)
			{
				float size = 3.0F;
				List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos), t -> t != this.mob && !t.isAlliedTo(this.mob));
				list.forEach(t -> 
				{
					this.mob.doHurtTarget(t);
					UnleashedUtil.disableShield(t, this.mob.damageSources().mobAttack(this.mob), 20);
				});
				CameraShakeEntity.cameraShake(this.mob.level, pos, 30.0F, 0.05F, 0, 20);
			}
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 60;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 30;
	}
}

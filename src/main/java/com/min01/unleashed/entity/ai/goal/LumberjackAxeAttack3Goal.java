package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class LumberjackAxeAttack3Goal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackAxeAttack3Goal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(3);
		this.mob.setStopLookTick(this.getSkillUsingTime());
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() < 2 && !this.mob.isGun() && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.mob.getAnimationTick() == 15)
		{
			Vec3 pos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.mob.yBodyRot), this.mob.position(), 0, 0, 5.0F);
			float size = 3.0F;
			List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, new AABB(-size, -size, -size, size, size, size).move(pos), t -> t != this.mob && !t.isAlliedTo(this.mob));
			list.forEach(t -> 
			{
				this.mob.doHurtTarget(t);
			});
		}
	}

	@Override
	public int getSkillUsingTime()
	{
		return 25;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 20;
	}
}

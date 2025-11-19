package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishSummonDashCloneGoal extends AbstractCelestialJellyfishSkillGoal
{
	public boolean canContinueToUse = true;
	public int cloneTick;
	
	public CelestialJellyfishSummonDashCloneGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(3);
		this.mob.doTeleport();
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.isSecondPhase() && !this.mob.isClone();
	}
	
	@Override
	public boolean canContinueToUse() 
	{
		return super.canContinueToUse() && this.canContinueToUse;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		--this.cloneTick;
		if(this.cloneTick == 15)
		{
			EntityCelestialJellyfish jellyfish = new EntityCelestialJellyfish(UnleashedEntities.CELESTIAL_JELLYFISH.get(), this.mob.level);
			jellyfish.setClone(true);
			jellyfish.setTransform(true);
			jellyfish.setHitTime(true);
			jellyfish.setHitTime(100);
			jellyfish.setPos(this.mob.position());
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.mob.getYHeadRot()), this.mob.position(), this.mob.getRandom().nextBoolean() ? 2 : -2, 0, 0);
			jellyfish.setDeltaMovement(UnleashedUtil.fromToVector(this.mob.position(), lookPos, 0.2F));
			jellyfish.goal = CelestialJellyfishCloneDashGoal.class;
			this.mob.level.addFreshEntity(jellyfish);
		}
		if(this.cloneTick == 0)
		{
			this.mob.goal = CelestialJellyfishDashGoal.class;
			this.canContinueToUse = false;
		}
		if(this.mob.isMove())
		{
			this.mob.setHitTime(true);
			this.mob.setHitTime(100);
			this.mob.setMove(false);
		}
		if(this.mob.isEnd())
		{
			this.cloneTick = this.adjustedTickDelay(60);
			this.mob.setEnd(false);
		}
	}

	@Override
	protected void performSkill() 
	{
		
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
		this.canContinueToUse = true;
		this.cloneTick = 0;
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 1000;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 20;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 800;
	}
}

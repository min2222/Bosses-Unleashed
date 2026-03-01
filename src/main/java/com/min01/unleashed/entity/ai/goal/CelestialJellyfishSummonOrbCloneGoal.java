package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishSummonOrbCloneGoal extends AbstractCelestialJellyfishGoal
{
	public boolean canContinueToUse = true;
	public int cloneTick;
	public int cloneCount;
	
	public CelestialJellyfishSummonOrbCloneGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(3);
		this.mob.doTeleport();
		this.mob.setInvincible(true);
		this.mob.runningGoal = this.getClass();
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() == 2 && !this.mob.isClone();
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
		if(this.cloneTick == 10)
		{
			EntityCelestialJellyfish jellyfish = new EntityCelestialJellyfish(UnleashedEntities.CELESTIAL_JELLYFISH.get(), this.mob.level);
			jellyfish.setClone(true);
			jellyfish.setTransform(true);
			jellyfish.setAnimationState(4);
			jellyfish.setAnimationTick(Integer.MAX_VALUE);
			jellyfish.setPos(this.mob.position());
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.mob.getYHeadRot()), this.mob.position(), this.mob.getRandom().nextBoolean() ? 2 : -2, 0, 0);
			jellyfish.setDeltaMovement(UnleashedUtil.getVelocityTowards(this.mob.position(), lookPos, 0.2F));
			jellyfish.goal = CelestialJellyfishCloneShootOrbGoal.class;
			jellyfish.setOwner(this.mob);
			jellyfish.setTarget(this.mob.getTarget());
			this.mob.level.addFreshEntity(jellyfish);
		}
		if(this.cloneTick == 0)
		{
			if(this.cloneCount < 5)
			{
				this.mob.doTeleport();
				this.cloneCount++;
			}
			else
			{
				if(this.mob.getHealth() <= this.mob.getMaxHealth() / 2 && this.mob.getRandom().nextBoolean())
				{
					this.mob.goal = CelestialJellyfishDashGoal.class;
				}
				else
				{
					this.mob.goal = CelestialJellyfishShootOrbGoal.class;
				}
				this.canContinueToUse = false;
			}
		}
	}
	
	@Override
	public void onTeleport() 
	{
		this.mob.setAnimationState(4);
		this.mob.setAnimationTick(Integer.MAX_VALUE);
	}
	
	@Override
	public void onTeleportEnd() 
	{
		if(this.cloneCount >= 1)
		{
			this.cloneTick = this.adjustedTickDelay(30);
		}
		else
		{
			this.cloneTick = this.adjustedTickDelay(60);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
		this.mob.runningGoal = null;
		this.canContinueToUse = true;
		this.cloneTick = 0;
		this.cloneCount = 0;
	}

	@Override
	public int getSkillUsingTime() 
	{
		return Integer.MAX_VALUE;
	}
	
	@Override
	public int getSkillWarmupTime()
	{
		return 20;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 400;
	}
}

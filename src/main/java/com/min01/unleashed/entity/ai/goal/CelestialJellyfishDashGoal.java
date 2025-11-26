package com.min01.unleashed.entity.ai.goal;

import java.util.List;

import com.min01.unleashed.config.UnleashedConfig;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishDashGoal extends AbstractCelestialJellyfishSkillGoal
{
	private int dashCount;
	private int dashTick;
	private boolean canContinueToUse = true;
	private boolean isDash;
	
	public CelestialJellyfishDashGoal(EntityCelestialJellyfish mob)
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
		this.mob.setAnimationState(2);
		this.mob.doTeleport();
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getRandom().nextBoolean();
	}
	
	@Override
	public boolean canContinueToUse()
	{
		if(this.mob.getTarget() == null || !this.mob.getTarget().isAlive() || !this.mob.isAlive())
		{
			this.nextSkillTickCount = 0;
			return false;
		}
		return super.canContinueToUse() && this.canContinueToUse;
	}
	
	@Override
	public void tick()
	{
		super.tick();
		--this.dashTick;
		if(this.dashCount == 5)
		{
			this.mob.setFloat(true);
		}
		if(this.dashTick == 0)
		{
			this.mob.doTeleport();
			this.mob.setShowAfterImage(false);
			this.mob.setLastLookPos(Vec3.ZERO);
			this.isDash = false;
		}
		if(this.isDash)
		{
			List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(1.85F), t -> !(t instanceof EntityCelestialJellyfish) && !t.isAlliedTo(this.mob));
			list.forEach(t -> 
			{
				this.mob.doHurtTarget(t);
			});
			if(this.mob.getAnimationTick() % 2 == 0)
			{
				Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.mob.getYHeadRot()), this.mob.position(), 0, 0, -15);
				EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.mob.level);
				orb.setPos(lookPos);
				orb.setOwner(this.mob);
				this.mob.level.addFreshEntity(orb);
			}
		}
		else
		{
			this.mob.setDeltaMovement(Vec3.ZERO);
			if(this.mob.isMove())
			{
				if(this.mob.getTarget() != null)
				{
					this.mob.lookAt(Anchor.EYES, this.mob.getTarget().getEyePosition());
				}
				if(this.dashCount >= 5)
				{
					if(this.mob.isClone())
					{
						this.mob.discard();
					}
					else
					{
						this.mob.setHitTime(true);
						this.mob.setHitTime(this.mob.isSecondPhase() ? UnleashedConfig.phaseTwoDealTime.get() : UnleashedConfig.phaseOneDealTime.get());
					}
				}
				else
				{
		            this.mob.setShowWarning(true);
				}
				this.mob.setMove(false);
			}
			if(this.mob.isEnd())
			{
				this.mob.setLastLookPos(UnleashedUtil.getLookPos(new Vec2(this.mob.getXRot(), this.mob.getYHeadRot()), this.mob.position(), 0, 2, 100));
				if(this.mob.isClone() || this.mob.goal == this.getClass())
				{
					this.skillWarmupDelay = this.adjustedTickDelay(12);
				}
				else
				{
					this.skillWarmupDelay = this.adjustedTickDelay(10);
				}
				this.mob.setEnd(false);
			}
		}
	}

	@Override
	protected void performSkill() 
	{
		this.mob.setShowWarning(false);
		if(this.dashCount < 5)
		{
			this.mob.addDeltaMovement(UnleashedUtil.fromToVector(this.mob.position(), this.mob.getLastLookPos(), 6.0F));
			this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get());
			this.mob.setShowAfterImage(true);
			this.isDash = true;
			this.dashCount++;
			this.dashTick = 10;
		}
		else
		{
			this.canContinueToUse = false;
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
		this.mob.setLastLookPos(Vec3.ZERO);
		this.mob.setShowAfterImage(false);
		this.mob.setShowWarning(false);
		this.mob.setFloat(false);
		this.canContinueToUse = true;
		this.isDash = false;
		this.dashCount = 0;
		this.dashTick = 0;
	}

	@Override
	protected int getSkillUsingTime()
	{
		return 1000;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 1000;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 600;
	}
}

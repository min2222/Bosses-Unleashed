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

public class CelestialJellyfishDashGoal extends AbstractCelestialJellyfishGoal
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
		if(this.mob.isClone())
		{
			this.mob.setAnimationState(4);
			this.mob.setAnimationTick(Integer.MAX_VALUE);
		}
		this.mob.setInvincible(true);
		this.mob.doTeleport();
		this.mob.runningGoal = this.getClass();
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
		--this.dashTick;
		if(this.dashCount == 5)
		{
			this.mob.setTeleportAbove(true);
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
		}
	}

	@Override
	public void performSkill() 
	{
		this.mob.setShowWarning(false);
		if(this.dashCount < 5)
		{
			this.mob.addDeltaMovement(UnleashedUtil.getVelocityTowards(this.mob.position(), this.mob.getLastLookPos(), 6.0F));
			this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get(), 1.5F, 1.0F);
			this.mob.setShowAfterImage(true);
			this.isDash = true;
			this.dashCount++;
			this.dashTick = 10;
		}
		else
		{
			this.mob.setTeleporting(true);
			this.canContinueToUse = false;
		}
	}
	
	@Override
	public void onTeleport()
	{
		if(this.dashCount >= 5)
		{
			if(this.mob.isClone())
			{
				this.mob.discard();
			}
			else
			{
				this.mob.setAnimationState(2);
				this.mob.setAnimationTick(this.mob.getPhase() == 2 ? UnleashedConfig.phaseTwoDealTime.get() : UnleashedConfig.phaseOneDealTime.get());
			}
		}
		else
		{
			if(this.mob.getTarget() != null)
			{
				this.mob.lookAt(Anchor.FEET, this.mob.getTarget().position());
			}
            this.mob.setShowWarning(true);
		}
	}
	
	@Override
	public void onTeleportEnd()
	{
		this.mob.setLastLookPos(UnleashedUtil.getLookPos(new Vec2(this.mob.getXRot(), this.mob.getYHeadRot()), this.mob.position(), 0, 0, 300));
		if(this.mob.isClone() || this.mob.goal == this.getClass())
		{
			this.skillWarmupDelay = this.adjustedTickDelay(12);
		}
		else
		{
			this.skillWarmupDelay = this.adjustedTickDelay(10);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setLastLookPos(Vec3.ZERO);
		this.mob.setInvincible(false);
		this.mob.setShowAfterImage(false);
		this.mob.setShowWarning(false);
		this.mob.runningGoal = null;
		this.canContinueToUse = true;
		this.isDash = false;
		this.dashCount = 0;
		this.dashTick = 0;
	}

	@Override
	public int getSkillUsingTime()
	{
		return 1000;
	}
	
	@Override
	public int getSkillWarmupTime() 
	{
		return 1000;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 400;
	}
}

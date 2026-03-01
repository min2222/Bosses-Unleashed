package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishShootOrbGoal extends AbstractCelestialJellyfishGoal
{
	public float xRot;
	public float yRot;
	public int dir = 1;
	public boolean isShoot;
	public boolean isRewind;
	
	public CelestialJellyfishShootOrbGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		if(!this.mob.isClone())
		{
			this.mob.setAnimationState(3);
		}
		this.mob.setInvincible(true);
		this.mob.doTeleport();
		this.mob.runningGoal = this.getClass();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.isShoot && !this.isRewind)
		{
			if(this.mob.isClone() || this.mob.goal == this.getClass())
			{
				Vec2 rot = new Vec2(this.mob.getXRot(), this.mob.getYHeadRot());
				Vec3 lookPos = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 1);
				Vec3 motion = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 3);
				EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.mob.level);
				orb.setPos(lookPos);
				orb.setDeltaMovement(UnleashedUtil.getVelocityTowards(lookPos, motion, 1.0F));
				orb.setOwner(this.mob);
				orb.setTrail(true);
				this.mob.level.addFreshEntity(orb);
				this.mob.setInvincible(false);
			}
			else
			{
				this.xRot += 5 * this.dir;
				this.yRot += 5;
				this.xRot = Mth.clamp(this.xRot, -45.0F, 65.0F);
				if(this.xRot >= 65.0F)
				{
					this.dir = -1;
					this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get(), 1.5F, 1.0F);
				}
				if(this.xRot <= -45.0F)
				{
					this.dir = 1;
					this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get(), 1.5F, 1.0F);
				}
				for(float i = 22.5F; i <= 360; i += 22.5F)
				{
					int dir = 1;
					float index = i / 22.5F;
					if(index % 2 == 0)
					{
						dir = -1;
					}
					Vec2 rot = new Vec2(this.xRot * dir, i + (this.yRot * dir));
					Vec3 lookPos = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 1);
					Vec3 motion = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 3);
					EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.mob.level);
					orb.setPos(lookPos);
					orb.setDeltaMovement(UnleashedUtil.getVelocityTowards(lookPos, motion, 1.0F));
					orb.setOwner(this.mob);
					orb.setTrail(true);
					this.mob.level.addFreshEntity(orb);
				}
			}
		}
		if(this.skillWarmupDelay == -15)
		{
			if(this.isRewind)
			{
				this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_REWIND.get(), 2.0F, 1.0F);
			}
			this.mob.setAnimationTick(this.getSkillUsingTime() - this.getSkillWarmupTime());
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
		this.isShoot = true;
	}

	@Override
	public void performSkill()
	{
		if(!this.mob.isClone())
		{
			this.mob.explosion();
			this.isRewind = true;
			this.mob.isRewind = true;
		}
	}
	
	@Override
	public void stop()
	{
		super.stop();
		if(this.mob.isClone())
		{
			this.mob.doTeleport();
			this.mob.setDiscard(true);
		}
		this.mob.setAnimationState(0);
		this.mob.setAnimationTick(0);
		this.mob.setInvincible(false);
		this.mob.runningGoal = null;
		this.mob.isRewind = false;
		this.isRewind = false;
		this.isShoot = false;
		this.dir = 1;
		this.xRot = 0.0F;
		this.yRot = 0.0F;
	}

	@Override
	public int getSkillUsingTime()
	{
		return 300;
	}
	
	@Override
	public int getSkillWarmupTime() 
	{
		return 200;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 500;
	}
}

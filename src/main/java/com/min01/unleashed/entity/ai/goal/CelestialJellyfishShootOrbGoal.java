package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishShootOrbGoal extends AbstractCelestialJellyfishSkillGoal
{
	public float xRot;
	public float yRot;
	public int dir = 1;
	public boolean isShoot;
	
	public CelestialJellyfishShootOrbGoal(EntityCelestialJellyfish mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		if(this.mob.goal == null)
		{
			this.mob.setAnimationState(3);
		}
		if(!this.mob.isClone())
		{
			this.mob.doTeleport();
		}
		this.mob.setFloat(true);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getRandom().nextBoolean();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.isShoot && !this.mob.isRewind())
		{
			if(this.mob.isClone() || this.mob.goal == this.getClass())
			{
				Vec2 rot = new Vec2(this.mob.getXRot(), this.mob.getYHeadRot());
				Vec3 lookPos = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 1);
				Vec3 motion = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 3);
				EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.mob.level);
				orb.setPos(lookPos);
				orb.setDeltaMovement(UnleashedUtil.fromToVector(lookPos, motion, 1.0F));
				orb.setOwner(this.mob);
				orb.setTrail(true);
				this.mob.level.addFreshEntity(orb);
			}
			else
			{
				this.xRot += 5 * this.dir;
				this.yRot += 5;
				this.xRot = Mth.clamp(this.xRot, -45.0F, 65.0F);
				if(this.xRot >= 65.0F)
				{
					this.dir = -1;
					this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get());
				}
				if(this.xRot <= -45.0F)
				{
					this.dir = 1;
					this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get());
				}
				for(float i = 11.25F; i <= 360; i += 11.25F)
				{
					int dir = 1;
					float index = i / 11.25F;
					if(index % 2 == 0)
					{
						dir = -1;
					}
					Vec2 rot = new Vec2(this.xRot * dir, i + (this.yRot * dir));
					Vec3 lookPos = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 1);
					Vec3 motion = UnleashedUtil.getLookPos(rot, this.mob.getEyePosition().subtract(0.0F, 0.5F, 0.0F), 0, 0, 3);
					EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.mob.level);
					orb.setPos(lookPos);
					orb.setDeltaMovement(UnleashedUtil.fromToVector(lookPos, motion, 1.0F));
					orb.setOwner(this.mob);
					orb.setTrail(true);
					this.mob.level.addFreshEntity(orb);
				}
			}
		}
		if(this.mob.isMove())
		{
			this.mob.setHitTime(true);
			this.mob.setHitTime(this.getSkillUsingTime());
			this.mob.setMove(false);
		}
		if(this.mob.isEnd() || this.mob.isClone())
		{
			this.isShoot = true;
			this.mob.setEnd(false);
		}
		if(this.mob.isRewind())
		{
			if(this.skillWarmupDelay == -15)
			{
				this.mob.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_REWIND.get());
			}
		}
	}

	@Override
	protected void performSkill()
	{
		if(!this.mob.isClone() && this.mob.goal == null)
		{
			this.mob.explosion();
			this.mob.setRewind(true);
		}
	}
	
	@Override
	public void stop()
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setRewind(false);
		this.mob.setFloat(false);
		if(this.mob.isClone())
		{
			this.mob.doTeleport();
		}
		this.isShoot = false;
		this.dir = 1;
		this.xRot = 0.0F;
		this.yRot = 0.0F;
	}

	@Override
	protected int getSkillUsingTime()
	{
		return 300;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 200;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 800;
	}
}

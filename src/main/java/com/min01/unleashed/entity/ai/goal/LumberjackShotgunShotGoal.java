package com.min01.unleashed.entity.ai.goal;

import com.min01.unleashed.entity.CameraShakeEntity;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.misc.Laser;
import com.min01.unleashed.misc.Laser.LaserHitResult;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class LumberjackShotgunShotGoal extends AbstractAnimationGoal<MadLumberjackEntity>
{
	public LumberjackShotgunShotGoal(MadLumberjackEntity mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		if(!this.mob.isGun())
		{
	    	this.mob.setAnimationState(5);
		}
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.getPhase() == 0 && this.mob.distanceTo(this.mob.getTarget()) >= 10.0F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		int tick = this.mob.isGun() ? 0 : 20;
		if(this.mob.getAnimationTick() == this.getSkillUsingTime() - (10 + tick) || this.mob.getAnimationTick() == this.getSkillUsingTime() - (28 + tick))
		{
			Laser laser = new Laser();
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(this.mob.getXRot(), this.mob.yBodyRot), this.mob.getEyePosition(), 0, 0, 15.0F);
			LaserHitResult laserHit = laser.raytrace(this.mob.level, this.mob.getEyePosition(), lookPos, 5.0F, t -> t != this.mob && !t.isAlliedTo(this.mob), this.mob);
			laserHit.entities.forEach(t -> 
			{
				this.mob.doHurtTarget(t);
			});
			CameraShakeEntity.cameraShake(this.mob.level, this.mob.position(), 30.0F, 0.05F, 0, 20);
		}
	}
	
	@Override
	public void performSkill() 
	{
		this.mob.setAnimationState(4);
	}
	
	@Override
	public void stop()
	{
		super.stop();
		//TODO reload;
		this.mob.changeToAxe();
	}

	@Override
	public int getSkillUsingTime()
	{
		if(!this.mob.isGun())
		{
			return 70;
		}
		return 50;
	}

	@Override
	public int getSkillUsingInterval() 
	{
		return 100;
	}
}

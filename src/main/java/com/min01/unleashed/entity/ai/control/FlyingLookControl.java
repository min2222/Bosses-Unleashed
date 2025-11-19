package com.min01.unleashed.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;

public class FlyingLookControl extends LookControl 
{
	public FlyingLookControl(Mob mob) 
	{
		super(mob);
	}

	@Override
	public void tick() 
	{
		if(this.lookAtCooldown > 0) 
		{
			--this.lookAtCooldown;
			this.getYRotD().ifPresent((p_287449_) ->
			{
				this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, p_287449_ + 20.0F, this.yMaxRotSpeed);
			});
			this.getXRotD().ifPresent((p_289401_) ->
			{
				this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), p_289401_ + 1.0F, this.xMaxRotAngle));
			});
		} 
		else 
		{
			if(this.mob.getNavigation().isDone()) 
			{
				this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), 0.0F, 5.0F));
			}
			this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
		}
		float f = Mth.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
		if(f < -10) 
		{
			this.mob.yBodyRot -= 4.0F;
		}
		else if(f > 10)
		{
			this.mob.yBodyRot += 4.0F;
		}
	}
}
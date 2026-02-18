package com.min01.unleashed.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;

public class FlyingLookControl extends LookControl 
{
	private final int maxYRotFromCenter;
	
	public FlyingLookControl(Mob mob, int maxYRotFromCenter) 
	{
		super(mob);
		this.maxYRotFromCenter = maxYRotFromCenter;
	}

	@Override
	public void tick() 
	{
		if(this.lookAtCooldown > 0) 
		{
			--this.lookAtCooldown;
			this.getYRotD().ifPresent(t ->
			{
				this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, t + 20.0F, this.yMaxRotSpeed);
			});
			this.getXRotD().ifPresent(t ->
			{
				this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), t + 1.0F, this.xMaxRotAngle));
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
		if(f < -this.maxYRotFromCenter) 
		{
			this.mob.yBodyRot -= 4.0F;
		} 
		else if(f > this.maxYRotFromCenter)
		{
			this.mob.yBodyRot += 4.0F;
		}
	}
}
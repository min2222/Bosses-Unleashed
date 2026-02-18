package com.min01.unleashed.entity.ai.control;

import com.min01.unleashed.entity.IAnimatable;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class AnimationBodyRotationControl<T extends Mob & IAnimatable> extends BodyRotationControl 
{
	private final T mob;
	private int headStableTime;
	private float lastStableYHeadRot;

	public AnimationBodyRotationControl(T pMob) 
	{
		super(pMob);
		this.mob = pMob;
	}

	@Override
	public void clientTick()
	{
		if(this.isMoving())
		{
			this.mob.yBodyRot = this.mob.getYRot();
			this.rotateHeadIfNecessary();
			this.lastStableYHeadRot = this.mob.yHeadRot;
			this.headStableTime = 0;
		} 
		else
		{
			if(this.notCarryingMobPassengers())
			{
				if(Math.abs(this.mob.yHeadRot - this.lastStableYHeadRot) > 15.0F)
				{
					this.headStableTime = 0;
					this.lastStableYHeadRot = this.mob.yHeadRot;
					this.rotateHeadTowardsFront();
				} 
				else 
				{
					++this.headStableTime;
					if(this.headStableTime > 10) 
					{
						this.rotateBodyIfNecessary();
					}
				}
			}
		}
	}

	private void rotateBodyIfNecessary() 
	{
		this.mob.yBodyRot = UnleashedUtil.rotlerp(this.mob.yBodyRot, this.mob.yHeadRot, this.mob.maxBodyTurnY());
	}

	private void rotateHeadIfNecessary() 
	{
		this.mob.yHeadRot = UnleashedUtil.rotlerp(this.mob.yHeadRot, this.mob.yBodyRot, this.mob.maxBodyTurnY());
	}

	private void rotateHeadTowardsFront() 
	{
		int i = this.headStableTime - 10;
		float f = Mth.clamp((float) i / 10.0F, 0.0F, 1.0F);
		float f1 = (float) this.mob.maxBodyTurnY() * (1.0F - f);
		this.mob.yBodyRot = UnleashedUtil.rotlerp(this.mob.yBodyRot, this.mob.yHeadRot, f1);
	}

	private boolean notCarryingMobPassengers()
	{
		return !(this.mob.getFirstPassenger() instanceof Mob);
	}

	private boolean isMoving() 
	{
		double d0 = this.mob.getX() - this.mob.xo;
		double d1 = this.mob.getZ() - this.mob.zo;
		return d0 * d0 + d1 * d1 > (double) 2.5000003E-7F;
	}
}

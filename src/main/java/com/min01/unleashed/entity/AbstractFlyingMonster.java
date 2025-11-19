package com.min01.unleashed.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractFlyingMonster extends Monster
{
	public AbstractFlyingMonster(EntityType<? extends Monster> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}

	@Override
	protected void checkFallDamage(double p_20809_, boolean p_20810_, BlockState p_20811_, BlockPos p_20812_)
	{
		
	}

	@Override
	public void travel(Vec3 p_20818_)
	{
		if(this.isControlledByLocalInstance())
		{
			if(this.isInWater())
			{
				this.moveRelative(0.02F, p_20818_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
			} 
			else if(this.isInLava()) 
			{
				this.moveRelative(0.02F, p_20818_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} 
			else 
			{
				BlockPos ground = this.getBlockPosBelowThatAffectsMyMovement();
				float f = 0.91F;
				if(this.onGround())
				{
					f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
				}
				float f1 = 0.16277137F / (f * f * f);
				f = 0.91F;
				if(this.onGround())
				{
					f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
				}
				this.moveRelative(this.onGround() ? 0.1F * f1 : this.getRelativeSpeed(), p_20818_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) f));
			}
		}
		this.calculateEntityAnimation(false);
	}
	
	public float getRelativeSpeed()
	{
		return 0.02F;
	}

	@Override
	public boolean onClimbable()
	{
		return false;
	}
}

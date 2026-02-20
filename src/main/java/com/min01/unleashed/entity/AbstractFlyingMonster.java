package com.min01.unleashed.entity;

import com.min01.unleashed.entity.ai.control.FlyingLookControl;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractFlyingMonster extends Monster
{
	public static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(AbstractFlyingMonster.class, EntityDataSerializers.BOOLEAN);
	
	public float rollAngleO = 0.0F;
	public float rollAngle = 0.0F;
	
	public AbstractFlyingMonster(EntityType<? extends Monster> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
		this.lookControl = new FlyingLookControl(this, 10);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(IS_FLYING, true);
	}

	@Override
	protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos)
	{
		
	}

	@Override
	public void travel(Vec3 pTravelVector)
	{
		if(this.isFlying())
		{
			if(this.isControlledByLocalInstance())
			{
				if(this.isInWater() && this.isAffectedByFluids())
				{
					this.moveRelative(0.02F, pTravelVector);
					this.move(MoverType.SELF, this.getDeltaMovement());
					this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
				} 
				else if(this.isInLava() && this.isAffectedByFluids()) 
				{
					this.moveRelative(0.02F, pTravelVector);
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
					this.moveRelative(this.onGround() ? 0.1F * f1 : this.getRelativeSpeed(), pTravelVector);
					this.move(MoverType.SELF, this.getDeltaMovement());
					this.setDeltaMovement(this.getDeltaMovement().scale((double) f));
				}
			}
			this.calculateEntityAnimation(false);
		}
		else
		{
			super.travel(pTravelVector);
		}
	}
	
	@Override
	public void tick() 
	{
		super.tick();
	    Vec3 movement = this.getDeltaMovement();
	    float speed = (float) movement.length();
	    this.rollAngleO = this.rollAngle;
	    if(speed > this.getRollThreshold() && this.isFlying()) 
	    {
	        this.rollAngle += (this.getTargetRoll(movement) - this.rollAngle) * this.getRollAmount();
	    }
	    else
	    {
	        this.rollAngle *= 0.9F;
	    }
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag pCompound)
	{
		super.addAdditionalSaveData(pCompound);
		pCompound.putBoolean("isFlying", this.isFlying());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound)
	{
		super.readAdditionalSaveData(pCompound);
		this.setFlying(pCompound.getBoolean("isFlying"));
	}

	@Override
	public boolean onClimbable()
	{
		return false;
	}
	
	@Override
	protected PathNavigation createNavigation(Level pLevel) 
	{
		return new FlyingPathNavigation(this, pLevel);
	}
	
	public void switchControl(boolean isFlying)
	{
		
	}
	
	public float getRollAngle(float partialTicks)
	{
		return Mth.lerp(partialTicks, this.rollAngleO, this.rollAngle);
	}
	
    public void setFlying(boolean value)
    {
    	this.entityData.set(IS_FLYING, value);
    }
	
	public boolean isFlying()
	{
		return this.entityData.get(IS_FLYING);
	}
	
	public float getTargetRoll(Vec3 movement)
	{
		return (float) (Math.toDegrees(Math.atan2(movement.x, movement.z)) * 0.01F);
	}
	
	public float getRollThreshold()
	{
		return 0.05F;
	}
	
	public float getRollAmount()
	{
		return 0.01F;
	}
	
	public float getRelativeSpeed()
	{
		return 0.02F;
	}
}

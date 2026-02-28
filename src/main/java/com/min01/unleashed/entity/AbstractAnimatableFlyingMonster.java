package com.min01.unleashed.entity;

import com.min01.unleashed.entity.ai.control.AnimationBodyRotationControl;
import com.min01.unleashed.entity.ai.control.AnimationFlyingMoveControl;
import com.min01.unleashed.entity.ai.control.AnimationMoveControl;
import com.min01.unleashed.entity.ai.control.FlyingLookControl;
import com.min01.unleashed.entity.ai.navigation.NoSpinFlyingPathNavigation;
import com.min01.unleashed.entity.ai.navigation.NoSpinGroundPathNavigation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAnimatableFlyingMonster extends AbstractFlyingMonster implements IAnimatable
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_LOOK_TICK = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_MOVE_TICK = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_TARGET_VALID = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_ANIMATION_PLAYING = SynchedEntityData.defineId(AbstractAnimatableFlyingMonster.class, EntityDataSerializers.BOOLEAN);

	public Vec3[] posArray;
	
	public AbstractAnimatableFlyingMonster(EntityType<? extends AbstractFlyingMonster> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
		this.moveControl = new AnimationFlyingMoveControl<>(this);
		this.lookControl = new FlyingLookControl(this, 10);
		this.noCulling = true;
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(STOP_LOOK_TICK, 0);
		this.entityData.define(STOP_MOVE_TICK, 0);
		this.entityData.define(IS_TARGET_VALID, false);
		this.entityData.define(IS_ANIMATION_PLAYING, false);
	}
	
	@Override
	protected void registerGoals()
	{
		this.registerDefaultGoals();
	}
	
	public void registerDefaultGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getMoveInterval(), false)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableFlyingMonster.this.canMoveAround() && !AbstractAnimatableFlyingMonster.this.isFlying();
			}
			
			@Override
			protected Vec3 getPosition()
			{
				Vec2 radius = AbstractAnimatableFlyingMonster.this.getMoveRadius();
				return LandRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y);
			}
		});
		this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getFlyInterval(), false)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableFlyingMonster.this.canMoveAround() && AbstractAnimatableFlyingMonster.this.isFlying();
			}
			
			@Override
			protected Vec3 getPosition()
			{
				Vec2 radius = AbstractAnimatableFlyingMonster.this.getFlyRadius();
				Vec3 vec3 = this.mob.getViewVector(0.0F);
				Vec3 vec31 = HoverRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y, vec3.x, vec3.z, ((float)Math.PI / 2.0F), 3, 1);
				return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2.0F));
			}
		});
	}
    
    @Override
    public void tick()
    {
    	super.tick();

		if(!this.level.isClientSide)
		{
			this.setTargetValid(this.getTarget() != null && this.getTarget().isAlive());
		}
		
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
		
		if(this.getStopLookTick() > 0)
		{
			this.setStopLookTick(this.getStopLookTick() - 1);
		}
		
		if(this.getStopMoveTick() > 0)
		{
			this.setStopMoveTick(this.getStopMoveTick() - 1);
		}
		
		if(this.getAnimationState() != 0 && this.getAnimationTick() <= 0)
		{
			if(this.onAnimationEnd(this.getAnimationState()))
			{
				this.setAnimationState(0);
				this.setAnimationPlaying(false);
			}
		}
    }

    @Override
    protected BodyRotationControl createBodyControl() 
    {
    	return new AnimationBodyRotationControl<>(this);
    }
    
	@Override
	protected PathNavigation createNavigation(Level pLevel) 
	{
		return new NoSpinFlyingPathNavigation(this, pLevel);
	}
    
    public boolean onAnimationEnd(int animationState)
    {
    	return true;
    }
    
    @Override
	public void moveToTarget()
	{
		this.getNavigation().moveTo(this.getTarget(), 1.0F);
	}
	
    @Override
	public void lookAtTarget()
	{
		this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
	}
	
	public boolean canLookAround()
	{
		return this.canLook() && !this.isAnimationPlaying() && !this.isTargetValid();
	}
	
	public boolean canMoveAround()
	{
		return this.canMove() && !this.isAnimationPlaying() && !this.isTargetValid();
	}
	
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.readAdditionalSaveData(pCompound);
    	this.setAnimationPlaying(pCompound.getBoolean("isAnimationPlaying"));
    	this.setStopLookTick(pCompound.getInt("StopLookTick"));
    	this.setStopMoveTick(pCompound.getInt("StopMoveTick"));
    	this.setAnimationTick(pCompound.getInt("AnimationTick"));
    	this.setAnimationState(pCompound.getInt("AnimationState"));
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.addAdditionalSaveData(pCompound);
    	pCompound.putBoolean("isAnimationPlaying", this.isAnimationPlaying());
    	pCompound.putInt("StopLookTick", this.getStopLookTick());
    	pCompound.putInt("StopMoveTick", this.getStopMoveTick());
    	pCompound.putInt("AnimationTick", this.getAnimationTick());
    	pCompound.putInt("AnimationState", this.getAnimationState());
    }
    
    @Override
    public void switchControl(boolean isFlying)
    {
    	if(!isFlying && !(this.moveControl instanceof AnimationMoveControl))
    	{
    		this.moveControl = new AnimationMoveControl<>(this);
    		this.lookControl = new LookControl(this);
    		this.navigation = new NoSpinGroundPathNavigation(this, this.level);
    	}
    	if(isFlying && !(this.moveControl instanceof AnimationFlyingMoveControl))
    	{
    		this.moveControl = new AnimationFlyingMoveControl<>(this);
    		this.lookControl = new FlyingLookControl(this, 10);
    		this.navigation = this.createNavigation(this.level);
    	}
    }
    
    @Override
    public Vec3[] getPosArray()
    {
    	return this.posArray;
    }
	
	public void setTargetValid(boolean value)
	{
		this.entityData.set(IS_TARGET_VALID, value);
	}
	
	public boolean isTargetValid()
	{
		return this.entityData.get(IS_TARGET_VALID);
	}
	
	@Override
	public void setAnimationPlaying(boolean value) 
	{
		this.entityData.set(IS_ANIMATION_PLAYING, value);
	}
	
	@Override
	public boolean isAnimationPlaying() 
	{
		return this.getAnimationTick() > 0 || this.entityData.get(IS_ANIMATION_PLAYING);
	}
	
	@Override
    public void setStopLookTick(int value)
    {
    	this.entityData.set(STOP_LOOK_TICK, value);
    }
    
    @Override
    public int getStopLookTick()
    {
    	return this.entityData.get(STOP_LOOK_TICK);
    }
    
    @Override
    public boolean canLook()
    {
    	return this.getStopLookTick() <= 0;
    }
    
    @Override
    public void setStopMoveTick(int value)
    {
    	this.entityData.set(STOP_MOVE_TICK, value);
    }
    
    @Override
    public int getStopMoveTick()
    {
    	return this.entityData.get(STOP_MOVE_TICK);
    }
    
    @Override
    public boolean canMove()
    {
    	return this.getStopMoveTick() <= 0;
    }
    
    @Override
    public void setAnimationTick(int value)
    {
        this.entityData.set(ANIMATION_TICK, value);
    }
    
    @Override
    public int getAnimationTick()
    {
        return this.entityData.get(ANIMATION_TICK);
    }
    
    public void setAnimationState(int value)
    {
        this.entityData.set(ANIMATION_STATE, value);
    }
    
    public int getAnimationState()
    {
        return this.entityData.get(ANIMATION_STATE);
    }
    
    public boolean isAnimationPlaying(int state)
    {
    	return this.getAnimationState() == state && this.isAnimationPlaying();
    }
}

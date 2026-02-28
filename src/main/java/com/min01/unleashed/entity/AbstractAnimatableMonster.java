package com.min01.unleashed.entity;

import com.min01.unleashed.entity.ai.control.AnimationBodyRotationControl;
import com.min01.unleashed.entity.ai.control.AnimationMoveControl;
import com.min01.unleashed.entity.ai.navigation.NoSpinGroundPathNavigation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAnimatableMonster extends Monster implements IAnimatable
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_LOOK_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_MOVE_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_TARGET_VALID = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_ANIMATION_PLAYING = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);

	public Vec3[] posArray;
	
	public AbstractAnimatableMonster(EntityType<? extends Monster> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
		this.moveControl = new AnimationMoveControl<>(this);
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
				return super.canUse() && AbstractAnimatableMonster.this.canMoveAround();
			}
			
			@Override
			protected Vec3 getPosition()
			{
				Vec2 radius = AbstractAnimatableMonster.this.getMoveRadius();
				return LandRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y);
			}
		});
		this.goalSelector.addGoal(0, new RandomLookAroundGoal(this)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableMonster.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableMonster.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Mob.class, 8.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableMonster.this.canLookAround();
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
    	return new NoSpinGroundPathNavigation(this, pLevel);
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

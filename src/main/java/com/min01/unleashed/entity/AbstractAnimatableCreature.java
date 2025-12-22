package com.min01.unleashed.entity;

import com.min01.unleashed.entity.ai.navigation.FixedPathNavigation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAnimatableCreature extends PathfinderMob implements IAnimatable, IPosArray
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> CAN_LOOK = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> CAN_MOVE = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_USING_SKILL = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.BOOLEAN);

	public Vec3[] posArray;
	
	public AbstractAnimatableCreature(EntityType<? extends PathfinderMob> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
		this.noCulling = true;
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(CAN_LOOK, true);
		this.entityData.define(CAN_MOVE, true);
		this.entityData.define(HAS_TARGET, false);
		this.entityData.define(IS_USING_SKILL, false);
	}
	
	@Override
	protected void registerGoals()
	{
		this.registerDefaultGoals();
	}
	
	public void registerDefaultGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableCreature.this.canMoveAround();
			}
		});
		this.goalSelector.addGoal(0, new RandomLookAroundGoal(this)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableCreature.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableCreature.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Mob.class, 8.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableCreature.this.canLookAround();
			}
		});
	}
    
    @Override
    public void tick()
    {
    	super.tick();

		if(!this.level.isClientSide)
		{
			this.setHasTarget(this.getTarget() != null && this.getTarget().isAlive());
		}
		
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
		
		if(this.entityData.get(IS_USING_SKILL) && this.getAnimationTick() <= 0)
		{
			this.onAnimationEnd(this.getAnimationState());
			this.setAnimationState(0);
			this.setUsingSkill(false);
		}
    }
    
    @Override
    protected PathNavigation createNavigation(Level pLevel)
    {
    	return new FixedPathNavigation(this, pLevel);
    }
    
    public void onAnimationEnd(int animationState)
    {
    	
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
		return this.canLook() && !this.isUsingSkill() && !this.hasTarget();
	}
	
	public boolean canMoveAround()
	{
		return this.canMove() && !this.isUsingSkill() && !this.hasTarget();
	}
	
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.readAdditionalSaveData(pCompound);
    	this.setUsingSkill(pCompound.getBoolean("isUsingSkill"));
    	this.setCanLook(pCompound.getBoolean("CanLook"));
    	this.setCanMove(pCompound.getBoolean("CanMove"));
    	this.setAnimationTick(pCompound.getInt("AnimationTick"));
    	this.setAnimationState(pCompound.getInt("AnimationState"));
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.addAdditionalSaveData(pCompound);
    	pCompound.putBoolean("isUsingSkill", this.isUsingSkill());
    	pCompound.putBoolean("CanLook", this.canLook());
    	pCompound.putBoolean("CanMove", this.canMove());
    	pCompound.putInt("AnimationTick", this.getAnimationTick());
    	pCompound.putInt("AnimationState", this.getAnimationState());
    }
    
    @Override
    public Vec3[] getPosArray()
    {
    	return this.posArray;
    }
	
	public void setHasTarget(boolean value)
	{
		this.entityData.set(HAS_TARGET, value);
	}
	
	public boolean hasTarget()
	{
		return this.entityData.get(HAS_TARGET);
	}
	
	@Override
	public void setUsingSkill(boolean value) 
	{
		this.entityData.set(IS_USING_SKILL, value);
	}
	
	@Override
	public boolean isUsingSkill() 
	{
		return this.getAnimationTick() > 0 || this.entityData.get(IS_USING_SKILL);
	}
	
    public void setCanLook(boolean value)
    {
    	this.entityData.set(CAN_LOOK, value);
    }
    
    @Override
    public boolean canLook()
    {
    	return this.entityData.get(CAN_LOOK);
    }
    
    @Override
    public void setCanMove(boolean value)
    {
    	this.entityData.set(CAN_MOVE, value);
    }
    
    @Override
    public boolean canMove()
    {
    	return this.entityData.get(CAN_MOVE);
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
    
    public boolean isUsingSkill(int state)
    {
    	return this.getAnimationState() == state && this.isUsingSkill();
    }
}

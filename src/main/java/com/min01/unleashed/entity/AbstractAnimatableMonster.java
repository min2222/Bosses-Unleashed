package com.min01.unleashed.entity;

import com.min01.unleashed.entity.ai.control.AnimationBodyRotationControl;
import com.min01.unleashed.entity.ai.control.AnimationFlyingMoveControl;
import com.min01.unleashed.entity.ai.control.AnimationMoveControl;
import com.min01.unleashed.entity.ai.control.AnimationSwimmingMoveControl;
import com.min01.unleashed.entity.ai.control.FlyingLookControl;
import com.min01.unleashed.entity.ai.navigation.NoSpinFlyingPathNavigation;
import com.min01.unleashed.entity.ai.navigation.NoSpinGroundPathNavigation;
import com.min01.unleashed.entity.ai.navigation.NoSpinWaterBoundPathNavigation;
import com.min01.unleashed.misc.MobClassification;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public abstract class AbstractAnimatableMonster extends Monster implements IAnimatable
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_LOOK_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> STOP_MOVE_TICK = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_TARGET_VALID = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_ANIMATION_PLAYING = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_SWIM = SynchedEntityData.defineId(AbstractAnimatableMonster.class, EntityDataSerializers.BOOLEAN);
	
	public float rollAngleO = 0.0F;
	public float rollAngle = 0.0F;
	
	public Vec3[] posArray;
	
	public AbstractAnimatableMonster(EntityType<? extends Monster> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
		if(this.getMobClassification() == MobClassification.WATER)
		{
			this.moveControl = new AnimationSwimmingMoveControl<>(this);
			this.lookControl = new SmoothSwimmingLookControl(this, 10);
			this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		}
		else if(this.getMobClassification() == MobClassification.AIR)
		{
			this.moveControl = new AnimationFlyingMoveControl<>(this);
			this.lookControl = new FlyingLookControl(this, 10);
		}
		else
		{
			this.moveControl = new AnimationMoveControl<>(this);
		}
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
		this.entityData.define(IS_SWIM, true);
		this.entityData.define(IS_FLYING, true);
	}
	
	@Override
	protected void registerGoals()
	{
		this.registerDefaultGoals();
	}
	
	public void registerDefaultGoals()
	{
		if(this.getMobClassification() == MobClassification.WATER)
		{
			this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getMoveInterval(), false)
			{
				@Override
				public boolean canUse()
				{
					if(super.canUse() && AbstractAnimatableMonster.this.canMoveAround())
					{
						return !AbstractAnimatableMonster.this.isInWater();
					}
					return false;
				}
				
				@Override
				protected Vec3 getPosition()
				{
					Vec2 radius = AbstractAnimatableMonster.this.getMoveRadius();
					return DefaultRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y);
				}
			});
			this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getSwimInterval(), false)
			{
				@Override
				public boolean canUse()
				{
					if(super.canUse() && AbstractAnimatableMonster.this.canMoveAround())
					{
						return AbstractAnimatableMonster.this.isInWater();
					}
					return false;
				}
				
				@Override
				protected Vec3 getPosition()
				{
					Vec2 radius = AbstractAnimatableMonster.this.getSwimRadius();
					return BehaviorUtils.getRandomSwimmablePos(this.mob, (int) radius.x, (int) radius.y);
				}
			});
		}
		else if(this.getMobClassification() == MobClassification.AIR)
		{
			this.goalSelector.addGoal(0, new FloatGoal(this));
			this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getMoveInterval(), false)
			{
				@Override
				public boolean canUse()
				{
					return super.canUse() && AbstractAnimatableMonster.this.canMoveAround() && !AbstractAnimatableMonster.this.isFlying();
				}
				
				@Override
				protected Vec3 getPosition()
				{
					Vec2 radius = AbstractAnimatableMonster.this.getMoveRadius();
					return LandRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y);
				}
			});
			this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1.0F, this.getFlyInterval(), false)
			{
				@Override
				public boolean canUse()
				{
					return super.canUse() && AbstractAnimatableMonster.this.canMoveAround() && AbstractAnimatableMonster.this.isFlying();
				}
				
				@Override
				protected Vec3 getPosition()
				{
					Vec2 radius = AbstractAnimatableMonster.this.getFlyRadius();
					Vec3 vec3 = this.mob.getViewVector(0.0F);
					Vec3 vec31 = HoverRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y, vec3.x, vec3.z, ((float)Math.PI / 2.0F), 3, 1);
					return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, (int) radius.x, (int) radius.y, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2.0F));
				}
			});
		}
		else
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
	}
	
	@Override
	public boolean canBreatheUnderwater() 
	{
		return this.getMobClassification() == MobClassification.WATER;
	}

	@Override
	public MobType getMobType()
	{
		return this.getMobClassification() == MobClassification.WATER ? MobType.WATER : super.getMobType();
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader pLevel)
	{
		if(this.getMobClassification() == MobClassification.WATER)
		{
			return pLevel.isUnobstructed(this);
		}
		return super.checkSpawnObstruction(pLevel);
	}
	
	@Override
	protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos)
	{
		if(this.getMobClassification() != MobClassification.AIR)
		{
			super.checkFallDamage(pY, pOnGround, pState, pPos);
		}
	}
	
	@Override
	protected void playStepSound(BlockPos pPos, BlockState pState) 
	{
		if(!this.isSwim() || this.getMobClassification() != MobClassification.WATER)
		{
			super.playStepSound(pPos, pState);
		}
	}

	protected void handleAirSupply(int pAirSupply) 
	{
		if(this.isAlive() && !this.isInWaterOrBubble())
		{
			this.setAirSupply(pAirSupply - 1);
			if(this.getAirSupply() == -20)
			{
				this.setAirSupply(0);
				this.hurt(this.damageSources().drown(), 2.0F);
			}
		} 
		else 
		{
			this.setAirSupply(300);
		}
	}
	
    @Override
    public void travel(Vec3 pTravelVector) 
    {
    	if(this.getMobClassification() == MobClassification.WATER)
    	{
        	if(this.isEffectiveAi() && this.isInWater() && this.isSwim())
        	{
        		this.moveRelative((float) (this.getSpeed() * this.getAttributeValue(ForgeMod.SWIM_SPEED.get())), pTravelVector);
        		this.move(MoverType.SELF, this.getDeltaMovement());
        		this.setDeltaMovement(this.getDeltaMovement().scale(0.9F));
        	}
        	else
        	{
        		super.travel(pTravelVector);
        	}
    	}
    	else if(this.getMobClassification() == MobClassification.AIR)
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
    					float flySpeed = (float) (this.getAttributes().hasAttribute(Attributes.FLYING_SPEED) ? this.getAttributeValue(Attributes.FLYING_SPEED) : 1.0F);
    					this.moveRelative(this.onGround() ? 0.1F * f1 : this.getSpeed() * flySpeed, pTravelVector);
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
    	else
    	{
    		super.travel(pTravelVector);
    	}
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
		
	    Vec3 movement = this.getDeltaMovement();
	    float speed = (float) movement.length();
	    this.rollAngleO = this.rollAngle;
	    boolean flag = false;
	    if(this.getMobClassification() == MobClassification.WATER)
	    {
	    	flag = this.isInWater();
	    }
	    else if(this.getMobClassification() == MobClassification.AIR)
	    {
	    	flag = this.isFlying();
	    }
	    if(speed > this.getRollThreshold() && flag) 
	    {
	        this.rollAngle += (this.getTargetRoll(movement) - this.rollAngle) * this.getRollAmount();
	    }
	    else
	    {
	        this.rollAngle *= 0.9F;
	    }
    }
    
    @Override
    protected BodyRotationControl createBodyControl() 
    {
    	return new AnimationBodyRotationControl<>(this);
    }
    
    public void switchControl(boolean flag)
    {
    	if(this.getMobClassification() == MobClassification.WATER)
    	{
        	if(!flag && !(this.moveControl instanceof AnimationMoveControl))
        	{
        		this.moveControl = new AnimationMoveControl<>(this);
        		this.lookControl = new LookControl(this);
        		this.navigation = new NoSpinGroundPathNavigation(this, this.level);
        	}
        	if(flag && !(this.moveControl instanceof AnimationSwimmingMoveControl))
        	{
        		this.moveControl = new AnimationSwimmingMoveControl<>(this);
        		this.lookControl = new SmoothSwimmingLookControl(this, 10);
        		this.navigation = this.createNavigation(this.level);
        	}
    	}
    	else if(this.getMobClassification() == MobClassification.AIR)
    	{
        	if(!flag && !(this.moveControl instanceof AnimationMoveControl))
        	{
        		this.moveControl = new AnimationMoveControl<>(this);
        		this.lookControl = new LookControl(this);
        		this.navigation = new NoSpinGroundPathNavigation(this, this.level);
        	}
        	if(flag && !(this.moveControl instanceof AnimationFlyingMoveControl))
        	{
        		this.moveControl = new AnimationFlyingMoveControl<>(this);
        		this.lookControl = new FlyingLookControl(this, 10);
        		this.navigation = this.createNavigation(this.level);
        	}
    	}
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
		this.setSwim(pCompound.getBoolean("isSwim"));
		this.setFlying(pCompound.getBoolean("isFlying"));
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
		pCompound.putBoolean("isSwim", this.isSwim());
		pCompound.putBoolean("isFlying", this.isFlying());
    }
    
    @Override
    protected boolean isAffectedByFluids()
    {
    	if(this.getMobClassification() == MobClassification.WATER)
    	{
        	return this.isSwim();
    	}
    	return super.isAffectedByFluids();
    }

	@Override
	public void baseTick() 
	{
		int i = this.getAirSupply();
		super.baseTick();
		if(this.getMobClassification() == MobClassification.WATER)
		{
			this.handleAirSupply(i);
		}
	}

	@Override
	public boolean isPushedByFluid() 
	{
		if(this.getMobClassification() == MobClassification.WATER)
		{
			return false;
		}
		return true;
	}
	
	@Override
	protected PathNavigation createNavigation(Level pLevel) 
	{
		if(this.getMobClassification() == MobClassification.WATER)
		{
			return new NoSpinWaterBoundPathNavigation(this, pLevel);
		}
		else if(this.getMobClassification() == MobClassification.AIR)
		{
			return new NoSpinFlyingPathNavigation(this, pLevel);
		}
		return new NoSpinGroundPathNavigation(this, pLevel);
	}
    
	@Override
	public boolean onClimbable()
	{
		if(this.getMobClassification() == MobClassification.AIR)
		{
			return false;
		}
		return super.onClimbable();
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
    
	public float getRollAngle(float partialTicks)
	{
		return Mth.lerp(partialTicks, this.rollAngleO, this.rollAngle);
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
	
	public void setSwim(boolean isSwim)
	{
		this.entityData.set(IS_SWIM, isSwim);
	}
	
	public boolean isSwim()
	{
		return this.entityData.get(IS_SWIM);
	}
	
    public void setFlying(boolean value)
    {
    	this.entityData.set(IS_FLYING, value);
    }
	
	public boolean isFlying()
	{
		return this.entityData.get(IS_FLYING);
	}
	
	public static boolean checkWaterSpawnRules(EntityType<? extends PathfinderMob> pType, ServerLevelAccessor pServerLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) 
    {
		return pServerLevel.getBlockState(pPos.below()).is(Blocks.WATER) && pServerLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }
	
	public MobClassification getMobClassification()
	{
		return MobClassification.LAND;
	}
}

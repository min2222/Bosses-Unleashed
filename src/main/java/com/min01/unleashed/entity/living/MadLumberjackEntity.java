package com.min01.unleashed.entity.living;

import javax.annotation.Nullable;

import com.min01.unleashed.entity.AbstractAnimatableMonster;
import com.min01.unleashed.entity.ai.goal.LookAtTargetGoal;
import com.min01.unleashed.entity.ai.goal.LumberjackAxeAttack1Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackAxeAttack2Goal;
import com.min01.unleashed.entity.ai.goal.MoveToTargetGoal;
import com.min01.unleashed.misc.SmoothAnimationState;
import com.min01.unleashed.misc.UnleashedBossEvent;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MadLumberjackEntity extends AbstractAnimatableMonster
{
	public static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(MadLumberjackEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_GUN = SynchedEntityData.defineId(MadLumberjackEntity.class, EntityDataSerializers.BOOLEAN);
	
	public final SmoothAnimationState axeIdleAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState gunIdleAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState gunWalkAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState axeSprintAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState gunSprintAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState axeAttack1AnimationState = new SmoothAnimationState();
	public final SmoothAnimationState axeAttack2AnimationState = new SmoothAnimationState(0.5F, true);

	public final UnleashedBossEvent bossEvent = (UnleashedBossEvent) new UnleashedBossEvent(this.getDisplayName(), this).setDarkenScreen(true);
	
	public MadLumberjackEntity(EntityType<? extends Monster> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
		this.setMaxUpStep(1.5F);
		this.posArray = new Vec3[1];
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 500.0F)
        		.add(Attributes.ATTACK_DAMAGE, 10.0F)
        		.add(Attributes.FOLLOW_RANGE, 50.0F)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 1.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.2F);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(0, new MoveToTargetGoal<>(this));
    	this.goalSelector.addGoal(0, new LookAtTargetGoal<>(this));
    	this.goalSelector.addGoal(0, new LumberjackAxeAttack1Goal(this));
    	this.goalSelector.addGoal(0, new LumberjackAxeAttack2Goal(this));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }
    
    @Override
    protected void defineSynchedData() 
    {
    	super.defineSynchedData();
    	this.entityData.define(PHASE, 0);
    	this.entityData.define(IS_GUN, false);
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	if(this.level.isClientSide)
    	{
    		this.axeIdleAnimationState.updateWhen(this.getAnimationState() == 0 && !this.isGun(), this.tickCount);
    		this.gunIdleAnimationState.updateWhen(this.getAnimationState() == 0 && this.isGun(), this.tickCount);
    		this.gunWalkAnimationState.updateWhen(this.isGun() && !this.isTargetValid(), this.tickCount);
    		//this.axeSprintAnimationState.updateWhen(!this.isGun() && this.isTargetValid(), this.tickCount);
    		//this.gunSprintAnimationState.updateWhen(this.isGun() && this.isTargetValid(), this.tickCount);
    		this.axeAttack1AnimationState.updateWhen(this.isAnimationPlaying(1), this.tickCount);
    		this.axeAttack2AnimationState.updateWhen(this.isAnimationPlaying(2), this.tickCount);
    	}
    	if(this.isAnimationPlaying())
    	{
    		this.yBodyRot = this.yHeadRot;
    	}
    }
    
    @Override
    protected void customServerAiStep()
    {
    	super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
    
    @Override
    public void moveToTarget() 
    {
		this.getNavigation().moveTo(this.getTarget(), 1.25F);
    }
    
    @Override
    public void lookAtTarget()
    {
    	super.lookAtTarget();
    }
    
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) 
    {
    	this.walkAnimation.setSpeed(0.0F);
    	return super.hurt(pSource, pAmount);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.addAdditionalSaveData(pCompound);
    	pCompound.putInt("Phase", this.getPhase());
    	pCompound.putBoolean("isGun", this.isGun());
    	
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound)
    {
    	super.readAdditionalSaveData(pCompound);
    	this.setPhase(pCompound.getInt("Phase"));
    	this.setGun(pCompound.getBoolean("isGun"));
        if(this.hasCustomName()) 
        {
        	this.bossEvent.setName(this.getDisplayName());
        }
    }
    
    @Override
    public float maxMoveTurnY() 
    {
    	return 60.0F;
    }
    
    @Override
    public float maxBodyTurnY() 
    {
    	return 60.0F;
    }
    
    @Override
    public void setCustomName(@Nullable Component pName) 
    {
    	super.setCustomName(pName);
    	this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer)
    {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer)
    {
    	super.stopSeenByPlayer(pServerPlayer);
    	this.bossEvent.removePlayer(pServerPlayer);
    }
    
    @Override
    protected Component getTypeName() 
    {
    	if(this.getPhase() == 1)
    	{
    		return Component.translatable("entity.bossesunleashed.the_starving_lumberjack");
    	}
    	else if(this.getPhase() == 2)
    	{
    		return Component.translatable("entity.bossesunleashed.the_infected_brute");
    	}
    	return super.getTypeName();
    }
    
    public void setGun(boolean value)
    {
    	this.entityData.set(IS_GUN, value);
    }
    
    public boolean isGun()
    {
    	return this.entityData.get(IS_GUN);
    }
    
    public void setPhase(int value)
    {
    	this.entityData.set(PHASE, value);
    }
    
    public int getPhase()
    {
    	return this.entityData.get(PHASE);
    }
}

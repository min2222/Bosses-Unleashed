package com.min01.unleashed.entity.living;

import javax.annotation.Nullable;

import com.min01.unleashed.entity.AbstractAnimatableMonster;
import com.min01.unleashed.entity.ai.goal.BruteBiteGoal;
import com.min01.unleashed.entity.ai.goal.BruteSmashGoal;
import com.min01.unleashed.entity.ai.goal.BruteSweepGoal;
import com.min01.unleashed.entity.ai.goal.LookAtTargetGoal;
import com.min01.unleashed.entity.ai.goal.LumberjackAxeAttack1Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackAxeAttack2Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackAxeAttack3Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackBearTrapGoal;
import com.min01.unleashed.entity.ai.goal.LumberjackPhase2Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackPhase3Goal;
import com.min01.unleashed.entity.ai.goal.LumberjackShotgunShotGoal;
import com.min01.unleashed.entity.ai.goal.MoveToTargetGoal;
import com.min01.unleashed.misc.SmoothAnimationState;
import com.min01.unleashed.misc.UnleashedBossEvent;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
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
	public final SmoothAnimationState axeAttack3AnimationState = new SmoothAnimationState();
	public final SmoothAnimationState shotgunShotAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState axeChangeAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState shotgunChangeAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieIdleAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieWalkAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieSprintAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack1AnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack2AnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack2SuccessAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack3AnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack4LeftAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState zombieAttack4RightAnimationState = new SmoothAnimationState();

	public final UnleashedBossEvent bossEvent = (UnleashedBossEvent) new UnleashedBossEvent(this.getDisplayName(), this).setDarkenScreen(true);
	
	public MadLumberjackEntity(EntityType<? extends Monster> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
		this.setMaxUpStep(1.5F);
		this.posArray = new Vec3[3];
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
    	this.goalSelector.addGoal(0, new LumberjackAxeAttack3Goal(this));
    	this.goalSelector.addGoal(0, new LumberjackShotgunShotGoal(this));
    	this.goalSelector.addGoal(0, new LumberjackPhase2Goal(this));
    	this.goalSelector.addGoal(0, new LumberjackBearTrapGoal(this));
    	this.goalSelector.addGoal(0, new LumberjackPhase3Goal(this));
    	this.goalSelector.addGoal(0, new BruteSmashGoal(this));
    	this.goalSelector.addGoal(0, new BruteSweepGoal(this));
    	this.goalSelector.addGoal(0, new BruteBiteGoal(this));
    	this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false)
    	{
    		@Override
    		public void start() 
    		{
    			super.start();
    			//TODO roar;
    		}
    	});
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
    		this.axeIdleAnimationState.updateWhen(this.getAnimationState() == 0 && !this.isGun() && this.getPhase() < 2, this.tickCount);
    		this.gunIdleAnimationState.updateWhen(this.getAnimationState() == 0 && this.isGun() && this.getPhase() < 2, this.tickCount);
    		this.gunWalkAnimationState.updateWhen(this.getAnimationState() == 0 && this.isGun() && this.getPhase() < 2, this.tickCount);
    		this.zombieIdleAnimationState.updateWhen(this.getAnimationState() == 0 && this.getPhase() >= 2 && !this.isSprinting(), this.tickCount);
    		this.zombieWalkAnimationState.updateWhen(this.getAnimationState() == 0 && this.getPhase() >= 2 && !this.isSprinting(), this.tickCount);
    		this.zombieSprintAnimationState.updateWhen(this.getAnimationState() == 0 && this.getPhase() >= 2 && this.isSprinting(), this.tickCount);
    		this.axeAttack1AnimationState.updateWhen(this.isAnimationPlaying(1), this.tickCount);
    		this.axeAttack2AnimationState.updateWhen(this.isAnimationPlaying(2), this.tickCount);
    		this.axeAttack3AnimationState.updateWhen(this.isAnimationPlaying(3), this.tickCount);
    		this.shotgunShotAnimationState.updateWhen(this.isAnimationPlaying(4), this.tickCount);
    		this.axeChangeAnimationState.updateWhen(this.isAnimationPlaying(5), this.tickCount);
    		this.shotgunChangeAnimationState.updateWhen(this.isAnimationPlaying(6), this.tickCount);
    		this.zombieAttack1AnimationState.updateWhen(this.isAnimationPlaying(7), this.tickCount);
    		this.zombieAttack2AnimationState.updateWhen(this.isAnimationPlaying(8), this.tickCount);
    		this.zombieAttack2SuccessAnimationState.updateWhen(this.isAnimationPlaying(9), this.tickCount);
    		this.zombieAttack3AnimationState.updateWhen(this.isAnimationPlaying(10), this.tickCount);
    		this.zombieAttack4LeftAnimationState.updateWhen(this.isAnimationPlaying(11), this.tickCount);
    		this.zombieAttack4RightAnimationState.updateWhen(this.isAnimationPlaying(12), this.tickCount);
    	}
    	if(this.getTarget() != null)
    	{
    		if(this.isGun() && this.getAnimationState() == 0)
    		{
        		if(this.distanceTo(this.getTarget()) <= 6.0F)
        		{
        			this.changeToAxe();
        		}
    		}
    		this.setSprinting(this.distanceTo(this.getTarget()) >= 12.0F && this.getPhase() >= 2);
    	}
    	if(!this.isAnimationPlaying(4))
    	{
    		this.yHeadRot = this.yBodyRot;
    	}
    	else if(this.isAnimationPlaying())
    	{
    		this.yBodyRot = this.yHeadRot;
    	}
    	if(this.isAnimationPlaying(5))
    	{
    		if(this.getAnimationTick() == 60)
    		{
    			this.setGun(true);
    		}
    	}
    	if(this.isAnimationPlaying(6))
    	{
    		if(this.getAnimationTick() == 10)
    		{
    			this.setGun(false);
    		}
    	}
    	//TODO eating
    }
    
    public void changeToAxe()
    {
    	this.setAnimationState(6);
    	this.setAnimationTick(20);
    	this.setStopMoveTick(this.getAnimationTick());
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
		if(this.getPhase() == 1)
		{
			this.getNavigation().moveTo(this.getTarget(), 1.35F);
		}
		else
		{
			this.getNavigation().moveTo(this.getTarget(), 1.25F);
		}
    }
    
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) 
    {
    	this.walkAnimation.setSpeed(0.0F);
    	if(this.getAnimationState() == 0 && this.getAnimationTick() > 0 && this.getPhase() >= 2 && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
    	{
    		return false;
    	}
    	return super.hurt(pSource, pAmount);
    }
    
    @Override
    protected void updateWalkAnimation(float pPartialTick)
    {
        float f = Math.min(pPartialTick * 8.0F, 1.0F);
        this.walkAnimation.update(f, 0.4F);
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
    public void push(Entity pEntity) 
    {
    	
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

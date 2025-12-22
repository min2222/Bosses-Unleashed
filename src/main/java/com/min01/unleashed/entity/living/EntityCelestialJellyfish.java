package com.min01.unleashed.entity.living;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.unleashed.entity.AbstractAnimatableFlyingMonster;
import com.min01.unleashed.entity.EntityBlackhole;
import com.min01.unleashed.entity.EntityCameraShake;
import com.min01.unleashed.entity.EntityWormhole;
import com.min01.unleashed.entity.IShaderEffect;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishCloneDashGoal;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishCloneShootOrbGoal;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishDashGoal;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishShootOrbGoal;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishSummonDashCloneGoal;
import com.min01.unleashed.entity.ai.goal.CelestialJellyfishSummonOrbCloneGoal;
import com.min01.unleashed.entity.projectile.EntityCelestialBeam;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.misc.AfterImage;
import com.min01.unleashed.misc.SmoothAnimationState;
import com.min01.unleashed.misc.UnleashedBossBarType;
import com.min01.unleashed.misc.UnleashedBossEvent;
import com.min01.unleashed.misc.UnleashedEntityDataSerializers;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;
import com.min01.unleashed.world.UnleashedSavedData;
import com.min01.unleashed.world.UnleashedWorlds;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityCelestialJellyfish extends AbstractAnimatableFlyingMonster implements IShaderEffect
{
	public static final EntityDataAccessor<Boolean> IS_TRANSFORM = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> SHOW_EFFECT = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> SHOW_WARNING = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> SHOW_AFTER_IMAGE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_VISIBLE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_HIT_TIME = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_TELEPORT = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_REWIND = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_MOVE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_END = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FLOAT = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_CLONE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_SECOND_PHASE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_FINAL_PHASE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Float> EFFECT_SCALE = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.FLOAT);
	public static final EntityDataAccessor<Integer> SCALE_DIR = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> HIT_TIME = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Vec3> LAST_LOOK_POS = SynchedEntityData.defineId(EntityCelestialJellyfish.class, UnleashedEntityDataSerializers.VEC3.get());
	
	public int oldSwell;
	public int swell;
	public final int maxSwell = 320;
	public int phaseTime;
	
	public Class<?> goal;
	
	public final SmoothAnimationState swimAnimationState = new SmoothAnimationState();
	
	public final AfterImage<EntityCelestialJellyfish> afterImage;
	
	public final UnleashedBossEvent bossEvent = (UnleashedBossEvent) new UnleashedBossEvent(this.getDisplayName(), UnleashedBossBarType.CELESTIAL_JELLY_FISH, this).setDarkenScreen(true);
	   
	public EntityCelestialJellyfish(EntityType<? extends Monster> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
		this.afterImage = new AfterImage<>(this, 2, 1);
    	this.bossEvent.setVisible(false);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 200.0F)
        		.add(Attributes.ATTACK_DAMAGE, 15.0F)
        		.add(Attributes.FOLLOW_RANGE, 200.0F)
        		.add(Attributes.ARMOR, 12.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 12.0F)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 10.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(0, new CelestialJellyfishDashGoal(this));
    	this.goalSelector.addGoal(0, new CelestialJellyfishShootOrbGoal(this));
    	this.goalSelector.addGoal(0, new CelestialJellyfishSummonDashCloneGoal(this));
    	this.goalSelector.addGoal(0, new CelestialJellyfishCloneDashGoal(this));
    	this.goalSelector.addGoal(0, new CelestialJellyfishSummonOrbCloneGoal(this));
    	this.goalSelector.addGoal(0, new CelestialJellyfishCloneShootOrbGoal(this));
    	this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }
    
    @Override
    protected void defineSynchedData() 
    {
    	super.defineSynchedData();
    	this.entityData.define(IS_TRANSFORM, false);
    	this.entityData.define(SHOW_EFFECT, false);
    	this.entityData.define(SHOW_WARNING, false);
    	this.entityData.define(SHOW_AFTER_IMAGE, false);
    	this.entityData.define(IS_VISIBLE, true);
    	this.entityData.define(IS_HIT_TIME, false);
    	this.entityData.define(IS_TELEPORT, false);
    	this.entityData.define(IS_REWIND, false);
    	this.entityData.define(IS_MOVE, false);
    	this.entityData.define(IS_END, false);
    	this.entityData.define(IS_FLOAT, false);
    	this.entityData.define(IS_CLONE, false);
    	this.entityData.define(IS_SECOND_PHASE, false);
    	this.entityData.define(IS_FINAL_PHASE, false);
    	this.entityData.define(EFFECT_SCALE, 0.0F);
    	this.entityData.define(SCALE_DIR, 1);
    	this.entityData.define(HIT_TIME, 0);
		this.entityData.define(OWNER_UUID, Optional.empty());
    	this.entityData.define(LAST_LOOK_POS, Vec3.ZERO);
    }
    
    @Override
    public void tick() 
    {
    	this.refreshDimensions();
    	this.tickTransform();
        
    	super.tick();
    	
    	if(!this.level.isClientSide)
    	{
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    	}
    	else
    	{
    		this.swimAnimationState.updateWhen(this.isAlive(), this.tickCount);
    	}
    	
    	this.afterImage.tick(this.showAfterImage());
    	
    	if(this.isTransform())
    	{
			this.tickTeleport();
			this.tickFinalPhase();
			
			if(!this.getLastLookPos().equals(Vec3.ZERO))
			{
				this.lookAt(Anchor.FEET, this.getLastLookPos());
			}
			else if(this.getTarget() != null)
			{
				this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
			}
			
    		if(this.isClone())
    		{
    			if(this.getOwner() != null)
    			{
       				if(this.getOwner().hurtTime > 0)
    				{
        				this.getOwner().setAnimationTick(0);
        				this.getOwner().doTeleport();
        				this.discard();
    				}
        			if(!this.getOwner().isAlive())
        			{
        				this.discard();
        			}
    			}
    			if(!this.level.isClientSide)
    			{
    				if(this.goal == null)
    				{
        				if(!this.isTeleport())
        				{
            				this.doTeleport();
        				}
        	    		if(this.isMove())
        	    		{
        	    			this.discard();
        	    		}
    				}
    			}
    		}
			
			if(!this.entityData.get(IS_USING_SKILL) && this.isEnd())
			{
				this.setEnd(false);
			}
    		
    		if(this.isHitTime())
    		{
    			if(this.isAlive())
    			{
        			this.setYBodyRot(0.0F);
        			this.setYRot(0.0F);
        			if(this.getHitTime() > 0)
        			{
        				this.setDeltaMovement(Vec3.ZERO);
            			this.setHitTime(this.getHitTime() - 1);
            			this.setDeltaMovement(Vec3.ZERO.subtract(0.0F, 0.01F, 0.0F));
            			if(this.tickCount % 40 == 0 && !this.entityData.get(IS_USING_SKILL) && !this.isClone())
            			{
            				UnleashedShaderEffects.addEffect(this.level, "Shockwave", this.position().add(0, 4.5F, 0), 100, 1.0F);
            			}
        			}
        			else if(this.isMove() && this.getHealth() > 1.0F)
        			{
        				this.setHitTime(false);
        			}
    			}
    			else
    			{
    				this.setHitTime(false);
    				this.setHitTime(0);
    			}
    		}
    	}
    	else
    	{
    		BlockPos groundPos = UnleashedUtil.getGroundPos(this.level, this.getX(), this.getY() + 2, this.getZ());
    		if(this.getY() <= groundPos.getY() + 5)
    		{
    			this.addDeltaMovement(new Vec3(0.0F, 0.005F, 0.0F));
    		}
    	}
    }
    
    @Override
    public boolean canCollideWith(Entity pEntity) 
    {
    	return false;
    }
    
    @Override
    protected void doPush(Entity pEntity) 
    {
    	
    }
    
    @Override
    public void push(double pX, double pY, double pZ)
    {
    	
    }
    
    @Override
    public boolean isPushable()
    {
    	return false;
    }
    
    public void tickFinalPhase()
    {
    	if(this.isSecondPhase() && this.getHealth() <= 1.0F)
    	{
    		if(this.isMove())
    		{
    			this.setFloat(true);
    			this.setHitTime(true);
    			this.setHitTime(500);
    		}
    		if(this.isEnd())
    		{
    			this.setFinalPhase(true);
    		}
    	}
    	if(this.isFinalPhase())
    	{
    		this.setDeltaMovement(Vec3.ZERO);
    		int tick = this.getHitTime();
			float strength = 500 - this.getHitTime();
    		tick /= 20;
    		tick = Math.max(tick, 1);
    		if(this.tickCount % tick == 0)
    		{
    			EntityCelestialBeam beam = new EntityCelestialBeam(UnleashedEntities.CELESTIAL_BEAM.get(), this.level);
    			beam.setPos(this.getEyePosition());
    			beam.setOwner(this);
    			beam.setYaw(this.random.nextFloat() * 360.0F);
    			beam.setPitch(this.random.nextFloat() * 45.0F);
    			this.level.addFreshEntity(beam);
    			EntityCameraShake.cameraShake(this.level, this.position(), Math.max(strength * 0.1F, 30.0F), Math.max(strength * 0.0001F, 0.0F), 0, 20);
    		}
			List<Entity> list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(15.0F), this::canPush);
			list.forEach(t -> 
			{
				if(t instanceof Projectile)
				{
					Vec3 motion = UnleashedUtil.getVelocityTowards(this.position(), t.position(), 0.6F);
					t.addDeltaMovement(motion);
				}
				else
				{
					Vec3 motion = UnleashedUtil.getVelocityTowards(this.position(), t.position(), Math.max(0.05F - (strength * 0.0001F), 0.0F));
					t.push(motion.x, motion.y, motion.z);
				}
	    		if(t instanceof ServerPlayer player)
	    		{
	    			player.connection.send(new ClientboundSetEntityMotionPacket(t));
	    		}
			});
    		if(this.hurtTime > 0)
    		{
    			this.level.explode(this, this.getX(), this.getEyeY(), this.getZ(), 5.0F, ExplosionInteraction.NONE);
    			this.setHealth(0.0F);
    			this.setHitTime(false);
    			this.setHitTime(0);
    			this.setFinalPhase(false);
    			this.spawnWormhole();
    		}
    		else if(this.getHitTime() <= 0)
    		{
    			EntityBlackhole blackHole = new EntityBlackhole(UnleashedEntities.BLACKHOLE.get(), this.level);
    			blackHole.setPos(this.position());
    			this.level.addFreshEntity(blackHole);
    			this.playSound(SoundEvents.GENERIC_EXPLODE);
    			EntityCameraShake.cameraShake(this.level, this.position(), 100.0F, 0.1F, 0, 10);
    			this.dropAllDeathLoot(this.damageSources().generic());
    			this.discard();
    		}
    	}
    }
    
    public boolean canPush(Entity entity)
    {
    	if(entity instanceof Player player)
    	{
    		return EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(player);
    	}
    	return entity instanceof Projectile projectile && !(projectile.getOwner() instanceof EntityCelestialJellyfish);
    }
    
    @Override
    public boolean isEffectiveAi() 
    {
    	return super.isEffectiveAi() && !this.isFinalPhase();
    }
    
    public void spawnWormhole()
    {
    	if(this.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
    	{
			float yRot = this.getRandom().nextFloat() * 360.0F;
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.getYHeadRot() + yRot), this.position(), 0, 0, this.getRandom().nextInt(10, 20));
        	EntityWormhole wormhole = new EntityWormhole(UnleashedEntities.WORMHOLE.get(), this.level);
        	wormhole.setPos(lookPos);
        	this.level.addFreshEntity(wormhole);
    	}
    }
    
    public void tickTransform()
    {
		this.oldSwell = this.swell;

		if(this.getAnimationState() == 1)
		{
			this.swell++;
			
			EntityCameraShake.cameraShake(this.level, this.position(), 30.0F, 0.01F, 0, 10);
			this.setDeltaMovement(Vec3.ZERO);
			
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(this.getEffectScale() * 0.1F), EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(t -> !(t instanceof EntityCelestialJellyfish) && !t.isAlliedTo(this)));
			list.forEach(t -> 
			{
				Vec3 motion = UnleashedUtil.getVelocityTowards(this.position(), t.position(), 0.05F);
				t.push(motion.x, motion.y, motion.z);
	    		if(t instanceof ServerPlayer player)
	    		{
	    			player.connection.send(new ClientboundSetEntityMotionPacket(t));
	    		}
			});
			
			if(this.swell == 300)
			{
    			this.setTransform(true);
			}
			
			if(this.swell >= 300)
			{
				this.setEffectScale(this.getEffectScale() - 20);
    			if(this.getEffectScale() == 199.0F)
    			{
    				UnleashedShaderEffects.addEffect(this.level, "Shockwave", this.position(), 100, 1.0F);
    	    		this.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_EXPLOSION.get());
    			}
			}
			else
			{
				this.setEffectScale(this.swell);
			}

    		if(!this.isSecondPhase() && !this.isAlive() && this.swell < 250)
			{
    			Vec3 spreadPos = UnleashedUtil.getSpreadPosition(this, new Vec3(50, 15, 50));
				EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), this.level);
				orb.setPos(spreadPos);
				orb.setDeltaMovement(UnleashedUtil.getVelocityTowards(spreadPos, this.position(), 1.0F));
				orb.setOwner(this);
				orb.setTrail(true);
				this.level.addFreshEntity(orb);
			}
			
			if(this.swell >= this.maxSwell && this.getEffectScale() <= 0.0F)
			{
				this.swell = 0;
	    		this.setAnimationState(0);
	    		this.setShowEffect(false);
	    		this.setStarfield(true);
                this.bossEvent.setVisible(true);
	    		if(!this.isSecondPhase() && !this.isAlive())
	    		{
	    			this.setSecondPhase(true);
	    			this.setHealth(this.getMaxHealth());
	    			this.doTeleport();
	    		}
			}
		}
    }
    
    @Override
    public void die(DamageSource pDamageSource)
    {
		this.setStarfield(false);
    	if(this.isSecondPhase())
    	{
        	super.die(pDamageSource);
    	}
    }
    
    @Override
    public void remove(RemovalReason pReason)
    {
    	if(!this.isClone())
    	{
    		if(pReason == RemovalReason.KILLED || pReason == RemovalReason.DISCARDED)
    		{
        		this.setStarfield(false);
        		this.setJellyfishSpawned(false);
    		}
    	}
    	super.remove(pReason);
    }
    
    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer)
    {
    	return false;
    }
    
    @Override
    protected void tickDeath()
    {
    	if(this.isSecondPhase())
    	{
        	super.tickDeath();
    	}
    	else
    	{
    		this.phaseTime++;
    		this.setShowWarning(false);
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
            this.bossEvent.setVisible(false);
    		if(this.phaseTime == 200)
    		{
    			this.setAnimationState(1);
    			this.setShowEffect(true);
        		this.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_TRANSFORM.get());
    		}
    	}
    }
    
    public void setJellyfishSpawned(boolean value)
    {
		if(!this.level.isClientSide && this.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
		{
			UnleashedSavedData data = UnleashedSavedData.get(this.level);
			if(data != null)
			{
				data.setJellyfishSpawned(value);
			}
		}
    }
    
    public void setStarfield(boolean value)
    {
		if(!this.level.isClientSide && this.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
		{
			UnleashedSavedData data = UnleashedSavedData.get(this.level);
			if(data != null)
			{
				data.setStarfield(value);
			}
		}
    }
    
    public void tickTeleport()
    {
    	if(this.isTeleport())
    	{
    		this.setEffectScale(this.getEffectScale() + (20 * this.getScaleDirection()));
    		this.setEffectScale(Mth.clamp(this.getEffectScale(), 0.0F, 200.0F));
    		this.setDeltaMovement(Vec3.ZERO);
    		
    		if(this.getScaleDirection() == -1)
    		{
    			if(!this.isVisible())
    			{
    				if(this.getEffectScale() == 60.0F)
    				{
        				this.setShowEffect(false);
        				this.setHitTime(false);
        				this.setHitTime(0);
    				}
        			if(this.getEffectScale() == 40.0F)
        			{
        				if(this.getTarget() != null)
        				{
        					float yRot = this.getRandom().nextFloat() * 360.0F;
        					Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.getTarget().getYHeadRot() + yRot), this.getTarget().position(), 0, 0, this.getRandom().nextInt(10, 20));
        					if(this.isFloat())
        					{
        						lookPos = lookPos.add(0, 4.5F, 0);
        						this.setFloat(false);
        					}
            				this.moveTo(lookPos);
        				}
        				this.setMove(true);
        				if(this.isClone() && this.getOwner() != null && !this.level.isClientSide && this.goal == null)
        				{
        					this.discard();
        				}
        			}
        			if(this.getEffectScale() <= 0.0F)
        			{
        				this.setShowEffect(true);
        				this.setScaleDirection(1);
        			}
    			}
    			else
    			{
        			if(this.getEffectScale() <= 0.0F)
        			{
        				this.setScaleDirection(1);
        				this.setTeleport(false);
        				this.setShowEffect(false);
        				this.setMove(false);
        				this.setEnd(true);
        			}
    			}
    		}
    		else
    		{
    			if(this.getEffectScale() == 180.0F)
    			{
    				this.explosion();
    	    		this.setVisible(!this.isVisible());
    			}
    			if(this.getEffectScale() >= 200.0F)
    			{
    				this.setScaleDirection(-1);
    			}
    		}
    	}
    }
    
    public void explosion()
    {
    	Vec3 pos = this.position();
    	if(this.isHitTime())
    	{
    		pos = pos.add(0, 4.5F, 0);
    	}
		UnleashedShaderEffects.addEffect(this.level, "Shockwave", pos, 100, 1.0F);
		EntityCameraShake.cameraShake(this.level, this.position(), 100.0F, 0.1F, 0, 20);
		this.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_EXPLOSION.get());
    }
    
    public void doTeleport()
    {
		this.setShowEffect(true);
		this.setTeleport(true);
		this.setVisible(true);
		this.setDeltaMovement(Vec3.ZERO);
    }
    
    public float getSwelling(float partialTicks) 
    {
    	return Mth.lerp(partialTicks, this.oldSwell, this.swell) / (this.maxSwell - 2);
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) 
    {
    	return UnleashedSounds.CELESTIAL_JELLYFISH_HURT.get();
    }
    
    @Override
    protected SoundEvent getDeathSound() 
    {
    	return UnleashedSounds.CELESTIAL_JELLYFISH_HURT.get();
    }
    
    @Override
    public boolean hurt(DamageSource pSource, float pAmount)
    {
    	if(pSource.is(DamageTypeTags.IS_EXPLOSION))
    	{
    		return false;
    	}
    	if(!pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
    	{
        	if(pSource.getDirectEntity() != null && this.getAnimationState() == 0 && !this.isTransform())
        	{
        		this.setAnimationState(1);
        		this.setShowEffect(true);
        		this.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_TRANSFORM.get());
        		return false;
        	}
    		if(!this.isVisible() || this.getAnimationState() == 1 || this.getAnimationState() == 3 || this.isClone() || this.isTeleport())
    		{
    			return false;
    		}
    		else if(this.isSecondPhase() && this.getHealth() - pAmount <= 1.0F && !this.isFinalPhase())
    		{
    			if(pSource.getDirectEntity() != null)
    			{
        			this.setHealth(1.0F);
        			this.doTeleport();
        			return false;
    			}
    		}
    	}
    	return super.hurt(pSource, pAmount);
    }
    
    @Override
    public EntityDimensions getDimensions(Pose pPose) 
    {
    	if(this.isTransform())
    	{
    		if(this.isHitTime())
    		{
        		return EntityDimensions.fixed(3.0F, 7.875F);
    		}
    		return EntityDimensions.fixed(3.0F, 2.0F);
    	}
    	return super.getDimensions(pPose);
    }
    
    @Override
    public Component getTypeName() 
    {
    	if(!this.isTransform())
    	{
    		return Component.translatable("entity.bossesunleashed.jellyfish");
    	}
    	return super.getTypeName();
    }
    
    @Override
    public float getOffset() 
    {
    	if(this.isHitTime())
    	{
    		return 4.5F;
    	}
    	return 0;
    }
    
    @Override
    public boolean displayFireAnimation() 
    {
    	return false;
    }
    
    @Override
    public boolean shouldApplyEffect() 
    {
    	return this.entityData.get(SHOW_EFFECT);
    }
    
    @Override
    public boolean cameraRotation()
    {
    	return this.getAnimationState() != 1;
    }
    
    @Override
    public String getEffetName() 
    {
    	return "Galaxy";
    }
   
    @Override
    public int getEffectTickCount() 
    {
    	if(this.getAnimationState() == 1)
    	{
        	return this.swell;
    	}
    	return this.getAnimationTick();
    }
    
    @Override
    public float getEffectScale() 
    {
    	return this.entityData.get(EFFECT_SCALE);
    }
     
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) 
    {
    	super.readAdditionalSaveData(pCompound);
    	this.setTransform(pCompound.getBoolean("isTransform"));
    	this.bossEvent.setVisible(pCompound.getBoolean("isTransform"));
    	this.setShowEffect(pCompound.getBoolean("ShowEffect"));
    	this.setVisible(pCompound.getBoolean("isVisible"));
    	this.setShowWarning(pCompound.getBoolean("ShowWarning"));
    	this.setShowAfterImage(pCompound.getBoolean("ShowAfterImage"));
    	this.setHitTime(pCompound.getBoolean("isHitTime"));
    	this.setTeleport(pCompound.getBoolean("isTeleport"));
    	this.setRewind(pCompound.getBoolean("isRewind"));
    	this.setMove(pCompound.getBoolean("isMove"));
    	this.setEnd(pCompound.getBoolean("isEnd"));
    	this.setFloat(pCompound.getBoolean("isFloat"));
    	this.setClone(pCompound.getBoolean("isClone"));
    	this.setSecondPhase(pCompound.getBoolean("isSecondPhase"));
    	this.setFinalPhase(pCompound.getBoolean("isFinalPhase"));
    	this.setEffectScale(pCompound.getFloat("EffectScale"));
    	this.setScaleDirection(pCompound.getInt("ScaleDirection"));
    	this.setHitTime(pCompound.getInt("HitTime"));
    	this.phaseTime = pCompound.getInt("PhaseTime");
    	this.swell = pCompound.getInt("Swell");
		if(pCompound.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(pCompound.getUUID("Owner")));
		}
        if(this.hasCustomName()) 
        {
        	this.bossEvent.setName(this.getDisplayName());
        }
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound)
    {
    	super.addAdditionalSaveData(pCompound);
    	pCompound.putBoolean("isTransform", this.isTransform());
    	pCompound.putBoolean("ShowEffect", this.shouldApplyEffect());
    	pCompound.putBoolean("isVisible", this.isVisible());
    	pCompound.putBoolean("ShowWarning", this.showWarning());
    	pCompound.putBoolean("ShowAfterImage", this.showAfterImage());
    	pCompound.putBoolean("isHitTime", this.isHitTime());
    	pCompound.putBoolean("isTeleport", this.isTeleport());
    	pCompound.putBoolean("isRewind", this.isRewind());
    	pCompound.putBoolean("isMove", this.isMove());
    	pCompound.putBoolean("isEnd", this.isEnd());
    	pCompound.putBoolean("isFloat", this.isFloat());
    	pCompound.putBoolean("isClone", this.isClone());
    	pCompound.putBoolean("isSecondPhase", this.isSecondPhase());
    	pCompound.putBoolean("isFinalPhase", this.isFinalPhase());
    	pCompound.putFloat("EffectScale", this.getEffectScale());
    	pCompound.putInt("ScaleDirection", this.getScaleDirection());
    	pCompound.putInt("HitTime", this.getHitTime());
    	pCompound.putInt("PhaseTime", this.phaseTime);
    	pCompound.putInt("Swell", this.swell);
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			pCompound.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
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
        if(!this.isClone())
        {
            this.bossEvent.addPlayer(pServerPlayer);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer)
    {
    	super.stopSeenByPlayer(pServerPlayer);
    	this.bossEvent.removePlayer(pServerPlayer);
    }
    
	public void setOwner(EntityCelestialJellyfish owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Nullable
	public EntityCelestialJellyfish getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return UnleashedUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
    
    public void setLastLookPos(Vec3 pos)
    {
    	this.entityData.set(LAST_LOOK_POS, pos);
    }
    
    public Vec3 getLastLookPos()
    {
    	return this.entityData.get(LAST_LOOK_POS);
    }
    
    public void setHitTime(int value)
    {
    	this.entityData.set(HIT_TIME, value);
    }
    
    public int getHitTime()
    {
    	return this.entityData.get(HIT_TIME);
    }
    
    public void setScaleDirection(int value)
    {
    	this.entityData.set(SCALE_DIR, value);
    }
    
    public int getScaleDirection()
    {
    	return this.entityData.get(SCALE_DIR);
    }
    
    public void setEffectScale(float value)
    {
    	this.entityData.set(EFFECT_SCALE, value);
    }
    
    public void setFinalPhase(boolean value)
    {
    	this.entityData.set(IS_FINAL_PHASE, value);
    }
    
    public boolean isFinalPhase()
    {
    	return this.entityData.get(IS_FINAL_PHASE);
    }
    
    public void setSecondPhase(boolean value)
    {
    	this.entityData.set(IS_SECOND_PHASE, value);
    }
    
    public boolean isSecondPhase()
    {
    	return this.entityData.get(IS_SECOND_PHASE);
    }
    
    public void setClone(boolean value)
    {
    	this.entityData.set(IS_CLONE, value);
    }
    
    public boolean isClone()
    {
    	return this.entityData.get(IS_CLONE);
    }
    
    public void setFloat(boolean value)
    {
    	this.entityData.set(IS_FLOAT, value);
    }
    
    public boolean isFloat()
    {
    	return this.entityData.get(IS_FLOAT);
    }
    
    public void setEnd(boolean value)
    {
    	this.entityData.set(IS_END, value);
    }
    
    public boolean isEnd()
    {
    	return this.entityData.get(IS_END);
    }
    
    public void setMove(boolean value)
    {
    	this.entityData.set(IS_MOVE, value);
    }
    
    public boolean isMove()
    {
    	return this.entityData.get(IS_MOVE);
    }
    
    public void setRewind(boolean value)
    {
    	this.entityData.set(IS_REWIND, value);
    }
    
    public boolean isRewind()
    {
    	return this.entityData.get(IS_REWIND);
    }
    
    public void setTeleport(boolean value)
    {
    	this.entityData.set(IS_TELEPORT, value);
    }
    
    public boolean isTeleport()
    {
    	return this.entityData.get(IS_TELEPORT);
    }
    
    public void setHitTime(boolean value)
    {
    	this.entityData.set(IS_HIT_TIME, value);
    }
    
    public boolean isHitTime()
    {
    	return this.entityData.get(IS_HIT_TIME);
    }
    
    public void setShowAfterImage(boolean value)
    {
    	this.entityData.set(SHOW_AFTER_IMAGE, value);
    }
    
    public boolean showAfterImage()
    {
    	return this.entityData.get(SHOW_AFTER_IMAGE);
    }
    
    public void setShowWarning(boolean value)
    {
    	this.entityData.set(SHOW_WARNING, value);
    }
    
    public boolean showWarning()
    {
    	return this.entityData.get(SHOW_WARNING);
    }
    
    public void setVisible(boolean value)
    {
    	this.entityData.set(IS_VISIBLE, value);
    }
    
    public boolean isVisible()
    {
    	return this.entityData.get(IS_VISIBLE);
    }
    
    public void setShowEffect(boolean value)
    {
    	this.entityData.set(SHOW_EFFECT, value);
    }
    
    public void setTransform(boolean value)
    {
    	this.entityData.set(IS_TRANSFORM, value);
    	this.bossEvent.setName(this.getDisplayName());
    }
    
    public boolean isTransform()
    {
    	return this.entityData.get(IS_TRANSFORM);
    }
}

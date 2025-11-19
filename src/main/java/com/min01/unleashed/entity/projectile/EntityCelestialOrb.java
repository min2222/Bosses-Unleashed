package com.min01.unleashed.entity.projectile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.unleashed.entity.ITrail;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.particle.UnleashedParticles;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EntityCelestialOrb extends ThrowableProjectile implements ITrail
{
	public static final EntityDataAccessor<Boolean> IS_TRAIL = SynchedEntityData.defineId(EntityCelestialOrb.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_REWIND = SynchedEntityData.defineId(EntityCelestialOrb.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityCelestialOrb.class, EntityDataSerializers.OPTIONAL_UUID);
	
    private Vec3[] trailPositions = new Vec3[64];
    private int trailPointer = -1;
    
	public EntityCelestialOrb(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_) 
	{
		super(p_37466_, p_37467_);
		this.setNoGravity(true);
	}

	@Override
	protected void defineSynchedData() 
	{
		this.entityData.define(IS_TRAIL, false);
		this.entityData.define(IS_REWIND, false);
		this.entityData.define(OWNER_UUID, Optional.empty());
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.tickTrail();
		}
		if(this.isTrail())
		{
			if(this.getOwner() instanceof EntityCelestialJellyfish jellyfish)
			{
				if(!this.isRewind())
				{
					if(jellyfish.isRewind() && jellyfish.isAlive())
					{
						this.setDeltaMovement(this.getDeltaMovement().scale(-(this.distanceToXZ(jellyfish) * 0.05F)));
						this.setDeltaMovement(this.getDeltaMovement().scale(0.1F));
						this.setRewind(true);
					}
					if(!jellyfish.isSecondPhase() && !jellyfish.isAlive())
					{
						if(this.distanceToXZ(jellyfish) <= 2.5F)
						{
							this.discard();
						}
					}
				}
				else
				{
					this.setDeltaMovement(this.getDeltaMovement().scale(1.05F));
					if(this.distanceToXZ(jellyfish) <= 3.5F)
					{
						this.discard();
					}
				}
			}
			if(this.tickCount >= 350)
			{
				this.discard();
			}
		}
		else
		{
			if(this.tickCount == 38)
			{
				this.playSound(SoundEvents.GENERIC_EXPLODE);
				this.level.broadcastEntityEvent(this, (byte) 99);
			}
			if(this.tickCount >= 40)
			{
				List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5.0F), this::canExplode);
				list.forEach(t ->
				{
					t.hurt(this.damageSources().explosion(this, this.getOwner()), 8.0F);
				});
				this.discard();
			}
			if(this.tickCount >= 200)
			{
				this.discard();
			}
		}
		if(this.getOwner() == null || this.getOwner().isRemoved())
		{
			this.discard();
		}
	}
	
	public boolean canExplode(Entity entity)
	{
		if(this.getOwner() != null)
		{
			if(this.getOwner() instanceof EntityCelestialJellyfish owner && entity instanceof EntityCelestialJellyfish jellyfish)
			{
				if(owner.isClone() && jellyfish.isClone())
				{
					if(owner.getOwner() != null && jellyfish.getOwner() != null)
					{
						if(owner.getOwner() == jellyfish.getOwner())
						{
							return false;
						}
					}
				}
				if(owner.isClone() && owner.getOwner() == jellyfish)
				{
					return false;
				}
				if(jellyfish.isClone() && jellyfish.getOwner() == this.getOwner())
				{
					return false;
				}
			}
			return entity != this.getOwner() && !entity.isAlliedTo(this.getOwner());
		}
		return true;
	}
	
	public float distanceToXZ(Entity entity)
	{
		float x = (float)(this.getX() - entity.getX());
		float z = (float)(this.getZ() - entity.getZ());
		return Mth.sqrt(x * x + z * z);
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) 
	{
		super.onHitEntity(p_37259_);
		if(this.canExplode(p_37259_.getEntity()))
		{
			this.playSound(SoundEvents.GENERIC_EXPLODE);
			this.level.broadcastEntityEvent(this, (byte) 99);
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5.0F), this::canExplode);
			list.forEach(t ->
			{
				t.hurt(this.damageSources().explosion(this, this.getOwner()), 8.0F);
			});
			this.discard();
		}
	}
	
	@Override
	public void handleEntityEvent(byte p_19882_) 
	{
		super.handleEntityEvent(p_19882_);
		if(p_19882_ == 99)
		{
			this.level.addAlwaysVisibleParticle(UnleashedParticles.CELESTIAL_EXPLOSION.get(), this.getX(), this.getY() + 0.5F, this.getZ(), 0, 0, 0);
		}
	}
	
	@Override
	public boolean isInWater() 
	{
		return false;
	}
	
	@Override
	protected void addAdditionalSaveData(CompoundTag p_37265_)
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putBoolean("isTrail", this.isTrail());
		p_37265_.putBoolean("isRewind", this.isRewind());
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			p_37265_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	protected void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		this.setTrail(p_37262_.getBoolean("isTrail"));
		this.setRewind(p_37262_.getBoolean("isRewind"));
		if(p_37262_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37262_.getUUID("Owner")));
		}
	}
	
	@Override
	public void setOwner(Entity owner)
	{
		if(owner == null)
		{
			this.entityData.set(OWNER_UUID, Optional.empty());
		}
		else
		{
			this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
		}
	}
	
	@Nullable
	@Override
	public Entity getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return UnleashedUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
	
	public void setRewind(boolean value)
	{
		this.entityData.set(IS_REWIND, value);
	}
	
	public boolean isRewind()
	{
		return this.entityData.get(IS_REWIND);
	}
	
	public void setTrail(boolean value)
	{
		this.entityData.set(IS_TRAIL, value);
	}
	
	public boolean isTrail()
	{
		return this.entityData.get(IS_TRAIL);
	}
	
    public boolean hasTrail() 
    {
        return this.trailPointer != -1 && this.isTrail();
    }
	
    public void tickTrail() 
    {
        Vec3 currentPosition = this.position();
        if(this.trailPointer == -1) 
        {
            for(int i = 0; i < this.trailPositions.length; i++)
            {
            	this.trailPositions[i] = currentPosition;
            }
        }
        if(++this.trailPointer == this.trailPositions.length)
        {
        	this.trailPointer = 0;
        }
        this.trailPositions[this.trailPointer] = currentPosition;
    }
	
    @Override
    public Vec3 getTrailPosition(int pointer, float partialTick)
    {
        if(this.isRemoved())
        {
            partialTick = 1.0F;
        }
        int i = this.trailPointer - pointer & 63;
        int j = this.trailPointer - pointer - 1 & 63;
        Vec3 d0 = this.trailPositions[j];
        Vec3 d1 = this.trailPositions[i].subtract(d0);
        return d0.add(d1.scale(partialTick));
    }
}


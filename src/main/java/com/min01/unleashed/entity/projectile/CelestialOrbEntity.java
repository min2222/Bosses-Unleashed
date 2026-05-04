package com.min01.unleashed.entity.projectile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.unleashed.entity.ITrail;
import com.min01.unleashed.entity.living.CelestialJellyfishEntity;
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

public class CelestialOrbEntity extends ThrowableProjectile implements ITrail
{
	public static final EntityDataAccessor<Boolean> IS_TRAIL = SynchedEntityData.defineId(CelestialOrbEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_REWIND = SynchedEntityData.defineId(CelestialOrbEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(CelestialOrbEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	
    private Vec3[] trailPositions = new Vec3[64];
    private int trailPointer = -1;
    
	public CelestialOrbEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
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
			if(this.getOwner() instanceof CelestialJellyfishEntity jellyfish)
			{
				if(!this.isRewind())
				{
					if(jellyfish.isRewind && jellyfish.isAlive())
					{
						this.setDeltaMovement(this.getDeltaMovement().scale(-(this.distanceToXZ(jellyfish) * 0.05F)));
						this.setDeltaMovement(this.getDeltaMovement().scale(0.1F));
						this.setRewind(true);
					}
					if(jellyfish.getPhase() != 2 && !jellyfish.isAlive())
					{
						if(this.distanceToXZ(jellyfish) <= 3.0F)
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
			if(this.tickCount >= 300 || this.touchingUnloadedChunk())
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
					t.hurt(this.damageSources().indirectMagic(this, this.getOwner()), 35.0F);
				});
				this.discard();
			}
		}
		if(this.getOwner() == null || this.getOwner().isRemoved())
		{
			this.discard();
		}
	}
	
	@Override
	public boolean displayFireAnimation() 
	{
		return false;
	}
	
	public boolean canExplode(Entity entity)
	{
		if(this.getOwner() != null)
		{
			if(this.getOwner() instanceof CelestialJellyfishEntity owner && entity instanceof CelestialJellyfishEntity jellyfish)
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
	protected void onHitEntity(EntityHitResult pResult) 
	{
		super.onHitEntity(pResult);
		if(this.canExplode(pResult.getEntity()))
		{
			this.playSound(SoundEvents.GENERIC_EXPLODE);
			this.level.broadcastEntityEvent(this, (byte) 99);
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5.0F), this::canExplode);
			list.forEach(t ->
			{
				t.hurt(this.damageSources().explosion(this, this.getOwner()), 20.0F);
			});
			this.discard();
		}
	}
	
	@Override
	public void handleEntityEvent(byte pId) 
	{
		super.handleEntityEvent(pId);
		if(pId == 99)
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
	protected void addAdditionalSaveData(CompoundTag pCompound)
	{
		super.addAdditionalSaveData(pCompound);
		pCompound.putBoolean("isTrail", this.isTrail());
		pCompound.putBoolean("isRewind", this.isRewind());
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			pCompound.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	protected void readAdditionalSaveData(CompoundTag pCompound) 
	{
		super.readAdditionalSaveData(pCompound);
		this.setTrail(pCompound.getBoolean("isTrail"));
		this.setRewind(pCompound.getBoolean("isRewind"));
		if(pCompound.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(pCompound.getUUID("Owner")));
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


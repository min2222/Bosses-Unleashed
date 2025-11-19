package com.min01.unleashed.entity.projectile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.min01.unleashed.entity.AbstractOwnableEntity;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityCelestialBeam extends AbstractOwnableEntity<EntityCelestialJellyfish>
{
	public static final EntityDataAccessor<Float> YAW = SynchedEntityData.defineId(EntityCelestialBeam.class, EntityDataSerializers.FLOAT);
	public static final EntityDataAccessor<Float> PITCH = SynchedEntityData.defineId(EntityCelestialBeam.class, EntityDataSerializers.FLOAT);
    
    public Vec3 endPos = Vec3.ZERO;
    public Vec3 collidePos = Vec3.ZERO;
    
	public EntityCelestialBeam(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
		this.noCulling = true;
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(YAW, 0.0F);
		this.entityData.define(PITCH, 0.0F);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.calculateEndPos();
		List<LivingEntity> hit = this.raytraceEntities(this.level, this.position(), this.endPos).entities;
		hit.forEach(t -> 
		{
			t.hurt(this.damageSources().indirectMagic(this, this.getOwner()), 6.0F);
		});
		if(this.tickCount >= 500 || this.getOwner() == null)
		{
			this.discard();
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putFloat("Yaw", this.getYaw());
		p_37265_.putFloat("Pitch", this.getPitch());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_)
	{
		super.readAdditionalSaveData(p_37262_);
		this.setYaw(p_37262_.getFloat("Yaw"));
		this.setPitch(p_37262_.getFloat("Pitch"));
	}
	
    @Override
    public boolean shouldRenderAtSqrDistance(double distance)
    {
        return true;
    }
	
    public float getYaw() 
    {
        return this.entityData.get(YAW);
    }

    public void setYaw(float yaw) 
    {
    	this.entityData.set(YAW, yaw);
    }

    public float getPitch()
    {
        return this.entityData.get(PITCH);
    }

    public void setPitch(float pitch)
    {
    	this.entityData.set(PITCH, pitch);
    }
	
    public void calculateEndPos()
    {
        double radius = 30;
        this.endPos = this.position().add(radius * Math.cos(this.getYaw()) * Math.cos(this.getPitch()), radius * Math.sin(this.getPitch()), radius * Math.sin(this.getYaw()) * Math.cos(this.getPitch()));
    }
	
    public BeamHitResult raytraceEntities(Level world, Vec3 from, Vec3 to) 
    {
    	BeamHitResult result = new BeamHitResult();
        //result.setBlockHit(world.clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)));
        if(result.blockHit != null)
        {
            this.collidePos = result.blockHit.getLocation();
        }
        else 
        {
        	this.collidePos = this.endPos;
        }
        AABB aabb = new AABB(Math.min(this.getX(), this.collidePos.x), Math.min(this.getY(), this.collidePos.y), Math.min(this.getZ(), this.collidePos.z), Math.max(this.getX(), this.collidePos.x), Math.max(this.getY(), this.collidePos.y), Math.max(this.getZ(), this.collidePos.z));
        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, aabb.inflate(1.0F), t -> t != this.getOwner());
        for(LivingEntity entity : entities)
        {
            float pad = entity.getPickRadius() + 0.5F;
            AABB aabb2 = entity.getBoundingBox().inflate(pad);
            Optional<Vec3> hit = aabb2.clip(from, to);
            if(aabb2.contains(from))
            {
                result.addEntityHit(entity);
            }
            else if(hit.isPresent()) 
            {
                result.addEntityHit(entity);
            }
        }
        return result;
    }
	
    public static class BeamHitResult
    {
        private BlockHitResult blockHit;
        private final List<LivingEntity> entities = new ArrayList<>();

        public BlockHitResult getBlockHit() 
        {
            return this.blockHit;
        }

        public void setBlockHit(HitResult rayTraceResult) 
        {
            if(rayTraceResult.getType() == HitResult.Type.BLOCK)
            {
                this.blockHit = (BlockHitResult) rayTraceResult;
            }
        }

        public void addEntityHit(LivingEntity entity) 
        {
            this.entities.add(entity);
        }
    }
}

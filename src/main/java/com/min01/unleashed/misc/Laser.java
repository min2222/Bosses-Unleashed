package com.min01.unleashed.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Laser
{
    public LaserHitResult raytrace(Level world, Vec3 from, Vec3 to, double radius, Predicate<? super Entity> predicate, @Nullable Entity entity) 
    {
    	return this.raytrace(world, from, to, radius, predicate, false, entity);
    }
    
    public LaserHitResult raytrace(Level world, Vec3 from, Vec3 to, double radius, Predicate<? super Entity> predicate, boolean ignoreBlocks, @Nullable Entity entity) 
    {
    	LaserHitResult result = new LaserHitResult();
        result.setBlockHit(world.clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)));
        if(result.blockHit != null && !ignoreBlocks)
        {
        	result.collidePos = result.blockHit.getLocation();
        }
        else 
        {
        	result.collidePos = to;
        }
        AABB aabb = new AABB(from, result.collidePos);
        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, aabb.inflate(radius), predicate);
        for(LivingEntity living : entities)
        {
            AABB aabb2 = living.getBoundingBox().inflate(living.getPickRadius() + radius);
            Optional<Vec3> hit = aabb2.clip(from, to);
            if(aabb2.contains(from))
            {
                result.addEntityHit(living);
            }
            else if(hit.isPresent()) 
            {
                result.addEntityHit(living);
            }
        }
        return result;
    }
	
    public static class LaserHitResult
    {
    	public BlockHitResult blockHit;
    	public Vec3 collidePos;
    	public final List<LivingEntity> entities = new ArrayList<>();

        public void setBlockHit(HitResult hitResult) 
        {
            if(hitResult.getType() == HitResult.Type.BLOCK)
            {
                this.blockHit = (BlockHitResult) hitResult;
            }
        }
        
        public float getLaserLength()
        {
            return (float) Math.sqrt(Math.pow(this.collidePos.x, 2) + Math.pow(this.collidePos.y, 2) + Math.pow(this.collidePos.z, 2));
        }

        public void addEntityHit(LivingEntity entity) 
        {
            this.entities.add(entity);
        }
    }
}

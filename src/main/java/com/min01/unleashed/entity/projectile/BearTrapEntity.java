package com.min01.unleashed.entity.projectile;

import java.util.List;

import com.min01.unleashed.entity.AbstractOwnableEntity;
import com.min01.unleashed.entity.living.MadLumberjackEntity;
import com.min01.unleashed.misc.SmoothAnimationState;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class BearTrapEntity extends AbstractOwnableEntity<MadLumberjackEntity>
{
	public static final EntityDataAccessor<Boolean> TRIGGERED = SynchedEntityData.defineId(BearTrapEntity.class, EntityDataSerializers.BOOLEAN);
	
	public final SmoothAnimationState trapAnimationState = new SmoothAnimationState();
	
	public BearTrapEntity(EntityType<?> pEntityType, Level pLevel)
	{
		super(pEntityType, pLevel);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(TRIGGERED, false);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.trapAnimationState.updateWhen(this.isTriggered(), this.tickCount);
		}
		
		if(!this.isTriggered())
		{
			float size = this.getBbWidth() / 2.0F;
			AABB aabb = new AABB(-size, 0.0F, -size, size, this.getBbHeight(), size).move(this.position());
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, aabb, EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(t -> this.getOwner() != null ? !t.isAlliedTo(this.getOwner()) && t != this.getOwner() : true));
			list.forEach(t -> 
			{
				this.trigger(t);
			});
		}
		int tick = this.isTriggered() ? 100 : 500;
		if(this.tickCount >= tick || this.getOwner() == null)
		{
			this.discard();
		}
	}
	
	public void trigger(LivingEntity living)
	{
		if(this.getOwner() != null)
		{
			living.hurt(this.damageSources().mobAttack(this.getOwner()), this.random.nextInt(2, 4));
		}
		this.setTriggered(true);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) 
	{
		super.addAdditionalSaveData(pCompound);
		pCompound.putBoolean("isTriggered", this.isTriggered());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) 
	{
		super.readAdditionalSaveData(pCompound);
		this.setTriggered(pCompound.getBoolean("isTriggered"));
	}
	
	public void setTriggered(boolean value)
	{
		this.entityData.set(TRIGGERED, value);
	}
	
	public boolean isTriggered()
	{
		return this.entityData.get(TRIGGERED);
	}
}

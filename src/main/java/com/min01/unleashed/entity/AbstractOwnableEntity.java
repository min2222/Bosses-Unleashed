package com.min01.unleashed.entity;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public abstract class AbstractOwnableEntity<T extends Entity> extends Entity
{
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(AbstractOwnableEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	
	public AbstractOwnableEntity(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
	}
	
	@Override
	protected void defineSynchedData()
	{
		this.entityData.define(OWNER_UUID, Optional.empty());
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			p_37265_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		if(p_37262_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37262_.getUUID("Owner")));
		}
	}
	
	public void setOwner(T owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Nullable
	public T getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return UnleashedUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

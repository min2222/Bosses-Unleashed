package com.min01.unleashed.entity;

import java.util.List;

import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.util.UnleashedUtil;
import com.min01.unleashed.world.UnleashedSavedData;
import com.min01.unleashed.world.UnleashedWorlds;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EntityWormhole extends Entity implements IShaderEffect
{
	public static final EntityDataAccessor<Boolean> CAN_DISAPPEAR = SynchedEntityData.defineId(EntityCelestialJellyfish.class, EntityDataSerializers.BOOLEAN);
	
	public int disapperTick;
	
	public EntityWormhole(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
		this.noCulling = true;
	}

	@Override
	protected void defineSynchedData() 
	{
		this.entityData.define(CAN_DISAPPEAR, false);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) 
	{
		this.setDisappear(p_20052_.getBoolean("CanDisappear"));
		this.disapperTick = p_20052_.getInt("DisappearTick");
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) 
	{
		p_20139_.putBoolean("CanDisappear", this.canDisappear());
		p_20139_.putInt("DisappearTick", this.disapperTick);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.canDisappear())
		{
			this.disapperTick++;
			if(this.disapperTick >= 200)
			{
				this.discard();
			}
		}
		if(!this.level.isClientSide)
		{
			List<Player> player = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(1.5F));
			player.forEach(t ->
			{
				if(t instanceof ServerPlayer serverPlayer)
				{
					UnleashedSavedData data = UnleashedSavedData.get(this.getServer().getLevel(Level.OVERWORLD));
					if(this.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
					{
						UnleashedUtil.teleportEntityToDimension(t, this.getServer().getLevel(data.getPrevDimension(serverPlayer.getUUID())), data.getPrevPos(serverPlayer.getUUID()));
						serverPlayer.setRespawnPosition(data.getRespawnDimension(serverPlayer.getUUID()), data.getRespawnPos(serverPlayer.getUUID()).equals(BlockPos.ZERO) ? null : data.getRespawnPos(serverPlayer.getUUID()), serverPlayer.getRespawnAngle(), serverPlayer.isRespawnForced(), false);
					}
					else
					{
						data.setPrevPos(serverPlayer.getUUID(), serverPlayer.blockPosition());
						data.setPrevDimension(serverPlayer.getUUID(), serverPlayer.level.dimension());
						data.setRespawnDimension(serverPlayer.getUUID(), serverPlayer.getRespawnDimension());
						if(serverPlayer.getRespawnPosition() != null)
						{
							data.setRespawnPos(serverPlayer.getUUID(), serverPlayer.getRespawnPosition());
						}
						BlockPos blockPos = BlockPos.containing(0, 64, 0);
						UnleashedUtil.teleportEntityToDimension(t, this.getServer().getLevel(UnleashedWorlds.CELESTIAL_FIELD), blockPos);
						serverPlayer.setRespawnPosition(UnleashedWorlds.CELESTIAL_FIELD, blockPos, serverPlayer.getRespawnAngle(), serverPlayer.isRespawnForced(), false);
						this.setDisappear(true);
					}
					if(this.level.players().size() < 1)
					{
						this.discard();
					}
				}
			});
		}
	}
	
	@Override
	public String getEffetName() 
	{
		return "Wormhole";
	}
	
	@Override
	public float getOffset() 
	{
		return this.getEyeHeight() - 0.25F;
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	public void setDisappear(boolean value)
	{
		this.entityData.set(CAN_DISAPPEAR, value);
	}
	
	public boolean canDisappear()
	{
		return this.entityData.get(CAN_DISAPPEAR);
	}
}

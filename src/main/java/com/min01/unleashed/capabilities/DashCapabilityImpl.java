package com.min01.unleashed.capabilities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateDashCapabilityPacket;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

public class DashCapabilityImpl implements IDashCapability
{
	public static final Capability<IDashCapability> DASH = CapabilityManager.get(new CapabilityToken<>() {});
	
	private int dashTick;
	private final Entity entity;
	
	public DashCapabilityImpl(Entity entity) 
	{
		this.entity = entity;
	}
	
	@Override
	public CompoundTag serializeNBT() 
	{
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DashTick", this.dashTick);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt)
	{
		this.setDashTick(nbt.getInt("DashTick"));
	}

	@Override
	public void tick(LivingEntity entity) 
	{
		this.setDashTick(this.getDashTick() - 1);
	}

	@Override
	public boolean isDash() 
	{
		return this.dashTick > 0;
	}

	@Override
	public void setDashTick(int tick) 
	{
		this.dashTick = tick;
		this.sendUpdatePacket();
	}

	@Override
	public int getDashTick() 
	{
		return this.dashTick;
	}
	
	private void sendUpdatePacket()
	{
		if(!this.entity.level.isClientSide)
		{
			UnleashedNetwork.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.entity), new UpdateDashCapabilityPacket(this.entity.getUUID(), this.dashTick));
		}
	}
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) 
	{
		return DASH.orEmpty(cap, LazyOptional.of(() -> this));
	}
}

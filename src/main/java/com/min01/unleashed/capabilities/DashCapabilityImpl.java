package com.min01.unleashed.capabilities;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateDashCapabilityPacket;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;

public class DashCapabilityImpl implements IDashCapability
{
	private int dashTick;
	
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
		this.dashTick = nbt.getInt("DashTick");
	}

	@Override
	public void tick(LivingEntity entity) 
	{
		this.dashTick--;
		if(!entity.level.isClientSide)
		{
			UnleashedNetwork.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new UpdateDashCapabilityPacket(entity.getUUID(), this.dashTick));
		}
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
	}

	@Override
	public int getDashTick() 
	{
		return this.dashTick;
	}
}

package com.min01.unleashed.capabilities;

import javax.annotation.Nonnull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class UnleashedCapabilities
{
	public static final Capability<IDashCapability> DASH = CapabilityManager.get(new CapabilityToken<>() {});
	
	public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> e)
	{
		if(e.getObject() instanceof Player) 
		{
			e.addCapability(IDashCapability.ID, new ICapabilitySerializable<CompoundTag>() 
			{
				LazyOptional<IDashCapability> inst = LazyOptional.of(() -> 
				{
					DashCapabilityImpl i = new DashCapabilityImpl();
					return i;
				});

				@Nonnull
				@Override
				public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing) 
				{
					return DASH.orEmpty(capability, this.inst.cast());
				}

				@Override
				public CompoundTag serializeNBT() 
				{
					return this.inst.orElseThrow(NullPointerException::new).serializeNBT();
				}

				@Override
				public void deserializeNBT(CompoundTag nbt)
				{
					this.inst.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
				}
			});
		}
	}
}

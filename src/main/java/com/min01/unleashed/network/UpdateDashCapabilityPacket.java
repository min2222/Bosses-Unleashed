package com.min01.unleashed.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.unleashed.capabilities.UnleashedCapabilities;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class UpdateDashCapabilityPacket 
{
	private final UUID entityUUID;
	private final int dashTick;
	
	public UpdateDashCapabilityPacket(UUID entityUUID, int dashTick) 
	{
		this.entityUUID = entityUUID;
		this.dashTick = dashTick;
	}

	public UpdateDashCapabilityPacket(FriendlyByteBuf buf)
	{
		this.entityUUID = buf.readUUID();
		this.dashTick = buf.readInt();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.entityUUID);
		buf.writeInt(this.dashTick);
	}
	
	public static class Handler 
	{
		public static boolean onMessage(UpdateDashCapabilityPacket message, Supplier<NetworkEvent.Context> ctx) 
		{
			ctx.get().enqueueWork(() ->
			{
				if(ctx.get().getDirection().getReceptionSide().isClient())
				{
					UnleashedUtil.getClientLevel(level -> 
					{
						Entity entity = UnleashedUtil.getEntityByUUID(level, message.entityUUID);
						entity.getCapability(UnleashedCapabilities.DASH).ifPresent(cap -> 
						{
							cap.setDashTick(message.dashTick);
						});
					});
				}
			});

			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}

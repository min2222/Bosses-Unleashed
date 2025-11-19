package com.min01.unleashed.network;

import java.util.function.Supplier;

import com.min01.unleashed.event.ClientEventHandlerForge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class UpdateStarfieldPacket 
{
	public final boolean isStarfield;

	public UpdateStarfieldPacket(boolean isStarfield) 
	{
		this.isStarfield = isStarfield;
	}

	public UpdateStarfieldPacket(FriendlyByteBuf buf)
	{
		this.isStarfield = buf.readBoolean();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeBoolean(this.isStarfield);
	}

	public static class Handler 
	{
		public static boolean onMessage(UpdateStarfieldPacket message, Supplier<NetworkEvent.Context> ctx)
		{
			ctx.get().enqueueWork(() ->
			{
				if(ctx.get().getDirection().getReceptionSide().isClient()) 
				{
					ClientEventHandlerForge.STARFIELD.set(message.isStarfield);
				}
			});
			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}

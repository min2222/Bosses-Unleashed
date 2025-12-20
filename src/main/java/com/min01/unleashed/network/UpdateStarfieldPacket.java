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

	public static UpdateStarfieldPacket read(FriendlyByteBuf buf)
	{
		return new UpdateStarfieldPacket(buf.readBoolean());
	}

	public void write(FriendlyByteBuf buf)
	{
		buf.writeBoolean(this.isStarfield);
	}

	public static boolean handle(UpdateStarfieldPacket message, Supplier<NetworkEvent.Context> ctx)
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

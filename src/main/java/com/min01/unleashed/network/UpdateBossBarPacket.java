package com.min01.unleashed.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.unleashed.misc.UnleashedBossBar;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

public class UpdateBossBarPacket 
{
    private UUID bossID;
    private ResourceLocation name;
    private boolean remove;
	
	public UpdateBossBarPacket(UUID bossID, ResourceLocation name, boolean remove) 
	{
		this.bossID = bossID;
		this.name = name;
		this.remove = remove;
	}

	public static UpdateBossBarPacket read(FriendlyByteBuf buf)
	{
		return new UpdateBossBarPacket(buf.readUUID(), buf.readResourceLocation(), buf.readBoolean());
	}

	public void write(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.bossID);
		buf.writeResourceLocation(this.name);
		buf.writeBoolean(this.remove);
	}
	
	public static boolean handle(UpdateBossBarPacket message, Supplier<NetworkEvent.Context> ctx) 
	{
		ctx.get().enqueueWork(() ->
		{
            if(message.remove) 
            {
            	UnleashedBossBar.BOSS_MAP.remove(message.bossID);
            }
            else
            {
            	UnleashedBossBar.BOSS_MAP.put(message.bossID, message.name);
            }
		});
		ctx.get().setPacketHandled(true);
		return true;
	}
}
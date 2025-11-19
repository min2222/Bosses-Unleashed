package com.min01.unleashed.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.unleashed.event.ClientEventHandlerForge;
import com.min01.unleashed.misc.UnleashedBossBarType;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class UpdateBossBarPacket 
{
    private final UUID bossBar;
    private final UUID entity;
    private final UnleashedBossBarType barType;
	
	public UpdateBossBarPacket(UUID bossBar, UUID entity, UnleashedBossBarType barType) 
	{
		this.bossBar = bossBar;
		this.entity = entity;
		this.barType = barType;
	}

	public UpdateBossBarPacket(FriendlyByteBuf buf)
	{
		this.bossBar = buf.readUUID();
		this.entity = buf.readUUID();
		this.barType = UnleashedBossBarType.values()[buf.readInt()];
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.bossBar);
		buf.writeUUID(this.entity);
		buf.writeInt(this.barType.ordinal());
	}
	
	public static class Handler 
	{
		public static boolean onMessage(UpdateBossBarPacket message, Supplier<NetworkEvent.Context> ctx) 
		{
			ctx.get().enqueueWork(() ->
			{
		        if(message.barType == UnleashedBossBarType.NONE)
		        {
		            ClientEventHandlerForge.BOSS_BAR_MAP.remove(message.bossBar);
		            ClientEventHandlerForge.BOSS_MAP.remove(message.bossBar);
		        }
		        else
		        {
		        	UnleashedUtil.getClientLevel(level -> 
		        	{
			        	ClientEventHandlerForge.BOSS_BAR_MAP.put(message.bossBar, message.barType);
			        	Entity entity = UnleashedUtil.getEntityByUUID(level, message.entity);
			        	if(entity != null)
			        	{
				        	ClientEventHandlerForge.BOSS_MAP.put(message.bossBar, entity);
			        	}
		        	});
		        }
			});

			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
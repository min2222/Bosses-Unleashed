package com.min01.unleashed.network;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class UnleashedNetwork 
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(BossesUnleashed.MODID, "bossesunleashed"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	
	public static int ID = 0;
	public static void registerMessages()
	{
		CHANNEL.registerMessage(ID++, UpdatePosArrayPacket.class, UpdatePosArrayPacket::encode, UpdatePosArrayPacket::new, UpdatePosArrayPacket.Handler::onMessage);
		CHANNEL.registerMessage(ID++, AddShaderEffectPacket.class, AddShaderEffectPacket::encode, AddShaderEffectPacket::new, AddShaderEffectPacket.Handler::onMessage);
		CHANNEL.registerMessage(ID++, UpdateBossBarPacket.class, UpdateBossBarPacket::encode, UpdateBossBarPacket::new, UpdateBossBarPacket.Handler::onMessage);
		CHANNEL.registerMessage(ID++, UpdateDashCapabilityPacket.class, UpdateDashCapabilityPacket::encode, UpdateDashCapabilityPacket::new, UpdateDashCapabilityPacket.Handler::onMessage);
		CHANNEL.registerMessage(ID++, UpdateStarfieldPacket.class, UpdateStarfieldPacket::encode, UpdateStarfieldPacket::new, UpdateStarfieldPacket.Handler::onMessage);
	}
	
    public static <MSG> void sendToServer(MSG message) 
    {
    	CHANNEL.sendToServer(message);
    }
    
    public static <MSG> void sendNonLocal(MSG msg, ServerPlayer player) 
    {
        CHANNEL.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
    
    public static <MSG> void sendToAll(MSG message)
    {
    	for(ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) 
    	{
    		CHANNEL.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    	}
    }
}

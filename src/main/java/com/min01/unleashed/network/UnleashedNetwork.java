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
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, BossesUnleashed.MODID),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	
	public static int ID = 0;
	public static void registerMessages()
	{
		CHANNEL.registerMessage(ID++, UpdatePosArrayPacket.class, UpdatePosArrayPacket::write, UpdatePosArrayPacket::read, UpdatePosArrayPacket::handle);
		CHANNEL.registerMessage(ID++, AddShaderEffectPacket.class, AddShaderEffectPacket::write, AddShaderEffectPacket::read, AddShaderEffectPacket::handle);
		CHANNEL.registerMessage(ID++, UpdateBossBarPacket.class, UpdateBossBarPacket::write, UpdateBossBarPacket::read, UpdateBossBarPacket::handle);
		CHANNEL.registerMessage(ID++, UpdateDashCapabilityPacket.class, UpdateDashCapabilityPacket::write, UpdateDashCapabilityPacket::read, UpdateDashCapabilityPacket::handle);
		CHANNEL.registerMessage(ID++, UpdateStarfieldPacket.class, UpdateStarfieldPacket::write, UpdateStarfieldPacket::read, UpdateStarfieldPacket::handle);
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

package com.min01.unleashed.misc;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateBossBarPacket;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;

public class UnleashedBossEvent extends ServerBossEvent
{
	private final Entity entity;
	
	public UnleashedBossEvent(Component component, Entity entity)
	{
		super(component, BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
		this.entity = entity;
	}
	
	@Override
    public void addPlayer(ServerPlayer serverPlayer) 
    {
    	UnleashedNetwork.sendNonLocal(new UpdateBossBarPacket(this.getId(), ForgeRegistries.ENTITY_TYPES.getKey(this.entity.getType()), false), serverPlayer);
        super.addPlayer(serverPlayer);
    }

    @Override
    public void removePlayer(ServerPlayer serverPlayer) 
    {
    	UnleashedNetwork.sendNonLocal(new UpdateBossBarPacket(this.getId(), ForgeRegistries.ENTITY_TYPES.getKey(this.entity.getType()), true), serverPlayer);
        super.removePlayer(serverPlayer);
    }
}
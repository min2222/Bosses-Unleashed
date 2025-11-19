package com.min01.unleashed.misc;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateBossBarPacket;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;

public class UnleashedBossEvent extends ServerBossEvent
{
	private final UnleashedBossBarType barType;
	private final Entity entity;
	
	public UnleashedBossEvent(Component component, UnleashedBossBarType barType, Entity entity)
	{
		super(component, BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
		this.barType = barType;
		this.entity = entity;
	}
	
	public UnleashedBossBarType getBarType()
	{
		return this.barType;
	}
	
	@Override
    public void addPlayer(ServerPlayer serverPlayer) 
    {
    	UnleashedNetwork.sendNonLocal(new UpdateBossBarPacket(this.getId(), this.entity.getUUID(), this.barType), serverPlayer);
        super.addPlayer(serverPlayer);
    }

    @Override
    public void removePlayer(ServerPlayer serverPlayer) 
    {
    	UnleashedNetwork.sendNonLocal(new UpdateBossBarPacket(this.getId(), this.entity.getUUID(), UnleashedBossBarType.NONE), serverPlayer);
        super.removePlayer(serverPlayer);
    }
}
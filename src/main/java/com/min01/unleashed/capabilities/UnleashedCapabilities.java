package com.min01.unleashed.capabilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class UnleashedCapabilities
{
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event)
	{
    	Entity entity = event.getObject();
		if(entity instanceof Player player)
		{
			event.addCapability(DashCapabilityImpl.ID, new DashCapabilityImpl(player));
		}
	}
}

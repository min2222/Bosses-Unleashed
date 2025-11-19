package com.min01.unleashed.event;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = BossesUnleashed.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler 
{
	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{

	}
	
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) 
    {
    	event.put(UnleashedEntities.CELESTIAL_JELLYFISH.get(), EntityCelestialJellyfish.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event)
    {
    	
    }
}

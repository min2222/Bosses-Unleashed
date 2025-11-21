package com.min01.unleashed;

import com.min01.unleashed.capabilities.UnleashedCapabilities;
import com.min01.unleashed.config.UnleashedConfig;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.item.UnleashedItems;
import com.min01.unleashed.misc.UnleashedCreativeTabs;
import com.min01.unleashed.misc.UnleashedEntityDataSerializers;
import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.particle.UnleashedParticles;
import com.min01.unleashed.sound.UnleashedSounds;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BossesUnleashed.MODID)
public class BossesUnleashed
{
	public static final String MODID = "bossesunleashed";
	
	public BossesUnleashed() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext ctx = ModLoadingContext.get();

		UnleashedCreativeTabs.CREATIVE_MODE_TAB.register(bus);
		UnleashedItems.ITEMS.register(bus);
		UnleashedEntities.ENTITY_TYPES.register(bus);
		UnleashedEntityDataSerializers.SERIALIZERS.register(bus);
		UnleashedParticles.PARTICLES.register(bus);
		UnleashedSounds.SOUNDS.register(bus);
		
		UnleashedNetwork.registerMessages();
		ctx.registerConfig(Type.COMMON, UnleashedConfig.CONFIG_SPEC, "bosses-unleashed.toml");
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, UnleashedCapabilities::attachEntityCapability);
	}
}

package com.min01.unleashed.sound;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedSounds 
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BossesUnleashed.MODID);

	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_HURT = registerFixedSound("celestial_jellyfish_hurt", 10.0F);
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_DASH = registerFixedSound("celestial_jellyfish_dash", 10.0F);
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_TRANSFORM = registerFixedSound("celestial_jellyfish_transform", 30.0F);
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_EXPLOSION = registerFixedSound("celestial_jellyfish_explosion", 30.0F);
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_REWIND = registerFixedSound("celestial_jellyfish_rewind", 30.0F);
	
	private static RegistryObject<SoundEvent> registerFixedSound(String name, float range) 
	{
		return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(BossesUnleashed.MODID, name), 16.0F * range));
    }
}

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

	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_HURT = registerSound("celestial_jellyfish_hurt");
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_DASH = registerSound("celestial_jellyfish_dash");
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_TRANSFORM = registerSound("celestial_jellyfish_transform");
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_EXPLOSION = registerSound("celestial_jellyfish_explosion");
	public static final RegistryObject<SoundEvent> CELESTIAL_JELLYFISH_REWIND = registerSound("celestial_jellyfish_rewind");
	
	public static RegistryObject<SoundEvent> registerSound(String name) 
	{
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, name)));
    }
}

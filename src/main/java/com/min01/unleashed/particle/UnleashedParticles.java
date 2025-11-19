package com.min01.unleashed.particle;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedParticles 
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BossesUnleashed.MODID);
	
	public static final RegistryObject<SimpleParticleType> CELESTIAL_EXPLOSION = PARTICLES.register("celestial_explosion", () -> new SimpleParticleType(false));
}

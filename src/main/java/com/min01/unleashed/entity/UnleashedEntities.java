package com.min01.unleashed.entity;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.projectile.EntityCelestialBeam;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BossesUnleashed.MODID);

	public static final RegistryObject<EntityType<EntityCelestialJellyfish>> CELESTIAL_JELLYFISH = registerEntity("celestial_jellyfish", createBuilder(EntityCelestialJellyfish::new, MobCategory.MONSTER).sized(0.875F, 0.5F).clientTrackingRange(100));
	
	public static final RegistryObject<EntityType<EntityCelestialOrb>> CELESTIAL_ORB = registerEntity("celestial_orb", createBuilder(EntityCelestialOrb::new, MobCategory.MISC).sized(1.25F, 1.25F).setTrackingRange(100));
	public static final RegistryObject<EntityType<EntityCelestialBeam>> CELESTIAL_BEAM = registerEntity("celestial_beam", createBuilder(EntityCelestialBeam::new, MobCategory.MISC).sized(0.1F, 0.1F).updateInterval(1));
	public static final RegistryObject<EntityType<EntityCameraShake>> CAMERA_SHAKE = registerEntity("camera_shake", EntityType.Builder.<EntityCameraShake>of(EntityCameraShake::new, MobCategory.MISC).sized(0.0F, 0.0F));
	public static final RegistryObject<EntityType<EntityWormhole>> WORMHOLE = registerEntity("wormhole", createBuilder(EntityWormhole::new, MobCategory.MISC).sized(3.0F, 3.0F).clientTrackingRange(100));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(BossesUnleashed.MODID, name).toString()));
	}
}

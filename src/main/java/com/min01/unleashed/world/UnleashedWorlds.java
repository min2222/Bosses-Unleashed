package com.min01.unleashed.world;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class UnleashedWorlds 
{
	public static final ResourceKey<Level> CELESTIAL_FIELD = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BossesUnleashed.MODID, "celestial_field"));
}

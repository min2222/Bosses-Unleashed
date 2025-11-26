package com.min01.unleashed.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class UnleashedConfig 
{
	public static final UnleashedConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;

	public static ForgeConfigSpec.BooleanValue cameraShakes;
	public static ForgeConfigSpec.BooleanValue shaderEffects;
	public static ForgeConfigSpec.DoubleValue wormholeSpawnChance;
	public static ForgeConfigSpec.IntValue phaseOneDealTime;
	public static ForgeConfigSpec.IntValue phaseTwoDealTime;
    
    static 
    {
    	Pair<UnleashedConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(UnleashedConfig::new);
    	CONFIG = pair.getLeft();
    	CONFIG_SPEC = pair.getRight();
    }
	
    public UnleashedConfig(ForgeConfigSpec.Builder config) 
    {
    	config.push("Settings");
    	UnleashedConfig.cameraShakes = config.comment("disable/enable camera shakes in various place").define("cameraShakes", true);
    	UnleashedConfig.shaderEffects = config.comment("disable/enable fancy shader effects in various place").define("shaderEffects", true);
    	UnleashedConfig.wormholeSpawnChance = config.comment("spawning chance of wormhole in end").defineInRange("wormholeSpawnChance", 1.0F, 0.0F, 100.0F);
    	UnleashedConfig.phaseOneDealTime = config.comment("deal time after dash for phase 1 (in ticks)").defineInRange("phaseOneDealTime", 250, 0, Integer.MAX_VALUE);
    	UnleashedConfig.phaseTwoDealTime = config.comment("deal time after dash for phase 2 (in ticks)").defineInRange("phaseTwoDealTime", 150, 0, Integer.MAX_VALUE);
        config.pop();
    }
}

package com.min01.unleashed.shader;

import java.util.ArrayList;
import java.util.List;

import com.min01.unleashed.config.UnleashedConfig;
import com.min01.unleashed.network.AddShaderEffectPacket;
import com.min01.unleashed.network.UnleashedNetwork;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class UnleashedShaderEffects 
{
	public static final List<ShaderEffect> EFFECTS = new ArrayList<>();
	
	public static void addEffect(Level level, String name, Vec3 pos, int lifeTime, float scale)
	{
		if(!level.isClientSide && UnleashedConfig.shaderEffects.get())
		{
			UnleashedNetwork.sendToAll(new AddShaderEffectPacket(level.dimension(), name, pos, lifeTime, scale));
		}
	}
	
	public static class ShaderEffect
	{
		public final ResourceKey<Level> dimension;
		public final String name;
		public final Vec3 pos;
		public final int lifeTime;
		public final float scale;
		public int tickCount;
		
		public ShaderEffect(ResourceKey<Level> dimension, String name, Vec3 pos, int lifeTime, float scale) 
		{
			this.dimension = dimension;
			this.name = name;
			this.pos = pos;
			this.lifeTime = lifeTime;
			this.scale = scale;
		}
		
		public boolean isAlive()
		{
			return this.tickCount++ < this.lifeTime;
		}
	}
}

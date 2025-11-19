package com.min01.unleashed.shader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

public class UnleashedShaders implements ResourceManagerReloadListener 
{
	protected static final List<ExtendedPostChain> SHADERS = new ArrayList<>();
	
	protected static ExtendedPostChain SHOCKWAVE;
	protected static ExtendedPostChain GALAXY;
	protected static ExtendedPostChain STARFIELD;
	protected static ExtendedPostChain WORMHOLE;

	@Override
	public void onResourceManagerReload(ResourceManager manager)
	{
		this.clear();
		try
		{
			init(manager);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void init(ResourceManager manager) throws IOException
	{
		SHOCKWAVE = add(new ExtendedPostChain(BossesUnleashed.MODID, "shockwave"));
		GALAXY = add(new ExtendedPostChain(BossesUnleashed.MODID, "galaxy"));
		STARFIELD = add(new ExtendedPostChain(BossesUnleashed.MODID, "starfield"));
		WORMHOLE = add(new ExtendedPostChain(BossesUnleashed.MODID, "wormhole"));
	}

	public void clear()
	{
		SHADERS.forEach(PostChain::close);
		SHADERS.clear();
	}

	public static ExtendedPostChain add(ExtendedPostChain shader)
	{
		SHADERS.add(shader);
		return shader;
	}
	
	public static ExtendedPostChain getShockwave() 
	{
		return SHOCKWAVE;
	}
	
	public static ExtendedPostChain getGalaxy() 
	{
		return GALAXY;
	}
	
	public static ExtendedPostChain getStarfield() 
	{
		return STARFIELD;
	}
	
	public static ExtendedPostChain getWormhole() 
	{
		return WORMHOLE;
	}
}

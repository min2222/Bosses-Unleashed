package com.min01.unleashed.shader;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Window;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public class ExtendedPostChain extends PostChain
{
	public ExtendedPostChain(TextureManager pTextureManager, ResourceManager pResourceManager, RenderTarget pScreenTarget, ResourceLocation pName) throws IOException, JsonSyntaxException
	{
		super(pTextureManager, pResourceManager, pScreenTarget, pName);
	}
	
	public ExtendedPostChain(String domain, String name) throws JsonSyntaxException, IOException
	{
		this(UnleashedClientUtil.MC.getTextureManager(), UnleashedClientUtil.MC.getResourceManager(), UnleashedClientUtil.MC.getMainRenderTarget(), ResourceLocation.fromNamespaceAndPath(domain, "shaders/post/" + name + ".json"));
		this.resize(UnleashedClientUtil.MC.getWindow().getWidth(), UnleashedClientUtil.MC.getWindow().getHeight());
	}

	public EffectInstance getMainShader()
	{
		return this.passes.get(0).getEffect();
	}

	@Override
	public void process(float pPartialTicks)
	{
		Window window = UnleashedClientUtil.MC.getWindow();
		if(this.screenWidth != window.getWidth() || this.screenHeight != window.getHeight())
		{
			this.resize(window.getWidth(), window.getHeight());
		}
		super.process(pPartialTicks);
	}
}

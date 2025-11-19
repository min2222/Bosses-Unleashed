package com.min01.unleashed.world.effects;

import javax.annotation.Nullable;

import org.joml.Matrix4f;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.event.ClientEventHandlerForge;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CelestialFieldDimensionSpecialEffects extends DimensionSpecialEffects
{
	private static final ResourceLocation NEBULA_LOCATION = new ResourceLocation(BossesUnleashed.MODID, "textures/environment/nebula.png");
	
	@Nullable
	private VertexBuffer skyBuffer;
	
	@Nullable
	private VertexBuffer darkBuffer;
	   
	public CelestialFieldDimensionSpecialEffects()
	{
		super(Float.NaN, true, DimensionSpecialEffects.SkyType.NONE, true, false);
		this.createLightSky();
		this.createDarkSky();
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 p_108908_, float p_108909_)
	{
		return p_108908_;
	}

	@Override
	public boolean isFoggyAt(int p_108905_, int p_108906_) 
	{
		return false;
	}

	@Override
	public float[] getSunriseColor(float p_108872_, float p_108873_) 
	{
		return null;
	}

	@Override
	public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
	{
		Minecraft minecraft = UnleashedClientUtil.MC;
		FogRenderer.levelFogColor();
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		RenderSystem.depthMask(false);
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		ShaderInstance shaderinstance = RenderSystem.getShader();
		this.skyBuffer.bind();
		this.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
		VertexBuffer.unbind();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		
		poseStack.pushPose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		float f12 = 30.0F;

		if(ClientEventHandlerForge.STARFIELD.get())
		{
			poseStack.pushPose();
			poseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
			poseStack.mulPose(Axis.XP.rotationDegrees(70.0F));
			RenderSystem.setShaderTexture(0, NEBULA_LOCATION);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			bufferbuilder.vertex(poseStack.last().pose(), -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
			bufferbuilder.vertex(poseStack.last().pose(), f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
			bufferbuilder.vertex(poseStack.last().pose(), f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
			bufferbuilder.vertex(poseStack.last().pose(), -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
			BufferUploader.drawWithShader(bufferbuilder.end());
			poseStack.popPose();
		}
		
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
		poseStack.popPose();
		
		RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
		double d0 = minecraft.player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);
		if(d0 < 0.0D)
		{
			poseStack.pushPose();
			poseStack.translate(0.0F, 12.0F, 0.0F);
			this.darkBuffer.bind();
			this.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
			VertexBuffer.unbind();
			poseStack.popPose();
		}
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		if(ClientEventHandlerForge.STARFIELD.get())
		{
			UnleashedClientUtil.applyStarfield(poseStack, partialTick);
		}
		RenderSystem.depthMask(true);
		return true;
	}
	
	private void createDarkSky()
	{
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		if(this.darkBuffer != null)
		{
			this.darkBuffer.close();
		}

		this.darkBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
		BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = buildSkyDisc(bufferbuilder, -16.0F);
		this.darkBuffer.bind();
		this.darkBuffer.upload(bufferbuilder$renderedbuffer);
		VertexBuffer.unbind();
	}

	private void createLightSky() 
	{
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		if(this.skyBuffer != null)
		{
			this.skyBuffer.close();
		}

		this.skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
		BufferBuilder.RenderedBuffer bufferbuilder$renderedbuffer = buildSkyDisc(bufferbuilder, 16.0F);
		this.skyBuffer.bind();
		this.skyBuffer.upload(bufferbuilder$renderedbuffer);
		VertexBuffer.unbind();
	}

	private static BufferBuilder.RenderedBuffer buildSkyDisc(BufferBuilder p_234268_, float p_234269_)
	{
		float f = Math.signum(p_234269_) * 512.0F;
		RenderSystem.setShader(GameRenderer::getPositionShader);
		p_234268_.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION);
		p_234268_.vertex(0.0D, (double)p_234269_, 0.0D).endVertex();
		for(int i = -180; i <= 180; i += 45) 
		{
			p_234268_.vertex((double)(f * Mth.cos((float)i * ((float)Math.PI / 180.0F))), (double)p_234269_, (double)(512.0F * Mth.sin((float)i * ((float)Math.PI / 180.0F)))).endVertex();
		}
		return p_234268_.end();
	}
}
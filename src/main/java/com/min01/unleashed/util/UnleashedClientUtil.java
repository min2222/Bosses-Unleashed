package com.min01.unleashed.util;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.ITrail;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.shader.ExtendedPostChain;
import com.min01.unleashed.shader.UnleashedShaders;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class UnleashedClientUtil 
{
	public static final Minecraft MC = Minecraft.getInstance();
	
	public static final Matrix4f INVERSE_MAT = new Matrix4f();
	
	public static void animateHead(ModelPart head, float netHeadYaw, float headPitch)
	{
		head.yRot += Math.toRadians(netHeadYaw);
		head.xRot += Math.toRadians(headPitch);
	}
	
	public static void applyWormhole(PoseStack mtx, float frameTime)
	{
		Minecraft minecraft = UnleashedClientUtil.MC;

		ExtendedPostChain shaderChain = UnleashedShaders.getWormhole();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("iResolution").set(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
			shader.safeGetUniform("InverseTransformMatrix").set(getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("ProjectionMatrix").set(RenderSystem.getProjectionMatrix());
			shader.safeGetUniform("ViewMatrix").set(mtx.last().pose());
			shader.safeGetUniform("iTime").set((((float) (minecraft.level.getGameTime() % 2400000)) + frameTime) / 20.0F);
			shaderChain.process(frameTime);
			minecraft.getMainRenderTarget().bindWrite(false);
		}
	}
	
	public static void applyStarfield(PoseStack mtx, float frameTime)
	{
		Minecraft minecraft = UnleashedClientUtil.MC;

		ExtendedPostChain shaderChain = UnleashedShaders.getStarfield();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("iResolution").set(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
			shader.safeGetUniform("InverseTransformMatrix").set(getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("iTime").set((((float) (minecraft.level.getGameTime() % 2400000)) + frameTime) / 20.0F);
			shaderChain.process(frameTime);
			minecraft.getMainRenderTarget().bindWrite(false);
		}
	}
	
	public static void applyGalaxy(PoseStack mtx, float frameTime, float tickCount, float scale, Vector3f camForward)
	{
		Minecraft minecraft = UnleashedClientUtil.MC;

		ExtendedPostChain shaderChain = UnleashedShaders.getGalaxy();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("iResolution").set(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
			shader.safeGetUniform("InverseTransformMatrix").set(getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("iTime").set(tickCount / 20.0F);
			shader.safeGetUniform("Scale").set(scale);
			shader.safeGetUniform("CamForward").set(camForward.x, camForward.y, camForward.z);
			shaderChain.process(frameTime);
			minecraft.getMainRenderTarget().bindWrite(false);
		}
	}
	
	public static void applyShockwave(PoseStack mtx, float frameTime, int tickCount)
	{
		Minecraft minecraft = UnleashedClientUtil.MC;

		ExtendedPostChain shaderChain = UnleashedShaders.getShockwave();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("iResolution").set(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
			shader.safeGetUniform("InverseTransformMatrix").set(getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("ProjectionMat").set(RenderSystem.getProjectionMatrix());
			shader.safeGetUniform("ViewMat").set(mtx.last().pose());
			shader.safeGetUniform("iTime").set(tickCount / 20.0F);
			shaderChain.process(frameTime);
			minecraft.getMainRenderTarget().bindWrite(false);
		}
	}
	
    public static void drawQuad(PoseStack stack, VertexConsumer consumer, float size, int packedLightIn, Vec3 color, float alpha) 
    {
        float minU = 0;
        float minV = 0;
        float maxU = 1;
        float maxV = 1;
        PoseStack.Pose matrixstack$entry = stack.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        drawVertex(matrix4f, matrix3f, consumer, size, size, 0, minU, minV, color, alpha, packedLightIn);
        drawVertex(matrix4f, matrix3f, consumer, size, -size, 0, minU, maxV, color, alpha, packedLightIn);
        drawVertex(matrix4f, matrix3f, consumer, -size, -size, 0, maxU, maxV, color, alpha, packedLightIn);
        drawVertex(matrix4f, matrix3f, consumer, -size, size, 0, maxU, minV, color, alpha, packedLightIn);
    }
    
    public static void drawVertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, Vec3 color, float alpha, int packedLightIn)
    {
    	vertexBuilder.vertex(matrix, offsetX, offsetY, offsetZ).color((float) color.x, (float) color.y, (float) color.z, alpha).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
    }
    
	public static Matrix4f getInverseTransformMatrix(Matrix4f outMat, Matrix4f modelView)
    {
		return outMat.identity().mul(RenderSystem.getProjectionMatrix()).mul(modelView).invert();
    }
	
    public static void renderTrail(ITrail entityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, float trailR, float trailG, float trailB, float trailA, int sampleSize, float trailHeight) 
    {
    	Camera camera = MC.gameRenderer.getMainCamera();
    	Vec3 cameraPos = camera.getPosition();
        int samples = 0;
        Vec3 drawFrom = entityIn.getTrailPosition(0, partialTicks);
        VertexConsumer vertexconsumer = bufferIn.getBuffer(UnleashedRenderType.eyesFix(new ResourceLocation(BossesUnleashed.MODID, "textures/misc/trail.png")));
        while(samples < sampleSize)
        {
            Vec3 sample = entityIn.getTrailPosition(samples, partialTicks);
            float u1 = samples / (float) sampleSize;
            float u2 = u1 + 1 / (float) sampleSize;
            Vec3 draw1 = drawFrom;
            Vec3 draw2 = sample;
            Vec3 segmentDir = draw2.subtract(draw1).normalize();
            Vec3 midPoint = draw1.add(draw2).scale(0.5);
            Vec3 toCamera = cameraPos.subtract(midPoint).normalize();
            Vec3 perpendicular = segmentDir.cross(toCamera).normalize().scale(trailHeight);
            Vec3 v1Bottom = draw1.subtract(perpendicular);
            Vec3 v1Top = draw1.add(perpendicular);
            Vec3 v2Bottom = draw2.subtract(perpendicular);
            Vec3 v2Top = draw2.add(perpendicular);
            PoseStack.Pose pose = poseStack.last();
            Matrix4f matrix4f = pose.pose();
            Matrix3f matrix3f = pose.normal();
            vertexconsumer.vertex(matrix4f, (float) v1Bottom.x, (float) v1Bottom.y, (float) v1Bottom.z).color(trailR, trailG, trailB, trailA).uv(u1, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BLOCK).normal(matrix3f, 0, 1, 0).endVertex();
            vertexconsumer.vertex(matrix4f, (float) v2Bottom.x, (float) v2Bottom.y, (float) v2Bottom.z).color(trailR, trailG, trailB, trailA).uv(u2, 1.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BLOCK).normal(matrix3f, 0, 1, 0).endVertex();
            vertexconsumer.vertex(matrix4f, (float) v2Top.x, (float) v2Top.y, (float) v2Top.z).color(trailR, trailG, trailB, trailA).uv(u2, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BLOCK).normal(matrix3f, 0, 1, 0).endVertex();
            vertexconsumer.vertex(matrix4f, (float) v1Top.x, (float) v1Top.y, (float) v1Top.z).color(trailR, trailG, trailB, trailA).uv(u1, 0.0F).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BLOCK).normal(matrix3f, 0, 1, 0).endVertex();
            samples++;
            drawFrom = sample;
        }
    }
}

package com.min01.unleashed.entity.renderer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.projectile.EntityCelestialBeam;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class CelestialBeamRenderer extends EntityRenderer<EntityCelestialBeam>
{
    private static final float TEXTURE_WIDTH = 256.0F;
    private static final float TEXTURE_HEIGHT = 32.0F;
    private static final float START_RADIUS = 1.3F;
    private static final float BEAM_RADIUS = 1.0F;
    
	public CelestialBeamRenderer(Context p_174008_) 
	{
		super(p_174008_);
	}
	
	@Override
	public void render(EntityCelestialBeam p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		Vec3 collidePos = p_114485_.collidePos;
        Vec3 pos = p_114485_.getPosition(p_114487_);
        float yaw = p_114485_.getYaw();
        float pitch = p_114485_.getPitch();
        
        Vec3 relativePos = collidePos.subtract(pos);

        float length = (float) Math.sqrt(Math.pow(relativePos.x, 2) + Math.pow(relativePos.y, 2) + Math.pow(relativePos.z, 2));
        int frame = 5;
        VertexConsumer consumer = p_114489_.getBuffer(UnleashedRenderType.eyes(this.getTextureLocation(p_114485_)));

        this.renderStart(frame, p_114488_, consumer, LightTexture.FULL_BRIGHT);
        this.renderBeam(length, 180.0F / (float) Math.PI * yaw, 180.0F / (float) Math.PI * pitch, frame, p_114488_, consumer, LightTexture.FULL_BRIGHT);

        p_114488_.pushPose();
        p_114488_.translate(relativePos.x, relativePos.y, relativePos.z);
        this.renderEnd(frame, null, p_114488_, consumer, LightTexture.FULL_BRIGHT);
        p_114488_.popPose();
    }

    private void renderFlatQuad(int frame, PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn)
    {
        float minU = 16.0F / TEXTURE_WIDTH * frame;
        float minV = 0.0F;
        float maxU = minU + 16.0F / TEXTURE_WIDTH;
        float maxV = minV + 16.0F / TEXTURE_HEIGHT;
        PoseStack.Pose matrixstack$entry = matrixStackIn.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        this.drawVertex(matrix4f, matrix3f, builder, -START_RADIUS, -START_RADIUS, 0, minU, minV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, -START_RADIUS, START_RADIUS, 0, minU, maxV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, START_RADIUS, START_RADIUS, 0, maxU, maxV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, START_RADIUS, -START_RADIUS, 0, maxU, minV, 1, packedLightIn);
    }

    private void renderStart(int frame, PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn) 
    {
        matrixStackIn.pushPose();
        Quaternionf quat = this.entityRenderDispatcher.cameraOrientation();
        matrixStackIn.mulPose(quat);
        this.renderFlatQuad(frame, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();
    }

    private void renderEnd(int frame, Direction side, PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn) 
    {
        matrixStackIn.pushPose();
        Quaternionf quat = this.entityRenderDispatcher.cameraOrientation();
        matrixStackIn.mulPose(quat);
        this.renderFlatQuad(frame, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();
        if(side == null) 
        {
            return;
        }
        matrixStackIn.pushPose();
        Quaternionf sideQuat = side.getRotation();
        sideQuat.mul(Axis.XP.rotationDegrees(90.0F));
        matrixStackIn.mulPose(sideQuat);
        matrixStackIn.translate(0, 0, -0.01F);
        this.renderFlatQuad(frame, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();
    }

    private void drawBeam(float length, int frame, PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn)
    {
        float minU = 0.0F;
        float minV = 16.0F / TEXTURE_HEIGHT + 1.0F / TEXTURE_HEIGHT * frame;
        float maxU = minU + 20.0F / TEXTURE_WIDTH;
        float maxV = minV + 1.0F / TEXTURE_HEIGHT;
        PoseStack.Pose matrixstack$entry = matrixStackIn.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        float offset = 0;
        this.drawVertex(matrix4f, matrix3f, builder, -BEAM_RADIUS, offset, 0, minU, minV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, -BEAM_RADIUS, length, 0, minU, maxV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, BEAM_RADIUS, length, 0, maxU, maxV, 1, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, builder, BEAM_RADIUS, offset, 0, maxU, minV, 1, packedLightIn);
    }

    private void renderBeam(float length, float yaw, float pitch, int frame,  PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn) 
    {
    	Camera camera = UnleashedClientUtil.MC.gameRenderer.getMainCamera();
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(yaw - 90.0F));
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(-pitch));
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(camera.getXRot() + 90.0F));
        this.drawBeam(length, frame, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(-camera.getXRot() - 90.0F));
        this.drawBeam(length, frame, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();
        matrixStackIn.popPose();
    }

    public void drawVertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, float alpha, int packedLightIn) 
    {
        vertexBuilder.vertex(matrix, offsetX, offsetY, offsetZ).color(1.0F, 1.0F, 1.0F, 1.0F * alpha).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
    }

	@Override
	public ResourceLocation getTextureLocation(EntityCelestialBeam p_114482_)
	{
		return new ResourceLocation(BossesUnleashed.MODID, "textures/entity/celestial_beam.png");
	}
}

package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.model.ModelCelestialOrb;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.misc.UnleashedRenderType;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CelestialOrbRenderer extends EntityRenderer<EntityCelestialOrb>
{
	private final ModelCelestialOrb model;
	public CelestialOrbRenderer(Context p_174008_) 
	{
		super(p_174008_);
		this.model = new ModelCelestialOrb(p_174008_.bakeLayer(ModelCelestialOrb.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityCelestialOrb p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		float xRot = Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot());
		float yRot = Mth.lerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot());
		
		/*p_114488_.pushPose();
		p_114488_.mulPose(Axis.YP.rotationDegrees(yRot));
		p_114488_.mulPose(Axis.XP.rotationDegrees(xRot));
		p_114488_.scale(-1.0F, -1.0F, 1.0F);
		p_114488_.scale(3.0F, 3.0F, 3.0F);
		p_114488_.translate(0.0F, -1.5F, 0.0F);
		this.model.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutout(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		p_114488_.popPose();*/
		
		p_114488_.pushPose();
		p_114488_.mulPose(Axis.YP.rotationDegrees(yRot));
		p_114488_.mulPose(Axis.XP.rotationDegrees(xRot));
		p_114488_.scale(-1.0F, -1.0F, 1.0F);
		p_114488_.scale(3.0F, 3.0F, 3.0F);
		p_114488_.translate(0.0F, -1.5F, 0.0F);
		this.model.renderToBuffer(p_114488_, p_114489_.getBuffer(UnleashedRenderType.eyesNoAlpha(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		p_114488_.popPose();
		
		if(p_114485_.hasTrail())
		{
			p_114488_.pushPose();
			Vec3 pos = p_114485_.getPosition(p_114487_);
			p_114488_.translate(-pos.x, -pos.y + 0.5F, -pos.z);
	        Vec3 color = Vec3.fromRGB24(8364799);
	        UnleashedClientUtil.renderTrail(p_114485_, p_114487_, p_114488_, p_114489_, (float)color.x, (float)color.y, (float)color.z, 0.8F, 8, 0.8F);
	        p_114488_.popPose();
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCelestialOrb p_114482_) 
	{
		return new ResourceLocation(BossesUnleashed.MODID, "textures/entity/celestial_orb.png");
	}
}

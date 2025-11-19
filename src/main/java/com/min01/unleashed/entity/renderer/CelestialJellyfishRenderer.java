package com.min01.unleashed.entity.renderer;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.model.ModelCelestialJellyfish;
import com.min01.unleashed.entity.renderer.layer.CelestialJellyfishLayer;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.min01.unleashed.util.UnleashedUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CelestialJellyfishRenderer extends MobRenderer<EntityCelestialJellyfish, ModelCelestialJellyfish>
{
	private final JellyfishRenderer renderer;
	
	public CelestialJellyfishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelCelestialJellyfish(p_174304_.bakeLayer(ModelCelestialJellyfish.LAYER_LOCATION)), 0.0F);
		this.addLayer(new CelestialJellyfishLayer(this, this.model, new ResourceLocation(BossesUnleashed.MODID, "textures/entity/celestial_jellyfish.png")));
		this.renderer = new JellyfishRenderer(p_174304_);
	}
	
	@Override
	public void render(EntityCelestialJellyfish p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		if(p_115455_.isTransform())
		{
			p_115455_.afterImage.render(p_115458_, p_115459_, RenderType.eyes(this.getTextureLocation(p_115455_)), p_115460_, p_115457_, 0.5F);
			p_115455_.afterImage.render(p_115458_, p_115459_, RenderType.entityTranslucent(this.getTextureLocation(p_115455_)), p_115460_, p_115457_, 0.5F);
			super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
			
			if(p_115455_.showWarning())
			{
		        double y = Mth.lerp((double)p_115457_, p_115455_.yOld, p_115455_.getY());
		        float scale = (float) p_115455_.getBoundingBox().inflate(1.85F).getSize() / 2.0F;
		    	Vec3 camPos = UnleashedClientUtil.MC.gameRenderer.getMainCamera().getPosition();
		        p_115458_.pushPose();
                BlockPos groundPos = UnleashedUtil.getGroundPos(p_115455_.level, p_115455_.getX(), p_115455_.getY(), p_115455_.getZ(), -2);
                p_115458_.translate(0, -(y - camPos.y), 0);
                p_115458_.translate(0, groundPos.getY() - camPos.y, 0);
                p_115458_.translate(0, 0.01F, 0);
                p_115458_.mulPose(Axis.XP.rotationDegrees(90.0F));
                p_115458_.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(p_115457_, p_115455_.yHeadRotO, p_115455_.getYHeadRot())));
                p_115458_.scale(1, scale * 2.0F, 1);
                p_115458_.translate(0, scale, 0);
                UnleashedClientUtil.drawQuad(p_115458_, p_115459_.getBuffer(RenderType.entityTranslucent(new ResourceLocation(BossesUnleashed.MODID, "textures/misc/white.png"))), scale, p_115460_, new Vec3(1.0F, 0.0F, 0.0F), 0.5F);
                p_115458_.popPose();
			}
		}
		else
		{
			this.renderer.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		}
	}
	
	@Override
	protected int getBlockLightLevel(EntityCelestialJellyfish p_114496_, BlockPos p_114497_)
	{
		return 15;
	}
	
	@Override
	protected RenderType getRenderType(EntityCelestialJellyfish p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
	}
	
	@Override
	public ResourceLocation getTextureLocation(EntityCelestialJellyfish p_115812_)
	{
		return new ResourceLocation(BossesUnleashed.MODID, "textures/entity/celestial_jellyfish.png");
	}
}

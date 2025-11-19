package com.min01.unleashed.misc;

import java.util.ArrayList;
import java.util.List;

import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AfterImage<T extends LivingEntity>
{
	public final List<ModelState> states = new ArrayList<>();
	public final T entity;
	public final int lifeTime;
	public final int interval;
	public int tick;
	public boolean shouldAdd;
	
	public AfterImage(T entity, int lifeTime, int interval) 
	{
		this.entity = entity;
		this.lifeTime = lifeTime;
		this.interval = interval;
	}
	
	public void tick(boolean shouldAdd)
	{
		if(this.entity.level.isClientSide)
		{
			if(shouldAdd)
			{
				this.tick++;
				if(this.tick >= this.interval)
				{
					this.addAfterImage();
					this.tick = 0;
				}
			}
			this.states.removeIf(t -> t.tick++ >= this.lifeTime);
		}
	}
	
	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	public <M extends EntityModel<T>> void render(PoseStack stack, MultiBufferSource bufferSource, RenderType renderType, int packedLight, float partialTicks, float alpha)
	{
		T living = this.entity;
		EntityRenderer<?> renderer = UnleashedClientUtil.MC.getEntityRenderDispatcher().getRenderer(living);
        boolean shouldSit = living.isPassenger() && (living.getVehicle() != null && living.getVehicle().shouldRiderSit());
    	Vec3 camPos = UnleashedClientUtil.MC.gameRenderer.getMainCamera().getPosition();
        double x = Mth.lerp((double)partialTicks, living.xOld, living.getX());
        double y = Mth.lerp((double)partialTicks, living.yOld, living.getY());
        double z = Mth.lerp((double)partialTicks, living.zOld, living.getZ());
    	if(renderer instanceof LivingEntityRenderer<?, ?> livingRenderer)
    	{
    		EntityModel<T> model = (EntityModel<T>) livingRenderer.getModel();
    	    for(ModelState state : this.states)
    	    {
    	        stack.pushPose();
                Vec3 pos = state.pos;
                stack.translate(-(x - camPos.x), -(y - camPos.y), -(z - camPos.z));
                stack.translate(pos.x - camPos.x, pos.y - camPos.y, pos.z - camPos.z);
    	        model.attackTime = living.getAttackAnim(partialTicks);
    	        model.riding = shouldSit;
    	        model.young = living.isBaby();
    	        if(living.hasPose(Pose.SLEEPING)) 
    	        {
    	        	Direction direction = living.getBedOrientation();
    	        	if(direction != null) 
    	        	{
    	        		float f4 = living.getEyeHeight(Pose.STANDING) - 0.1F;
    	        		stack.translate((float)(-direction.getStepX()) * f4, 0.0F, (float)(-direction.getStepZ()) * f4);
    	        	}
    	        }
    	        this.setupRotations(living, stack, state.f7, state.f, partialTicks);
    	        stack.scale(-1.0F, -1.0F, 1.0F);
    	        stack.translate(0.0F, -1.501F, 0.0F);
    	        model.prepareMobModel(living, state.f5, state.f8, partialTicks);
    	        model.setupAnim(living, state.f5, state.f8, state.f7, state.f2, state.f6);
    	        if(model instanceof HierarchicalModel<?> animatedModel)
    	        {
    	        	animatedModel.root().visible = true;
    	        }
            	VertexConsumer consumer = bufferSource.getBuffer(renderType);
            	model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);
    	        stack.popPose();
    	    }
    	}
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addAfterImage()
	{
		T living = this.entity;
		float partialTicks = UnleashedClientUtil.MC.getFrameTime();
        boolean shouldSit = living.isPassenger() && (living.getVehicle() != null && living.getVehicle().shouldRiderSit());
        double x = Mth.lerp((double)partialTicks, living.xOld, living.getX());
        double y = Mth.lerp((double)partialTicks, living.yOld, living.getY());
        double z = Mth.lerp((double)partialTicks, living.zOld, living.getZ());
    	Vec3 entityPos = new Vec3(x, y, z);
        float f = Mth.rotLerp(partialTicks, living.yBodyRotO, living.yBodyRot);
        float f1 = Mth.rotLerp(partialTicks, living.yHeadRotO, living.yHeadRot);
        float f2 = f1 - f;
        if(shouldSit && living.getVehicle() instanceof LivingEntity) 
        {
        	LivingEntity livingentity = (LivingEntity) living.getVehicle();
        	f = Mth.rotLerp(partialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
        	f2 = f1 - f;
        	float f3 = Mth.wrapDegrees(f2);
        	f3 = Mth.clamp(f3, -85.0F, 85.0F);
        	f = f1 - f3;
        	if(f3 * f3 > 2500.0F)
        	{
        		f += f3 * 0.2F;
        	}
        	f2 = f1 - f;
        }
        float f6 = Mth.lerp(partialTicks, living.xRotO, living.getXRot());
        if(LivingEntityRenderer.isEntityUpsideDown(living)) 
        {
           f6 *= -1.0F;
           f2 *= -1.0F;
        }
        float f7 = living.tickCount + partialTicks;
        float f8 = 0.0F;
        float f5 = 0.0F;
        if(!shouldSit && living.isAlive())
        {
        	f8 = living.walkAnimation.speed(partialTicks);
        	f5 = living.walkAnimation.position(partialTicks);
        	if(living.isBaby())
        	{
        		f5 *= 3.0F;
        	}
        	if(f8 > 1.0F) 
        	{
        		f8 = 1.0F;
        	}
        }
        this.states.add(new ModelState(f, f2, f5, f6, f7, f8, entityPos));
	}
	
	public void setupRotations(T entity, PoseStack stack, float tick, float yRot, float partialTicks)
	{
		if(entity.isFullyFrozen()) 
		{
			float wiggle = Mth.cos(entity.tickCount * 3.25F) * 1.2566F;
			yRot += wiggle;
		}
		if(!entity.hasPose(Pose.SLEEPING)) 
		{
			stack.mulPose(Axis.YP.rotationDegrees(180.0F - yRot));
		}
		if(entity.deathTime > 0) 
		{
			float f = ((float)entity.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
			f = Mth.sqrt(f);
			if(f > 1.0F) 
			{
				f = 1.0F;
			}
			stack.mulPose(Axis.ZP.rotationDegrees(f * 90.0F));
		}
		else if(entity.isAutoSpinAttack()) 
		{
			stack.mulPose(Axis.XP.rotationDegrees(-90.0F - entity.getXRot()));
			stack.mulPose(Axis.YP.rotationDegrees(((float)entity.tickCount + partialTicks) * -75.0F));
		}
		else if(entity.hasPose(Pose.SLEEPING))
		{
			Direction direction = entity.getBedOrientation();
			float f1 = direction != null ? this.sleepDirectionToRotation(direction) : partialTicks;
			stack.mulPose(Axis.YP.rotationDegrees(f1));
			stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
			stack.mulPose(Axis.YP.rotationDegrees(270.0F));
		}
		else if(LivingEntityRenderer.isEntityUpsideDown(entity))
		{
			stack.translate(0.0F, entity.getBbHeight() + 0.1F, 0.0F);
			stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
		}
	}
	
	public float sleepDirectionToRotation(Direction direction) 
	{
		switch (direction) 
		{
		case SOUTH:
			return 90.0F;
		case WEST:
			return 0.0F;
		case NORTH:
			return 270.0F;
		case EAST:
			return 180.0F;
		default:
			return 0.0F;
		}
	}
	
	public static class ModelState
	{
		public float f, f2, f5, f6, f7, f8;
		public int tick;
		public Vec3 pos;
		
		public ModelState(float f, float f2, float f5, float f6, float f7, float f8, Vec3 pos) 
		{
			this.f = f;
			this.f2 = f2;
			this.f5 = f5;
			this.f6 = f6;
			this.f7 = f7;
			this.f8 = f8;
			this.pos = pos;
		}
	}
}
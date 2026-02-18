package com.min01.unleashed.misc;

import org.joml.Vector3f;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SmoothAnimationState extends AnimationState
{
	public static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
	
	public float factorOld;
	public float factor = 1.0F;
	public float tickCount;
	
	public final float threshold;
	public final float lerpSpeed;
	
	public SmoothAnimationState() 
	{
		this(0.9F, 0.4F);
	}
	
	public SmoothAnimationState(float threshold) 
	{
		this(threshold, 0.4F);
	}
	
	public SmoothAnimationState(float threshold, float lerpSpeed)
	{
		this.threshold = threshold;
		this.lerpSpeed = lerpSpeed;
	}
	
	public void updateWhen(boolean updateWhen, int tickCount)
	{
		this.tickCount = tickCount;
	    this.factorOld = this.factor;
	    float target = updateWhen ? 0.0F : 1.0F;
	    this.factor += (target - this.factor) * this.lerpSpeed;
	    this.factor = Mth.clamp(this.factor, 0.0F, 1.0F);
	    this.animateWhen(this.factor <= this.threshold + 0.0001F, tickCount);
	}
	
	public float factor(float ageInTicks)
	{
		return Mth.lerp(this.partialTicks(ageInTicks), this.factorOld, this.factor);
	}

	public float partialTicks(float ageInTicks)
	{
		return ageInTicks - this.tickCount;
	}

	@OnlyIn(Dist.CLIENT)
	public void animate(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks) 
	{
		this.animate(model, definition, ageInTicks, 0.0F, 1.0F);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateWithSpeed(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks, float speed) 
	{
		this.animate(model, definition, ageInTicks, 0.0F, speed);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateIdle(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks, float limbSwingAmount, float animationScaleFactor, SmoothAnimationState... states) 
	{
		float totalFactor = 1.0F;
		float extraFactor = 0.0F;
		for(SmoothAnimationState state : states)
		{
			float factor = state.factor(ageInTicks);
			totalFactor *= factor;
			extraFactor += 1.0F - factor;
		}
		float totalLimb = (1.0F * totalFactor) + extraFactor;
		this.animate(model, definition, ageInTicks, Math.min((limbSwingAmount * totalLimb) * animationScaleFactor, 1.0F), 1.0F);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void animateWalk(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor, SmoothAnimationState... states)
	{
		float totalFactor = 1.0F;
		for(SmoothAnimationState state : states)
		{
			float factor = state.factor(ageInTicks);
			totalFactor *= factor;
		}
		float factor = 1.0F * totalFactor;
		animateWalk(model, definition, limbSwing, limbSwingAmount, maxAnimationSpeed, factor * animationScaleFactor);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateWalkWithFactor(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor)
	{
		float factor = 1.0F - this.factor(ageInTicks);
		animateWalk(model, definition, limbSwing, limbSwingAmount, maxAnimationSpeed, factor * animationScaleFactor);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void animateWalk(HierarchicalModel<?> model, AnimationDefinition definition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor)
	{
		long i = (long)(limbSwing * 50.0F * maxAnimationSpeed);
		float f = Math.min(limbSwingAmount * animationScaleFactor, 1.0F);
		KeyframeAnimations.animate(model, definition, i, f, ANIMATION_VECTOR_CACHE);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animate(HierarchicalModel<?> model, AnimationDefinition definition, float ageInTicks, float extraFactor, float speed) 
	{
		this.updateTime(ageInTicks, speed);
		this.ifStarted(t -> 
		{
			float totalFactor = extraFactor + this.factor(ageInTicks);
			KeyframeAnimations.animate(model, definition, t.getAccumulatedTime(), 1.0F - totalFactor, ANIMATION_VECTOR_CACHE);
		});
	}
}
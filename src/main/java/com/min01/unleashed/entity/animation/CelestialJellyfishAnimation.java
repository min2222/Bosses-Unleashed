package com.min01.unleashed.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class CelestialJellyfishAnimation
{
	public static final AnimationDefinition JELLYFISH_SWIM = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("tentacle7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacle12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("umbrella", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("outer_umbrella", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.9F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.9F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tentacles", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.9F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.9F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 1.0F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, -2.5024F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, -2.5024F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, -2.5024F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-4.9811F, -0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.9811F, -0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-4.9811F, -0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle5", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, 2.4976F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, 2.4976F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-2.4976F, 0.109F, 2.4976F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle9", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(-4.9811F, 0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle13", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.8F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.8F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("large_tentacle16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.9811F, -0.4352F, 4.9811F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

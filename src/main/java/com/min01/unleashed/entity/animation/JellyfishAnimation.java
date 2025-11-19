package com.min01.unleashed.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class JellyfishAnimation
{
	public static final AnimationDefinition JELLYFISH_SWIM = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("jellyfish", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.1F, 0.9F, 1.1F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

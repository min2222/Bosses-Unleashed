package com.min01.unleashed.misc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.joml.Vector3f;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class UnleashedKeyframeAnimations
{
	private static final float TWO_PI = (float)(Math.PI * 2.0);
	private static final float FULL_TURN_EPS = 0.18F;
	
	public static void animate(HierarchicalModel<?> pModel, AnimationDefinition pAnimationDefinition, long pAccumulatedTime, float positionScale, float rotationScale, boolean fadeSnapFullTurnRotationOnly, Vector3f pAnimationVecCache)
	{
		float f = getElapsedSeconds(pAnimationDefinition, pAccumulatedTime);
		boolean pastClipEnd = !pAnimationDefinition.looping() && f >= pAnimationDefinition.lengthInSeconds() - 1.0e-4F;
		for(Map.Entry<String, List<AnimationChannel>> entry : pAnimationDefinition.boneAnimations().entrySet())
		{
			Optional<ModelPart> optional = pModel.getAnyDescendantWithName(entry.getKey());
			List<AnimationChannel> list = entry.getValue();
			optional.ifPresent(part ->
			{
				list.forEach(channel ->
				{
					float scale;
					if(channel.target() == AnimationChannel.Targets.ROTATION)
					{
						boolean fullTurn = isFullTurnRotationChannel(channel);
						if(pastClipEnd && fullTurn)
						{
							scale = 0.0F;
						}
						else if(fadeSnapFullTurnRotationOnly && fullTurn)
						{
							scale = 0.0F;
						}
						else
						{
							scale = rotationScale;
						}
					}
					else
					{
						scale = positionScale;
					}
					Keyframe[] akeyframe = channel.keyframes();
					int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, (index) ->
					{
						return f <= akeyframe[index].timestamp();
					}) - 1);
					int j = Math.min(akeyframe.length - 1, i + 1);
					Keyframe keyframe = akeyframe[i];
					Keyframe keyframe1 = akeyframe[j];
					float f1 = f - keyframe.timestamp();
					float f2;
					if(j != i) 
					{
						f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
					} 
					else
					{
						f2 = 0.0F;
					}
					keyframe1.interpolation().apply(pAnimationVecCache, f2, akeyframe, i, j, scale);
					channel.target().apply(part, pAnimationVecCache);
				});
			});
		}
	}

	private static float getElapsedSeconds(AnimationDefinition definition, long accumulatedTime)
	{
		float seconds = (float)accumulatedTime / 1000.0F;
		return definition.looping() ? seconds % definition.lengthInSeconds() : seconds;
	}
	
	private static boolean isFullTurnRotationChannel(AnimationChannel channel)
	{
		if(channel.target() != AnimationChannel.Targets.ROTATION)
		{
			return false;
		}
		Keyframe[] keyframes = channel.keyframes();
		if(keyframes.length < 2)
		{
			return false;
		}
		Vector3f first = keyframes[0].target();
		Vector3f last = keyframes[keyframes.length - 1].target();
		float dx = Math.abs(last.x - first.x);
		float dy = Math.abs(last.y - first.y);
		float dz = Math.abs(last.z - first.z);
		float maxDelta = Math.max(dx, Math.max(dy, dz));
		return maxDelta >= TWO_PI - FULL_TURN_EPS;
	}
}

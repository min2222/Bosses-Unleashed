package com.min01.unleashed.misc;

import com.min01.unleashed.item.CelestialJellyfishUmbrellaItem;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IArmPoseTransformer;

public class UnleashedArmPoses
{
	public static HumanoidModel.ArmPose CELESTIAL_UMBRELLA;
	
	public static void registerArmPoses()
	{
		CELESTIAL_UMBRELLA = HumanoidModel.ArmPose.create("CELESTIAL_UMBRELLA", false, new CelestialUmbrellaArmPoseTransformer());
	}
	
	public static class CelestialUmbrellaArmPoseTransformer implements IArmPoseTransformer
	{
		@Override
		public void applyTransform(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm)
		{
			ItemStack stack = entity.getItemInHand(arm == HumanoidArm.LEFT ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
			if(entity.getDeltaMovement().y < -0.1F && CelestialJellyfishUmbrellaItem.isUnfolded(stack))
			{
				ModelPart armPart = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
				armPart.xRot += Math.toRadians(-80.0F);
			}
		}
	}
}
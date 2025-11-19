package com.min01.unleashed.misc;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
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
			if(entity.isUsingItem())
			{
				ModelPart armPart = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
				armPart.xRot = 80.0F;
			}
		}
	}
}
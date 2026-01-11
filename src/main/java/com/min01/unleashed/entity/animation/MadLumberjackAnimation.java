package com.min01.unleashed.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class MadLumberjackAnimation
{
	public static final AnimationDefinition AXE_IDLE = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.567F, -21.8183F, 13.8345F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-55.2948F, -22.9294F, 11.8264F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-60.567F, -21.8183F, 13.8345F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.93F, 65.29F, -61.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-93.4406F, 63.0752F, -62.237F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-90.4258F, 67.5143F, -61.425F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.93F, 65.29F, -61.83F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-11.503F, -4.8956F, -14.7295F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-18.06F, 22.88F, -32.05F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-20.3981F, 22.0928F, -33.9525F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-15.7155F, 23.6576F, -30.1454F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-18.06F, 22.88F, -32.05F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -35.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.4117F, 11.5218F, -23.6772F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-86.7034F, 13.49F, -22.6569F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.4117F, 11.5218F, -23.6772F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(1.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.6355F, 6.2706F, 4.5773F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(2.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(1.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -52.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -50.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -62.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -47.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -62.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -52.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 77.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 80.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition AXE_WALK = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.567F, -21.8183F, 13.8345F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-55.2948F, -22.9294F, 11.8264F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-60.567F, -21.8183F, 13.8345F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-55.2948F, -22.9294F, 11.8264F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-60.567F, -21.8183F, 13.8345F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.93F, 65.29F, -61.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(-93.4406F, 63.0752F, -62.237F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(-90.4258F, 67.5143F, -61.425F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(-93.4406F, 63.0752F, -62.237F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(-90.4258F, 67.5143F, -61.425F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.93F, 65.29F, -61.83F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-11.503F, -4.8956F, -14.7295F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-18.06F, 22.88F, -32.05F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(-20.3981F, 22.0928F, -33.9525F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(-15.7155F, 23.6576F, -30.1454F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(-20.3981F, 22.0928F, -33.9525F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(-15.7155F, 23.6576F, -30.1454F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-18.06F, 22.88F, -32.05F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -35.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.4117F, 11.5218F, -23.6772F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-86.7034F, 13.49F, -22.6569F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-91.4117F, 11.5218F, -23.6772F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-86.7034F, 13.49F, -22.6569F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.4117F, 11.5218F, -23.6772F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.2207F, -9.6559F, -2.613F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-13.64F, -8.58F, -1.31F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-37.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-13.64F, 8.58F, 1.31F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-37.5F, 7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(38.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(12.92F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.3F, KeyframeAnimations.degreeVec(38.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.7F, KeyframeAnimations.degreeVec(12.92F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.6355F, 6.2706F, 4.5773F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(2.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -62.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 77.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 80.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition GUN_IDLE = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-100.0612F, -13.6946F, 44.2258F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-98.2029F, -15.4311F, 43.7585F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-100.0612F, -13.6946F, 44.2258F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-0.07F, -20.28F, 39.12F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.8942F, -20.5463F, 37.9604F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-1.0311F, -20.0085F, 40.2707F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-0.07F, -20.28F, 39.12F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.997F, -4.8956F, -14.7295F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(19.65F, -24.24F, -64.15F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(20.2393F, -23.2255F, -63.8991F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(19.0591F, -25.2521F, -64.407F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(19.65F, -24.24F, -64.15F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.3838F, 1.5248F, -23.432F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-89.0878F, 2.5175F, -23.351F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.3838F, 1.5248F, -23.432F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(1.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.6355F, 6.2706F, 4.5773F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(2.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 105.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -25.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, -62.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 80.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.1F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition GUN_WALK = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-100.0612F, -13.6946F, 44.2258F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-98.2029F, -15.4311F, 43.7585F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-100.0612F, -13.6946F, 44.2258F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-98.2029F, -15.4311F, 43.7585F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-100.0612F, -13.6946F, 44.2258F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-0.07F, -20.28F, 39.12F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.8942F, -20.5463F, 37.9604F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(-1.0311F, -20.0085F, 40.2707F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.8942F, -20.5463F, 37.9604F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(-1.0311F, -20.0085F, 40.2707F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-0.07F, -20.28F, 39.12F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.997F, -4.8956F, -14.7295F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(19.65F, -24.24F, -64.15F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(20.2393F, -23.2255F, -63.8991F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(19.0591F, -25.2521F, -64.407F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(20.2393F, -23.2255F, -63.8991F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(19.0591F, -25.2521F, -64.407F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(19.65F, -24.24F, -64.15F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-91.3838F, 1.5248F, -23.432F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-89.0878F, 2.5175F, -23.351F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-91.3838F, 1.5248F, -23.432F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-89.0878F, 2.5175F, -23.351F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-91.3838F, 1.5248F, -23.432F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.2207F, -9.6559F, -2.613F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-13.64F, -8.58F, -1.31F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-37.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-13.64F, 8.58F, 1.31F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-37.5F, 7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(10.2207F, 9.6559F, 2.613F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(38.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(12.92F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.3F, KeyframeAnimations.degreeVec(38.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.7F, KeyframeAnimations.degreeVec(12.92F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(337.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(332.5668F, -2.4623F, 0.4378F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(335.07F, -2.46F, 0.44F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.6355F, 6.2706F, 4.5773F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(2.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -62.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 80.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 87.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 105.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.1F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition AXE_SPRINT = AnimationDefinition.Builder.withLength(0.8F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-33.21F, -24.94F, -1.81F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-84.9955F, -19.3234F, -16.3216F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(-23.623F, -25.9789F, 0.8752F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-33.21F, -24.94F, -1.81F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.89F, -4.34F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.posVec(0.89F, -4.34F, 3.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.posVec(0.8948F, -4.3392F, -2.3175F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.posVec(0.8948F, -4.3392F, -2.3175F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.89F, -4.34F, 1.06F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.86F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(-53.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-20.86F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(64.4873F, 25.6072F, 77.7472F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(-34.8844F, 50.3738F, -41.1194F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 70.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-54.0467F, 30.8455F, -50.0685F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(55.886F, 52.4225F, 61.7702F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(64.4873F, 25.6072F, 77.7472F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-112.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-111.8063F, 5.6842F, 13.9042F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, 6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(43.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(43.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.3099F, -0.9564F, 157.5193F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(3.0F, 0.3509F, -24.9204F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -62.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

		public static final AnimationDefinition GUN_SPRINT = AnimationDefinition.Builder.withLength(0.8F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(65.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-33.21F, -24.94F, -1.81F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-84.9955F, -19.3234F, -16.3216F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(-23.623F, -25.9789F, 0.8752F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-33.21F, -24.94F, -1.81F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone6", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.89F, -4.34F, 1.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.posVec(0.89F, -4.34F, 3.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.posVec(0.8948F, -4.3392F, -2.3175F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.posVec(0.8948F, -4.3392F, -2.3175F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.89F, -4.34F, 1.06F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.86F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(-53.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-20.86F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(64.4873F, 25.6072F, 77.7472F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(-34.8844F, 50.3738F, -41.1194F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 70.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-54.0467F, 30.8455F, -50.0685F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(55.886F, 52.4225F, 61.7702F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(64.4873F, 25.6072F, 77.7472F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(-61.7916F, -3.5052F, 1.5062F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(-61.7916F, -3.5052F, 1.5062F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 22.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-112.2259F, -17.3626F, 14.4581F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone14", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, 6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(52.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone15", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 6.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(43.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(43.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(-62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone19", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.3099F, -0.9564F, 157.5193F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(3.0F, 0.3509F, -24.9204F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone21", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone22", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone23", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, -62.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone24", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone25", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone26", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(-24.1211F, -6.9374F, -38.6217F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("shotgun", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.1F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}

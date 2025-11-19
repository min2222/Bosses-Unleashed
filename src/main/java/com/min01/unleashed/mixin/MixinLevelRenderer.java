package com.min01.unleashed.mixin;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.min01.unleashed.entity.IShaderEffect;
import com.min01.unleashed.item.IShaderEffectItem;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.min01.unleashed.util.UnleashedUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

@Mixin(value = LevelRenderer.class, priority = -10000)
public class MixinLevelRenderer
{
	@Inject(at = @At(value = "TAIL"), method = "renderLevel")
	private void renderLevel(PoseStack mtx, float frameTime, long nanoTime, boolean renderOutline, Camera camera, GameRenderer gameRenderer, LightTexture light, Matrix4f projMat, CallbackInfo ci)
	{
		Vec3 camPos = camera.getPosition();
		RenderSystem.depthMask(false);
		new ArrayList<>(UnleashedShaderEffects.EFFECTS).forEach(t -> 
		{
			Vec3 worldPos = t.pos;
			Vec3 pos = worldPos.subtract(camPos);
			mtx.pushPose();
			mtx.translate(pos.x, pos.y, pos.z);
			if(t.name.equals("Shockwave"))
			{
    			UnleashedClientUtil.applyShockwave(mtx, frameTime, t.tickCount);
			}
			mtx.popPose();
		});
		for(Entity entity : UnleashedUtil.getAllEntities(UnleashedClientUtil.MC.level))
		{
			if(!(entity instanceof IShaderEffect effect) || !effect.shouldApplyEffect())
			{
				continue;
			}
			mtx.pushPose();
			if(effect.getEffetName().equals("Galaxy"))
			{
				Vec3 pos = effect.getEffectPosition(entity).subtract(camPos);
				float scale = effect.getEffectScale() * 0.1F;
				mtx.translate(pos.x, pos.y + effect.getOffset(), pos.z);
				Vector3f vector3f = camera.getLookVector();
				if(effect.cameraRotation())
				{
					mtx.mulPose(camera.rotation());
					mtx.mulPose(Axis.XP.rotationDegrees(90.0F));
					vector3f = camera.getUpVector();
				}
				UnleashedClientUtil.applyGalaxy(mtx, frameTime, effect.getEffectTickCount(), scale, vector3f);
			}
			else if(effect.getEffetName().equals("Wormhole"))
			{
				Vec3 pos = effect.getEffectPosition(entity).subtract(camPos);
				mtx.translate(pos.x, pos.y + effect.getOffset(), pos.z);
				UnleashedClientUtil.applyWormhole(mtx, frameTime);
			}
			mtx.popPose();
		}
		if(camera.getEntity() != null)
		{
			Entity entity = camera.getEntity();
	        double x = Mth.lerp((double)frameTime, entity.xOld, entity.getX());
	        double y = Mth.lerp((double)frameTime, entity.yOld, entity.getY());
	        double z = Mth.lerp((double)frameTime, entity.zOld, entity.getZ());
	        
			if(entity instanceof LivingEntity living)
			{
				for(InteractionHand hand : InteractionHand.values())
				{
					ItemStack stack = living.getItemInHand(hand);
					if(stack.getItem() instanceof IShaderEffectItem effect)
					{
						if(effect.shouldApplyEffect(living, stack))
						{
							mtx.pushPose();
							if(effect.getEffetName(living, stack).equals("Galaxy"))
							{
								Vec3 pos = new Vec3(x, y, z).subtract(camPos);
								float scale = effect.getEffectScale(living, stack) * 0.1F;
								mtx.translate(pos.x, pos.y, pos.z);
								UnleashedClientUtil.applyGalaxy(mtx, frameTime, effect.getEffectTickCount(living, stack), scale, camera.getLookVector());
							}
							mtx.popPose();
						}
					}
				}
			}
		}
		RenderSystem.depthMask(true);
	}
}

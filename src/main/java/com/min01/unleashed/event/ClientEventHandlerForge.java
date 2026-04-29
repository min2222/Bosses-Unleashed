package com.min01.unleashed.event;

import java.util.concurrent.atomic.AtomicBoolean;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.config.UnleashedConfig;
import com.min01.unleashed.entity.CameraShakeEntity;
import com.min01.unleashed.misc.UnleashedBossBar;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.min01.unleashed.util.UnleashedUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BossesUnleashed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandlerForge
{
	public static final AtomicBoolean STARFIELD = new AtomicBoolean();

    @SubscribeEvent
    public static void onComputeCameraAngles(ViewportEvent.ComputeCameraAngles event) 
    {
        Player player = UnleashedClientUtil.MC.player;
        float delta = UnleashedClientUtil.MC.getPartialTick();
        float ticksExistedDelta = player.tickCount + delta;
        if(player != null)
        {
        	if(UnleashedConfig.cameraShakes.get())
        	{
                float shakeAmplitude = 0.0F;
                for(CameraShakeEntity cameraShake : player.level.getEntitiesOfClass(CameraShakeEntity.class, player.getBoundingBox().inflate(100.0F))) 
                {
                    if(cameraShake.distanceTo(player) < cameraShake.getRadius())
                    {
                        shakeAmplitude += cameraShake.getShakeAmount(player, delta);
                    }
                }
                if(shakeAmplitude > 1.0F)
                {
                    shakeAmplitude = 1.0F;
                }
                event.setPitch((float)(event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3.0F + 2.0F) * 25.0));
                event.setYaw((float)(event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5.0F + 1.0F) * 25.0));
                event.setRoll((float)(event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4.0F) * 25.0));
        	}
        }
    }
    
    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) 
    {
    	Player player = event.getEntity();
    	if(UnleashedUtil.isDash(player))
    	{
    		PoseStack stack = event.getPoseStack();
    		float yRot = Mth.rotLerp(event.getPartialTick(), player.yBodyRotO, player.yBodyRot);
    		stack.mulPose(Axis.YP.rotationDegrees(180.0F - yRot));
    		stack.mulPose(Axis.XP.rotationDegrees(-90.0F - player.getXRot()));
    		stack.mulPose(Axis.YP.rotationDegrees((player.tickCount + event.getPartialTick()) * -75.0F));
    	}
    }
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) 
    {
        if(event.phase == TickEvent.Phase.START) 
        {
        	if(UnleashedClientUtil.MC.player != null && UnleashedClientUtil.MC.level != null)
        	{
            	if(!UnleashedClientUtil.MC.isPaused())
            	{
                	UnleashedShaderEffects.EFFECTS.removeIf(t -> !t.isAlive());
            	}
        	}
            else if(STARFIELD.get())
            {
            	STARFIELD.set(false);
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBossEventProgress(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        ResourceLocation location = UnleashedBossBar.BOSS_MAP.getOrDefault(event.getBossEvent().getId(), null);
        if(location == null)
        	return;
        UnleashedBossBar bossBar = UnleashedBossBar.BOSS_BAR_MAP.getOrDefault(location, null);
        if(bossBar == null)
        	return;
        event.setCanceled(true);
        bossBar.draw(event);
    }
}

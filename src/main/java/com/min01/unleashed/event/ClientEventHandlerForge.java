package com.min01.unleashed.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.EntityCameraShake;
import com.min01.unleashed.misc.UnleashedBossBarType;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BossesUnleashed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandlerForge
{
	public static final AtomicBoolean STARFIELD = new AtomicBoolean();
    public static final Map<UUID, UnleashedBossBarType> BOSS_BAR_MAP = new HashMap<>();
    public static final Map<UUID, Entity> BOSS_MAP = new HashMap<>();
    public static final ResourceLocation JELLYFISH_BOSS_BAR_FRAME_TEXTURE = new ResourceLocation(BossesUnleashed.MODID, "textures/gui/celestial_jellyfish_bossbar_frame.png");
    public static final ResourceLocation JELLYFISH_BOSS_BAR_BAR_TEXTURE = new ResourceLocation(BossesUnleashed.MODID, "textures/gui/celestial_jellyfish_bossbar_bar.png");
    
    @SubscribeEvent
    public static void onSetupCamera(ViewportEvent.ComputeCameraAngles event) 
    {
        Player player = UnleashedClientUtil.MC.player;
        float delta = UnleashedClientUtil.MC.getFrameTime();
        float ticksExistedDelta = player.tickCount + delta;
        if(player != null)
        {
            float shakeAmplitude = 0.0F;
            for(EntityCameraShake cameraShake : player.level.getEntitiesOfClass(EntityCameraShake.class, player.getBoundingBox().inflate(100.0F))) 
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
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBossEventProgress(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        if(BOSS_BAR_MAP.containsKey(event.getBossEvent().getId()))
        {
            PoseStack poseStack = event.getGuiGraphics().pose();
            Component component = event.getBossEvent().getName();
            int i = UnleashedClientUtil.MC.getWindow().getGuiScaledWidth();
            int j = event.getY();
            int progressScaled = (int)(event.getBossEvent().getProgress() * 136.0F);
            int l = UnleashedClientUtil.MC.font.width(component);
            int i1 = i / 2 - l / 2;
            int j1 = j + 25;
            event.setCanceled(true);
            poseStack.pushPose();
            poseStack.translate(i1 / 9.5F, j - 23, 0);
            event.getGuiGraphics().blit(JELLYFISH_BOSS_BAR_FRAME_TEXTURE, event.getX(), event.getY(), 0, 0, 140, 32, 140, 32);
            event.getGuiGraphics().blit(JELLYFISH_BOSS_BAR_BAR_TEXTURE, event.getX(), event.getY(), 0, 0, progressScaled, 32, 140, 32);
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.translate(i1, j1, 0);
            UnleashedClientUtil.MC.font.drawInBatch(component.plainCopy().withStyle(ChatFormatting.AQUA).getVisualOrderText(), 0.0F, 0.0F, 16777215, true, poseStack.last().pose(), UnleashedClientUtil.MC.renderBuffers().bufferSource(), Font.DisplayMode.POLYGON_OFFSET, 0, LightTexture.FULL_BRIGHT);
            poseStack.popPose();
            event.setIncrement(event.getIncrement() + 7);
        }
    }
}

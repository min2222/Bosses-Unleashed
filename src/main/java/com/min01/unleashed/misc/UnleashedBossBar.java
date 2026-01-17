package com.min01.unleashed.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.util.UnleashedClientUtil;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.registries.ForgeRegistries;

//https://github.com/BobMowzie/MowziesMobs-Public/blob/main/src/main/java/com/bobmowzie/mowziesmobs/client/gui/CustomBossBar.java
public class UnleashedBossBar 
{
    public static final Map<UUID, ResourceLocation> BOSS_MAP = new HashMap<>();
    public static final Map<ResourceLocation, UnleashedBossBar> BOSS_BAR_MAP = new HashMap<>();
    
    static 
    {
    	register(() -> UnleashedEntities.CELESTIAL_JELLYFISH.get(), "celestial_jellyfish_bossbar", "celestial_jellyfish_bossbar_frame", 140, 32, 15, 0, 7, 127.0F, ChatFormatting.AQUA);
    }

    private final ResourceLocation baseTexture;
    private final ResourceLocation overlayTexture;

    private final int textureWidth;
    private final int textureHeight;
    
    private final int xOffset;
    private final int yOffset;

    private final int verticalIncrement;
    private final float progressScaled;

    private final ChatFormatting textColor;

    public UnleashedBossBar(ResourceLocation baseTexture, ResourceLocation overlayTexture, int textureWidth, int textureHeight, int xOffset, int yOffset, int verticalIncrement, float progressScaled, ChatFormatting textColor)
    {
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.verticalIncrement = verticalIncrement;
        this.progressScaled = progressScaled;
        this.textColor = textColor;
    }
    
    public static void register(Supplier<EntityType<?>> supplier, String baseTexture, String overlayTexture, int textureWidth, int textureHeight, int xOffset, int yOffset, int verticalIncrement, float progressScaled, ChatFormatting textColor)
    {
    	ResourceLocation base = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/gui/" + baseTexture + ".png");
    	ResourceLocation overlay = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "textures/gui/" + overlayTexture + ".png");
    	BOSS_BAR_MAP.put(ForgeRegistries.ENTITY_TYPES.getKey(supplier.get()), new UnleashedBossBar(base, overlay, textureWidth, textureHeight, xOffset, yOffset, verticalIncrement, progressScaled, textColor));
    }

    public void draw(CustomizeGuiOverlayEvent.BossEventProgress event)
    {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        int y = event.getY();
        
        int i = UnleashedClientUtil.MC.getWindow().getGuiScaledWidth();
        int j = y - 9;
        UnleashedClientUtil.MC.getProfiler().push("bossBarBase");

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.baseTexture);
        this.drawBar(guiGraphics, event.getX() + 1 + this.xOffset, y + this.yOffset, event.getBossEvent());
        Component component = event.getBossEvent().getName().copy().withStyle(this.textColor);
        UnleashedClientUtil.MC.getProfiler().pop();

        int l = UnleashedClientUtil.MC.font.width(component);
        int i1 = i / 2 - l / 2;
        int j1 = j;
        guiGraphics.drawString(UnleashedClientUtil.MC.font, component, i1, j1, 16777215);

        event.setIncrement(event.getIncrement() + this.verticalIncrement);
    }

    private void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent event) 
    {
        guiGraphics.blit(this.overlayTexture, x, y, 0, 0, this.textureWidth, this.textureHeight, this.textureWidth, this.textureHeight);
        int progressScaled = (int)(event.getProgress() * this.progressScaled);
        if(progressScaled > 0) 
        {
            guiGraphics.blit(this.baseTexture, x, y, 0, 0, 9 + progressScaled, this.textureHeight, this.textureWidth, this.textureHeight);
        }
    }
}

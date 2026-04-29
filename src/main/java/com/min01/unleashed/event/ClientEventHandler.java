package com.min01.unleashed.event;

import java.util.Map;
import java.util.Objects;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.model.CelestialJellyfishModel;
import com.min01.unleashed.entity.model.CelestialOrbModel;
import com.min01.unleashed.entity.model.JellyfishModel;
import com.min01.unleashed.entity.model.MadLumberjackModel;
import com.min01.unleashed.entity.renderer.CelestialBeamRenderer;
import com.min01.unleashed.entity.renderer.CelestialJellyfishRenderer;
import com.min01.unleashed.entity.renderer.CelestialOrbRenderer;
import com.min01.unleashed.entity.renderer.MadLumberjackRenderer;
import com.min01.unleashed.entity.renderer.NoneRenderer;
import com.min01.unleashed.entity.renderer.layer.CelestialJellyfishArmorLayer;
import com.min01.unleashed.item.CelestialJellyfishUmbrellaItem;
import com.min01.unleashed.item.UnleashedItems;
import com.min01.unleashed.item.model.CelestialJellyfishArmorModel;
import com.min01.unleashed.misc.UnleashedArmPoses;
import com.min01.unleashed.particle.CelestialExplosionParticle;
import com.min01.unleashed.particle.UnleashedParticles;
import com.min01.unleashed.shader.UnleashedShaders;
import com.min01.unleashed.world.effects.CelestialFieldDimensionSpecialEffects;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = BossesUnleashed.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		event.enqueueWork(() ->
		{
			UnleashedArmPoses.registerArmPoses();
	        ItemProperties.register(UnleashedItems.CELESTIAL_JELLYFISH_UMBRELLA.get(), ResourceLocation.parse("folded"), (pStack, pLevel, pEntity, pSeed) ->
	        {
	        	return CelestialJellyfishUmbrellaItem.isUnfolded(pStack) ? 0.0F : 1.0F;
	        });
		});
		/*try
		{
			AESUtil.encryptFiles(".png");
		}
		catch (Exception e)
		{
			
		}*/
	}
	
    @SubscribeEvent
    public static void onRegisterDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event)
    {
    	event.register(ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "celestial_field"), new CelestialFieldDimensionSpecialEffects());
    }
	
	@SubscribeEvent
	public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
	{
		event.registerReloadListener(new UnleashedShaders());
	}
	
	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event)
	{
		event.registerSpriteSet(UnleashedParticles.CELESTIAL_EXPLOSION.get(), CelestialExplosionParticle.Provider::new);
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(JellyfishModel.LAYER_LOCATION, JellyfishModel::createBodyLayer);
    	event.registerLayerDefinition(CelestialJellyfishModel.LAYER_LOCATION, CelestialJellyfishModel::createBodyLayer);
    	event.registerLayerDefinition(CelestialOrbModel.LAYER_LOCATION, CelestialOrbModel::createBodyLayer);
    	event.registerLayerDefinition(CelestialJellyfishArmorModel.LAYER_LOCATION, CelestialJellyfishArmorModel::createBodyLayer);
    	
    	event.registerLayerDefinition(MadLumberjackModel.LAYER_LOCATION, MadLumberjackModel::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(UnleashedEntities.CAMERA_SHAKE.get(), NoneRenderer::new);
    	
    	event.registerEntityRenderer(UnleashedEntities.CELESTIAL_JELLYFISH.get(), CelestialJellyfishRenderer::new);
    	event.registerEntityRenderer(UnleashedEntities.CELESTIAL_ORB.get(), CelestialOrbRenderer::new);
    	event.registerEntityRenderer(UnleashedEntities.CELESTIAL_BEAM.get(), CelestialBeamRenderer::new);
    	event.registerEntityRenderer(UnleashedEntities.WORMHOLE.get(), NoneRenderer::new);
    	event.registerEntityRenderer(UnleashedEntities.BLACKHOLE.get(), NoneRenderer::new);
    	
    	event.registerEntityRenderer(UnleashedEntities.MAD_LUMBERJACK.get(), MadLumberjackRenderer::new);
    }
    
	@SuppressWarnings({ "unchecked", "deprecation" })
	@SubscribeEvent
	public static void onAddLayers(EntityRenderersEvent.AddLayers event)
	{
		Map<EntityType<?>, EntityRenderer<?>> renderers = ObfuscationReflectionHelper.getPrivateValue(EntityRenderersEvent.AddLayers.class, event, "renderers");
		renderers.values().stream()
		.filter(LivingEntityRenderer.class::isInstance)
		.map(LivingEntityRenderer.class::cast)
		.forEach(t -> addLayers(t, event.getEntityModels()));
		
		event.getSkins().forEach(renderer -> 
		{
			LivingEntityRenderer<Player, HumanoidModel<Player>> skin = event.getSkin(renderer);
			addLayers(Objects.requireNonNull(skin), event.getEntityModels());
		});
	}
	
	private static <T extends LivingEntity, M extends HumanoidModel<T>> void addLayers(LivingEntityRenderer<T, M> renderer, EntityModelSet model)
	{
		renderer.addLayer(new CelestialJellyfishArmorLayer<>(renderer, new HumanoidModel<>(model.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(model.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
	}
}

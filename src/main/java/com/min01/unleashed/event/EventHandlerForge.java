package com.min01.unleashed.event;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.config.UnleashedConfig;
import com.min01.unleashed.entity.EntityWormhole;
import com.min01.unleashed.entity.UnleashedEntities;
import com.min01.unleashed.entity.living.EntityCelestialJellyfish;
import com.min01.unleashed.entity.projectile.EntityCelestialOrb;
import com.min01.unleashed.misc.UnleashedTags;
import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateStarfieldPacket;
import com.min01.unleashed.util.UnleashedUtil;
import com.min01.unleashed.world.UnleashedSavedData;
import com.min01.unleashed.world.UnleashedWorlds;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BossesUnleashed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge 
{
	@SubscribeEvent
	public static void onExplostionDetonate(ExplosionEvent.Detonate event)
	{
		if(event.getExplosion().getExploder() instanceof EntityCelestialJellyfish)
		{
			event.getAffectedEntities().removeIf(t -> t instanceof ItemEntity itemEntity && itemEntity.getItem().is(UnleashedTags.UnleashedItems.EXPLOSION_IMMUNE));
		}
	}
	
	@SubscribeEvent
	public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event)
	{
		Mob entity = event.getEntity();
		MobSpawnType type = event.getSpawnType();
		ServerLevelAccessor level = event.getLevel();
		DifficultyInstance difficulty = event.getDifficulty();
		if(entity instanceof EnderMan enderMan && enderMan.level.dimension() == Level.END && type == MobSpawnType.NATURAL)
		{
			if(level.getBiome(enderMan.blockPosition()).is(Biomes.END_HIGHLANDS) && difficulty.getDifficulty() != Difficulty.PEACEFUL)
			{
				if(Math.random() <= UnleashedConfig.wormholeSpawnChance.get() / 100.0F)
				{
					EntityWormhole wormhole = new EntityWormhole(UnleashedEntities.WORMHOLE.get(), enderMan.level);
					wormhole.setPos(enderMan.position());
					level.addFreshEntity(wormhole);
					event.setSpawnCancelled(true);
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		Level level = event.getLevel();
		Entity entity = event.getEntity();
		if(entity instanceof Player player && level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
		{
			UnleashedSavedData data = UnleashedSavedData.get(level);
			if(data != null)
			{
				UnleashedNetwork.sendToAll(new UpdateStarfieldPacket(data.isStarfield()));
				if(!data.isJellyfishSpawned())
				{
					EntityCelestialJellyfish jellyfish = new EntityCelestialJellyfish(UnleashedEntities.CELESTIAL_JELLYFISH.get(), level);
					float yRot = player.getRandom().nextFloat() * 360.0F;
					Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, player.getYHeadRot() + yRot), player.position(), 0, 4.5F, player.getRandom().nextInt(10, 20));
					jellyfish.setPos(lookPos);
					level.addFreshEntity(jellyfish);
					data.setJellyfishSpawned(true);
				}
			}
		}
	}
	
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
    	Player player = event.getEntity();
    	if(player.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
    	{
    		if(!player.getAbilities().instabuild && event.getItemStack().getItem() instanceof BlockItem)
    		{
    			event.setCancellationResult(InteractionResult.PASS);
        		event.setUseBlock(Result.DENY);
        		event.setCanceled(true);
    		}
    	}
    }
	
	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event)
	{
		LivingEntity entity = event.getEntity();
		if(entity instanceof ServerPlayer player && player.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
		{
			UnleashedSavedData data = UnleashedSavedData.get(player.level);
			if(data != null && data.isJellyfishSpawned() && event.getSource().getEntity() instanceof EntityCelestialJellyfish)
			{
				player.setRespawnPosition(UnleashedWorlds.CELESTIAL_FIELD, player.blockPosition(), player.getRespawnAngle(), player.isRespawnForced(), false);
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event)
	{
		Player player = event.player;
		UnleashedUtil.tickDash(player);
		if(UnleashedUtil.isDash(player))
		{
			int tick = UnleashedUtil.getDashTick(player);
			if(tick % 2 == 0)
			{
				EntityCelestialOrb orb = new EntityCelestialOrb(UnleashedEntities.CELESTIAL_ORB.get(), player.level);
				orb.setPos(player.position());
				orb.setOwner(player);
				player.level.addFreshEntity(orb);
			}
		}
	}
}
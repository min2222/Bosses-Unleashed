package com.min01.unleashed.event;

import com.min01.unleashed.BossesUnleashed;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.level.ExplosionEvent;
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
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		Level level = event.getLevel();
		if(event.getEntity() instanceof Player player)
		{
			UnleashedSavedData data = UnleashedSavedData.get(level);
			if(data != null)
			{
				UnleashedNetwork.sendToAll(new UpdateStarfieldPacket(data.isStarfield()));
				if(level.dimension() == UnleashedWorlds.CELESTIAL_FIELD && !data.isJellyfishSpawned())
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
	public static void onLivingDeath(LivingDeathEvent event)
	{
		LivingEntity entity = event.getEntity();
		if(entity instanceof ServerPlayer player)
		{
			UnleashedSavedData data = UnleashedSavedData.get(player.level);
			if(data != null && data.isJellyfishSpawned() && player.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD && event.getSource().getEntity() instanceof EntityCelestialJellyfish)
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
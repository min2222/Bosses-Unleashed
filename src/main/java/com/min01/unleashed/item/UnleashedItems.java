package com.min01.unleashed.item;

import java.util.function.Supplier;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.entity.UnleashedEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BossesUnleashed.MODID);
	
	public static final RegistryObject<Item> CELESTIAL_CORE = ITEMS.register("celestial_core", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> CELESTIAL_JELLY = ITEMS.register("celestial_jelly", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).alwaysEat().fast().build())));
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_UMBRELLA = ITEMS.register("celestial_jellyfish_umbrella", () -> new CelestialJellyfishUmbrellaItem());
	
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_MASK = ITEMS.register("celestial_jellyfish_mask", () -> new CelestialJellyfishArmorItem(Type.HELMET));
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_CHESTPLATE = ITEMS.register("celestial_jellyfish_chestplate", () -> new CelestialJellyfishArmorItem(Type.CHESTPLATE));
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_LEGGINGS = ITEMS.register("celestial_jellyfish_leggings", () -> new CelestialJellyfishArmorItem(Type.LEGGINGS));
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_BOOTS = ITEMS.register("celestial_jellyfish_boots", () -> new CelestialJellyfishArmorItem(Type.BOOTS));
	
	public static final RegistryObject<Item> CELESTIAL_JELLYFISH_SPAWN_EGG = registerSpawnEgg("celestial_jellyfish_spawn_egg", () -> UnleashedEntities.CELESTIAL_JELLYFISH.get(), 9757695, 10841311);
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
	
	public static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int color1, int color2) 
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
	}
}

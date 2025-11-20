package com.min01.unleashed.misc;

import com.min01.unleashed.BossesUnleashed;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UnleashedTags
{
	public static class UnleashedItems
	{
		public static final TagKey<Item> EXPLOSION_IMMUNE = create("explosion_immune");
		
		private static TagKey<Item> create(String name) 
		{
			return TagKey.create(Registries.ITEM, new ResourceLocation(BossesUnleashed.MODID, name));
		}
	}
}

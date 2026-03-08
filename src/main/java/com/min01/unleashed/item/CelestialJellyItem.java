package com.min01.unleashed.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CelestialJellyItem extends Item
{
	public CelestialJellyItem() 
	{
		super(new Item.Properties().rarity(Rarity.EPIC).food(new FoodProperties.Builder().nutrition(1).saturationMod(2.0F).alwaysEat().fast().build()));
	}

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) 
	{
		pTooltipComponents.add(Component.translatable("item.bossesunleashed.celestial_jelly.tooltip").withStyle(ChatFormatting.AQUA));
	}
}

package com.min01.unleashed.misc;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class UnleashedArmorMaterial implements ArmorMaterial
{
	private final String name;
	private final int[] durability;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;

	public UnleashedArmorMaterial(String name, int[] durability, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) 
	{
		this.name = name;
		this.durability = durability;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurabilityForType(ArmorItem.Type p_40484_) 
	{
		return this.durability[p_40484_.getSlot().getIndex()];
	}

	@Override
	public int getDefenseForType(ArmorItem.Type p_40487_)
	{
		return this.slotProtections[p_40487_.getSlot().getIndex()];
	}

	@Override
	public int getEnchantmentValue() 
	{
		return this.enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return this.sound;
	}

	@Override
	public Ingredient getRepairIngredient() 
	{
		return this.repairIngredient.get();
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return this.knockbackResistance;
	}
}

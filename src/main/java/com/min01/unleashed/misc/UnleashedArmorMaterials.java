package com.min01.unleashed.misc;

import com.min01.unleashed.item.UnleashedItems;
import com.min01.unleashed.sound.UnleashedSounds;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class UnleashedArmorMaterials
{
	public static final ArmorMaterial CELESTIAL_JELLYFISH = new UnleashedArmorMaterial("celestial_jellyfish", new int[]{400, 500, 800, 600}, new int[]{7, 8, 10, 9}, 30, UnleashedSounds.CELESTIAL_JELLYFISH_HURT.get(), 7.0F, -0.1F, () -> Ingredient.of(UnleashedItems.CELESTIAL_JELLY.get()));
}

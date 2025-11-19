package com.min01.unleashed.item;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.min01.unleashed.item.model.ModelCelestialJellyfishArmor;
import com.min01.unleashed.misc.UnleashedArmorMaterials;
import com.min01.unleashed.util.UnleashedClientUtil;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CelestialJellyfishArmorItem extends ArmorItem implements IGlowingArmor
{
	public CelestialJellyfishArmorItem(ArmorItem.Type type)
	{
		super(UnleashedArmorMaterials.CELESTIAL_JELLYFISH, type, new Item.Properties().rarity(Rarity.EPIC));
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) 
	{
		consumer.accept(new IClientItemExtensions()
		{
			@Override
			public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) 
			{
				ModelCelestialJellyfishArmor<?> model = new ModelCelestialJellyfishArmor<>(UnleashedClientUtil.MC.getEntityModels().bakeLayer(ModelCelestialJellyfishArmor.LAYER_LOCATION));
				model.helmet.visible = equipmentSlot == EquipmentSlot.HEAD;
				model.chestplate.visible = equipmentSlot == EquipmentSlot.CHEST;
				model.right_arm.visible = equipmentSlot == EquipmentSlot.CHEST;
				model.left_arm.visible = equipmentSlot == EquipmentSlot.CHEST;
				model.leggings.visible = equipmentSlot == EquipmentSlot.LEGS;
				model.r_leg.visible = equipmentSlot == EquipmentSlot.LEGS;
				model.l_leg.visible = equipmentSlot == EquipmentSlot.LEGS;
				model.right_feet.visible = equipmentSlot == EquipmentSlot.FEET;
				model.left_feet.visible = equipmentSlot == EquipmentSlot.FEET;
				return model;
			}
		});
	}
	
	@Override
	public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
	{
		return "bossesunleashed:textures/armor/celestial_jellyfish_armor.png";
	}
}

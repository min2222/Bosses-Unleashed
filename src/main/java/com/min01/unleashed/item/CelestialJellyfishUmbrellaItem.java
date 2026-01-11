package com.min01.unleashed.item;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.min01.unleashed.misc.UnleashedArmPoses;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class CelestialJellyfishUmbrellaItem extends SwordItem
{
	public final Multimap<Attribute, AttributeModifier> attributeModifiers;
	
	public CelestialJellyfishUmbrellaItem()
	{
		super(Tiers.NETHERITE, 0, 0, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant());
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Umbrella Attack Damage", 7.0F, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Umbrella Attack Speed", -3.2F, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}
	
	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
	{
		boolean isUnfolded = isUnfolded(pStack);
		if(pIsSelected)
		{
			if(isUnfolded)
			{
				if(pEntity.getDeltaMovement().y < 0.0F)
				{
					pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(1.0F, 0.65F, 1.0F));
				}
				pEntity.resetFallDistance();
			}
		}
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) 
	{
		pTooltipComponents.add(Component.translatable("item.bossesunleashed.celestial_jellyfish_umbrella.tooltip").withStyle(ChatFormatting.AQUA));
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair)
	{
		return pRepair.is(UnleashedItems.CELESTIAL_JELLY.get());
	}
	
	@Override
	public boolean canBeDepleted() 
	{
		return false;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) 
	{
		ItemStack stack = pPlayer.getItemInHand(pUsedHand);
		boolean isUnfolded = isUnfolded(stack);
		if(pPlayer.isShiftKeyDown())
		{
			setUnfolded(stack, !isUnfolded);
		}
		else
		{
			if(isUnfolded)
			{
				return InteractionResultHolder.consume(stack);
			}
			else if(pPlayer.onGround())
			{
				Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, pPlayer.getYHeadRot()), pPlayer.position(), 0, 2, 100.0F);
				pPlayer.setDeltaMovement(UnleashedUtil.getVelocityTowards(pPlayer.position(), lookPos, 4.0F).add(0.0F, 0.25F, 0.0F));
				pPlayer.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get(), 1.5F, 1.0F);
				UnleashedUtil.setDashTick(pPlayer, 20);
				pPlayer.getCooldowns().addCooldown(this, 60);
			}
			return InteractionResultHolder.consume(stack);
		}
		return InteractionResultHolder.pass(stack);
	}
	
	@Override
	public void onStopUsing(ItemStack stack, LivingEntity entity, int count) 
	{
		
	}
	
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) 
	{
		consumer.accept(new IClientItemExtensions() 
		{
			@Override
			public @Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) 
			{
				return UnleashedArmPoses.CELESTIAL_UMBRELLA;
			}
		});
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot)
	{
		return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction) 
	{
		return super.canPerformAction(stack, toolAction) && toolAction != ToolActions.SWORD_SWEEP;
	}
	
	@Override
	public int getUseDuration(ItemStack pStack)
	{
		return 60;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) 
	{
		return newStack.getItem() != this;
	}
	
    public static boolean isUnfolded(ItemStack stack)
    {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getBoolean("isUnfold") : false;
    }

    public static void setUnfolded(ItemStack stack, boolean value)
    {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("isUnfold", value);
    }
}

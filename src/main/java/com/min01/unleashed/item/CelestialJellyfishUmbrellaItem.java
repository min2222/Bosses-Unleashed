package com.min01.unleashed.item;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.min01.unleashed.entity.EntityCameraShake;
import com.min01.unleashed.misc.UnleashedArmPoses;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.sound.UnleashedSounds;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class CelestialJellyfishUmbrellaItem extends SwordItem implements IShaderEffectItem
{
	public final Multimap<Attribute, AttributeModifier> attributeModifiers;
	
	public CelestialJellyfishUmbrellaItem()
	{
		super(Tiers.NETHERITE, 0, 0, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Umbrella Attack Damage", 7.0F, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Umbrella Attack Speed", -3.2F, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}
	
	@Override
	public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) 
	{
		boolean isUnfolded = isUnfolded(p_41404_);
		if(p_41408_)
		{
			if(isUnfolded)
			{
				if(p_41406_.getDeltaMovement().y < 0.0F)
				{
					p_41406_.setDeltaMovement(p_41406_.getDeltaMovement().multiply(1.0F, 0.65F, 1.0F));
				}
				p_41406_.resetFallDistance();
			}
		}
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) 
	{
		p_41423_.add(Component.translatable("item.bossesunleashed.celestial_jellyfish_umbrella.tooltip").withStyle(ChatFormatting.AQUA));
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_)
	{
		return p_43312_.is(UnleashedItems.CELESTIAL_JELLY.get());
	}
	
	@Override
	public boolean canBeDepleted() 
	{
		return false;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_)
	{
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		boolean isUnfolded = isUnfolded(stack);
		if(p_41433_.isShiftKeyDown())
		{
			setUnfolded(stack, !isUnfolded);
		}
		else
		{
			if(isUnfolded)
			{
				p_41433_.startUsingItem(p_41434_);
				p_41432_.playSound(p_41433_, p_41433_.getX(), p_41433_.getY(), p_41433_.getZ(), UnleashedSounds.CELESTIAL_JELLYFISH_TRANSFORM.get(), p_41433_.getSoundSource(), 1.0F, 1.0F);
				return InteractionResultHolder.consume(stack);
			}
			else
			{
				Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, p_41433_.getYHeadRot()), p_41433_.position(), 0, 2, 100.0F);
				p_41433_.setDeltaMovement(UnleashedUtil.fromToVector(p_41433_.position(), lookPos, 4.0F).add(0.0F, 0.25F, 0.0F));
				p_41433_.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_DASH.get());
				UnleashedUtil.setDashTick(p_41433_, 20);
				p_41433_.getCooldowns().addCooldown(this, 60);
			}
			return InteractionResultHolder.consume(stack);
		}
		return InteractionResultHolder.pass(stack);
	}
	
	@Override
	public void onStopUsing(ItemStack stack, LivingEntity entity, int count) 
	{
		if(this.getUseDuration(stack) - entity.getUseItemRemainingTicks() >= this.getUseDuration(stack))
		{
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(entity.getXRot(), entity.getYHeadRot()), entity.position(), 0, 0, 500.0F);
			HitResult result = entity.level.clip(new ClipContext(entity.position(), lookPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
			if(result instanceof BlockHitResult blockHit)
			{
				if(blockHit.getType() != HitResult.Type.MISS)
				{
					BlockPos blockPos = blockHit.getBlockPos().above();
					entity.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
				}
			}
			UnleashedShaderEffects.addEffect(entity.level, "Shockwave", entity.position(), 100, 1.0F);
			EntityCameraShake.cameraShake(entity.level, entity.position(), 100.0F, 0.1F, 0, 20);
			entity.playSound(UnleashedSounds.CELESTIAL_JELLYFISH_EXPLOSION.get());
			if(entity instanceof Player player)
			{
				player.getCooldowns().addCooldown(this, 60);
			}
		}
		if(entity instanceof ServerPlayer player)
		{
			player.connection.send(new ClientboundStopSoundPacket(UnleashedSounds.CELESTIAL_JELLYFISH_TRANSFORM.get().getLocation(), player.getSoundSource()));
		}
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
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_41388_)
	{
		return p_41388_ == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(p_41388_);
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction) 
	{
		return super.canPerformAction(stack, toolAction) && toolAction != ToolActions.SWORD_SWEEP;
	}
	
	@Override
	public boolean shouldApplyEffect(LivingEntity entity, ItemStack stack) 
	{
		return entity.isUsingItem() && entity.getUseItem().getItem() == this && isUnfolded(stack);
	}
	
	@Override
	public String getEffetName(LivingEntity entity, ItemStack stack) 
	{
		return "Galaxy";
	}
	
	@Override
	public float getEffectScale(LivingEntity entity, ItemStack stack) 
	{
		return Math.min(this.getUseDuration(stack) - entity.getUseItemRemainingTicks(), 100);
	}
	
	@Override
	public int getEffectTickCount(LivingEntity entity, ItemStack stack) 
	{
		return Math.min(this.getUseDuration(stack) - entity.getUseItemRemainingTicks(), 100);
	}
	
	@Override
	public int getUseDuration(ItemStack p_41454_)
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

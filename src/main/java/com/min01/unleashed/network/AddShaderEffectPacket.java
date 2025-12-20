package com.min01.unleashed.network;

import java.util.function.Supplier;

import com.min01.unleashed.misc.UnleashedEntityDataSerializers;
import com.min01.unleashed.shader.UnleashedShaderEffects;
import com.min01.unleashed.shader.UnleashedShaderEffects.ShaderEffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public class AddShaderEffectPacket 
{
	private final ResourceKey<Level> dimension;
	private final String name;
	private final Vec3 pos;
	private final int lifeTime;
	private final float scale;

	public AddShaderEffectPacket(ResourceKey<Level> dimension, String name, Vec3 pos, int lifeTime, float scale) 
	{
		this.dimension = dimension;
		this.name = name;
		this.pos = pos;
		this.lifeTime = lifeTime;
		this.scale = scale;
	}

	public static AddShaderEffectPacket read(FriendlyByteBuf buf)
	{
		return new AddShaderEffectPacket(buf.readResourceKey(Registries.DIMENSION), buf.readUtf(), UnleashedEntityDataSerializers.readVec3(buf), buf.readInt(), buf.readFloat());
	}

	public void write(FriendlyByteBuf buf)
	{
		buf.writeResourceKey(this.dimension);
		buf.writeUtf(this.name);
		UnleashedEntityDataSerializers.writeVec3(buf, this.pos);
		buf.writeInt(this.lifeTime);
		buf.writeFloat(this.scale);
	}

	public static boolean handle(AddShaderEffectPacket message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() ->
		{
			if(ctx.get().getDirection().getReceptionSide().isClient())
			{
				UnleashedShaderEffects.EFFECTS.add(new ShaderEffect(message.dimension, message.name, message.pos, message.lifeTime, message.scale));
			}
		});
		ctx.get().setPacketHandled(true);
		return true;
	}
}

package com.min01.unleashed.misc;

import com.min01.unleashed.BossesUnleashed;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedEntityDataSerializers
{
	public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, BossesUnleashed.MODID);
	
	public static final RegistryObject<EntityDataSerializer<Vec3>> VEC3 = SERIALIZERS.register("serializer_vec3", () -> EntityDataSerializer.simple(UnleashedEntityDataSerializers::writeVec3, UnleashedEntityDataSerializers::readVec3));

	public static ByteBuf writeVec3(FriendlyByteBuf buf, Vec3 vec)
	{
		buf.writeDouble(vec.x);
		buf.writeDouble(vec.y);
		buf.writeDouble(vec.z);
		return buf;
	}
	
	public static Vec3 readVec3(ByteBuf buf)
	{
		return new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
}

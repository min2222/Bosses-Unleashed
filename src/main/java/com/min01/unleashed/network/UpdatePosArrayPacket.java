package com.min01.unleashed.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.unleashed.entity.IPosArray;
import com.min01.unleashed.misc.UnleashedEntityDataSerializers;
import com.min01.unleashed.util.UnleashedUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public class UpdatePosArrayPacket 
{
	private final UUID entityUUID;
	private final int array;
	private final Vec3 pos;

	public UpdatePosArrayPacket(Entity entity, Vec3 pos, int array) 
	{
		this.entityUUID = entity.getUUID();
		this.pos = pos;
		this.array = array;
	}

	public UpdatePosArrayPacket(FriendlyByteBuf buf)
	{
		this.entityUUID = buf.readUUID();
		this.pos = UnleashedEntityDataSerializers.readVec3(buf);
		this.array = buf.readInt();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.entityUUID);
		UnleashedEntityDataSerializers.writeVec3(buf, this.pos);
		buf.writeInt(this.array);
	}

	public static class Handler 
	{
		public static boolean onMessage(UpdatePosArrayPacket message, Supplier<NetworkEvent.Context> ctx)
		{
			ctx.get().enqueueWork(() ->
			{
				if(ctx.get().getDirection().getReceptionSide().isServer())
				{
					Entity entity = UnleashedUtil.getEntityByUUID(ctx.get().getSender().level, message.entityUUID);
					if(entity instanceof IPosArray mob) 
					{
						mob.getPosArray()[message.array] = message.pos;
					}
				}
				else
				{
					UnleashedUtil.getClientLevel(t -> 
					{
						Entity entity = UnleashedUtil.getEntityByUUID(t, message.entityUUID);
						if(entity instanceof IPosArray mob) 
						{
							mob.getPosArray()[message.array] = message.pos;
						}
					});
				}
			});
			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}

package com.min01.unleashed.world;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateStarfieldPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class UnleashedSavedData extends SavedData
{
	public static final String NAME = "unleashed_data";
	protected boolean isStarfield;
	protected boolean isJellyfishSpawned;
	protected final Map<UUID, ResourceKey<Level>> respawnDimension = new HashMap<>();
	protected final Map<UUID, ResourceKey<Level>> prevDimension = new HashMap<>();
	protected final Map<UUID, BlockPos> respawnPos =  new HashMap<>();
	protected final Map<UUID, BlockPos> prevPos =  new HashMap<>();
	
    public static UnleashedSavedData get(Level level)
    {
        if(level instanceof ServerLevel serverLevel) 
        {
            DimensionDataStorage storage = serverLevel.getDataStorage();
            UnleashedSavedData data = storage.computeIfAbsent(UnleashedSavedData::load, UnleashedSavedData::new, NAME);
            return data;
        }
        return null;
    }

    public static UnleashedSavedData load(CompoundTag nbt) 
    {
    	UnleashedSavedData data = new UnleashedSavedData();
    	data.setStarfield(nbt.getBoolean("isStarfield"));
    	data.setJellyfishSpawned(nbt.getBoolean("isJellyfishSpawned"));

        ListTag respawnDim = nbt.getList("RespawnDimension", 10);
        ListTag prevDim = nbt.getList("PrevDimension", 10);
        ListTag respawnP = nbt.getList("RespawnPos", 10);
        ListTag prevP = nbt.getList("PrevPos", 10);
        
        for(int i = 0; i < respawnDim.size(); ++i) 
        {
        	CompoundTag tag = respawnDim.getCompound(i);
        	data.setRespawnDimension(tag.getUUID("PlayerUUID"), ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("Dimension"))));
        }
        
        for(int i = 0; i < prevDim.size(); ++i) 
        {
        	CompoundTag tag = prevDim.getCompound(i);
        	data.setPrevDimension(tag.getUUID("PlayerUUID"), ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("Dimension"))));
        }
        
        for(int i = 0; i < respawnP.size(); ++i) 
        {
        	CompoundTag tag = respawnP.getCompound(i);
        	data.setRespawnPos(tag.getUUID("PlayerUUID"), NbtUtils.readBlockPos(tag.getCompound("BlockPos")));
        }
        
        for(int i = 0; i < prevP.size(); ++i) 
        {
        	CompoundTag tag = prevP.getCompound(i);
        	data.setRespawnPos(tag.getUUID("PlayerUUID"), NbtUtils.readBlockPos(tag.getCompound("BlockPos")));
        }
        return data;
    }
	
	@Override
	public CompoundTag save(CompoundTag nbt)
	{
		nbt.putBoolean("isStarfield", this.isStarfield);
		nbt.putBoolean("isJellyfishSpawned", this.isJellyfishSpawned);
		ListTag respawnDim = new ListTag();
		ListTag prevDim = new ListTag();
		ListTag respawnP = new ListTag();
		ListTag prevP = new ListTag();
		this.respawnDimension.forEach((t, u) ->
		{
			CompoundTag tag = new CompoundTag();
			tag.putUUID("PlayerUUID", t);
			tag.putString("Dimension", u.location().toString());
			respawnDim.add(tag);
		});
		this.prevDimension.forEach((t, u) ->
		{
			CompoundTag tag = new CompoundTag();
			tag.putUUID("PlayerUUID", t);
			tag.putString("Dimension", u.location().toString());
			prevDim.add(tag);
		});
		this.respawnPos.forEach((t, u) ->
		{
			CompoundTag tag = new CompoundTag();
			tag.putUUID("PlayerUUID", t);
			tag.put("BlockPos", NbtUtils.writeBlockPos(u));
			respawnP.add(tag);
		});
		this.prevPos.forEach((t, u) ->
		{
			CompoundTag tag = new CompoundTag();
			tag.putUUID("PlayerUUID", t);
			tag.put("BlockPos", NbtUtils.writeBlockPos(u));
			prevP.add(tag);
		});
		nbt.put("RespawnDimension", respawnDim);
		nbt.put("PrevDimension", prevDim);
		nbt.put("RespawnPos", respawnP);
		nbt.put("PrevPos", prevP);
		return nbt;
	}
	
	public void setStarfield(boolean value)
	{
		this.isStarfield = value;
		UnleashedNetwork.sendToAll(new UpdateStarfieldPacket(value));
		this.setDirty();
	}
	
	public boolean isStarfield()
	{
		return this.isStarfield;
	}
	
	public void setJellyfishSpawned(boolean value)
	{
		this.isJellyfishSpawned = value;
		this.setDirty();
	}
	
	public boolean isJellyfishSpawned()
	{
		return this.isJellyfishSpawned;
	}
	
	public void setRespawnDimension(UUID player, ResourceKey<Level> level)
	{
		this.respawnDimension.put(player, level);
		this.setDirty();
	}
	
	public ResourceKey<Level> getRespawnDimension(UUID player)
	{
		return this.respawnDimension.get(player);
	}
	
	public void setPrevDimension(UUID player, ResourceKey<Level> level)
	{
		this.prevDimension.put(player, level);
		this.setDirty();
	}
	
	public ResourceKey<Level> getPrevDimension(UUID player)
	{
		return this.prevDimension.get(player);
	}
	
	public void setRespawnPos(UUID player, BlockPos pos)
	{
		this.respawnPos.put(player, pos);
		this.setDirty();
	}
	
	public BlockPos getRespawnPos(UUID player)
	{
		return this.respawnPos.get(player);
	}
	
	public void setPrevPos(UUID player, BlockPos pos)
	{
		this.prevPos.put(player, pos);
		this.setDirty();
	}
	
	public BlockPos getPrevPos(UUID player)
	{
		return this.prevPos.get(player);
	}
}

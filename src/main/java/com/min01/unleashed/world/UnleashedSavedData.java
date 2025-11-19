package com.min01.unleashed.world;

import com.min01.unleashed.network.UnleashedNetwork;
import com.min01.unleashed.network.UpdateStarfieldPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
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
	protected ResourceKey<Level> respawnDimension = Level.OVERWORLD;
	protected ResourceKey<Level> prevDimension = Level.OVERWORLD;
	protected BlockPos respawnPos = BlockPos.ZERO;
	protected BlockPos prevPos = BlockPos.ZERO;
	
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
    	data.setRespawnDimension(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("RespawnDimension"))));
    	data.setPrevDimension(ResourceKey.create(Registries.DIMENSION, new ResourceLocation(nbt.getString("PrevDimension"))));
    	data.setRespawnPos(NbtUtils.readBlockPos(nbt.getCompound("RespawnPos")));
    	data.setPrevPos(NbtUtils.readBlockPos(nbt.getCompound("PrevPos")));
        return data;
    }
	
	@Override
	public CompoundTag save(CompoundTag nbt)
	{
		nbt.putBoolean("isStarfield", this.isStarfield);
		nbt.putBoolean("isJellyfishSpawned", this.isJellyfishSpawned);
		nbt.putString("RespawnDimension", this.respawnDimension.location().toString());
		nbt.putString("PrevDimension", this.prevDimension.location().toString());
		nbt.put("RespawnPos", NbtUtils.writeBlockPos(this.respawnPos));
		nbt.put("PrevPos", NbtUtils.writeBlockPos(this.prevPos));
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
	
	public void setRespawnDimension(ResourceKey<Level> level)
	{
		this.respawnDimension = level;
		this.setDirty();
	}
	
	public ResourceKey<Level> getRespawnDimension()
	{
		return this.respawnDimension;
	}
	
	public void setPrevDimension(ResourceKey<Level> level)
	{
		this.prevDimension = level;
		this.setDirty();
	}
	
	public ResourceKey<Level> getPrevDimension()
	{
		return this.prevDimension;
	}
	
	public void setRespawnPos(BlockPos pos)
	{
		this.respawnPos = pos;
		this.setDirty();
	}
	
	public BlockPos getRespawnPos()
	{
		return this.respawnPos;
	}
	
	public void setPrevPos(BlockPos pos)
	{
		this.prevPos = pos;
		this.setDirty();
	}
	
	public BlockPos getPrevPos()
	{
		return this.prevPos;
	}
}

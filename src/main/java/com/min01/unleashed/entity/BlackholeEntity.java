package com.min01.unleashed.entity;

import java.util.List;

import com.min01.unleashed.util.UnleashedUtil;
import com.min01.unleashed.world.UnleashedWorlds;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BlackholeEntity extends Entity implements IShaderEffect
{
	public BlackholeEntity(EntityType<?> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
		this.noCulling = true;
	}

	@Override
	protected void defineSynchedData() 
	{
		
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.tickCount == 60)
		{
    		this.spawnWormhole();
			this.discard();
		}
		else if(this.tickCount == 20)
		{
			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(100.0F));
			list.forEach(t -> 
			{
				t.hurt(this.damageSources().magic(), 1000.0F);
			});
		}
	}
    
    public void spawnWormhole()
    {
    	if(this.level.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
    	{
			float yRot = this.level.random.nextFloat() * 360.0F;
			Vec3 lookPos = UnleashedUtil.getLookPos(new Vec2(0.0F, this.getYHeadRot() + yRot), this.position(), 0, 0, this.level.random.nextInt(10, 20));
        	WormholeEntity wormhole = new WormholeEntity(UnleashedEntities.WORMHOLE.get(), this.level);
        	wormhole.setPos(lookPos);
        	this.level.addFreshEntity(wormhole);
    	}
    }

	@Override
	protected void readAdditionalSaveData(CompoundTag pCompound)
	{
		
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag pCompound)
	{
		
	}
	
	@Override
	public String getEffetName() 
	{
		return "Blackhole";
	}
	
	@Override
	public int getEffectTickCount() 
	{
		return this.tickCount;
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

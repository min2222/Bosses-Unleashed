package com.min01.unleashed.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class EntityCameraShake extends Entity 
{
	private static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.defineId(EntityCameraShake.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAGNITUDE = SynchedEntityData.defineId(EntityCameraShake.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DURATION = SynchedEntityData.defineId(EntityCameraShake.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FADE_DURATION = SynchedEntityData.defineId(EntityCameraShake.class, EntityDataSerializers.INT);

    public EntityCameraShake(EntityType<?> type, Level world) 
    {
        super(type, world);
    }

    public EntityCameraShake(Level world, Vec3 position, float radius, float magnitude, int duration, int fadeDuration) 
    {
        super(UnleashedEntities.CAMERA_SHAKE.get(), world);
        this.setRadius(radius);
        this.setMagnitude(magnitude);
        this.setDuration(duration);
        this.setFadeDuration(fadeDuration);
        this.setPos(position.x, position.y, position.z);
    }

    @OnlyIn(Dist.CLIENT)
    public float getShakeAmount(Player player, float delta) 
    {
        float ticksDelta = this.tickCount + delta;
        float timeFrac = 1.0F - (ticksDelta - this.getDuration()) / (this.getFadeDuration() + 1.0F);
        float baseAmount = ticksDelta < this.getDuration() ? this.getMagnitude() : timeFrac * timeFrac * this.getMagnitude();
        Vec3 playerPos = player.getEyePosition(delta);
        float distFrac = (float) (1.0F - Mth.clamp(this.position().distanceTo(playerPos) / this.getRadius(), 0, 1));
        return baseAmount * distFrac * distFrac;
    }

    @Override
    public void tick() 
    {
        super.tick();
        if(this.tickCount > this.getDuration() + this.getFadeDuration()) 
        {
        	this.discard();
        }
    }

    @Override
    protected void defineSynchedData()
    {
        this.entityData.define(RADIUS, 10.0F);
        this.entityData.define(MAGNITUDE, 1.0F);
        this.entityData.define(DURATION, 0);
        this.entityData.define(FADE_DURATION, 5);
    }

    public float getRadius() 
    {
        return this.entityData.get(RADIUS);
    }

    public void setRadius(float radius)
    {
        this.entityData.set(RADIUS, radius);
    }

    public float getMagnitude() 
    {
        return this.entityData.get(MAGNITUDE);
    }

    public void setMagnitude(float magnitude) 
    {
        this.entityData.set(MAGNITUDE, magnitude);
    }

    public int getDuration() 
    {
        return this.entityData.get(DURATION);
    }

    public void setDuration(int duration)
    {
        this.entityData.set(DURATION, duration);
    }

    public int getFadeDuration()
    {
        return this.entityData.get(FADE_DURATION);
    }

    public void setFadeDuration(int fadeDuration) 
    {
        this.entityData.set(FADE_DURATION, fadeDuration);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) 
    {
        this.setRadius(compound.getFloat("radius"));
        this.setMagnitude(compound.getFloat("magnitude"));
        this.setDuration(compound.getInt("duration"));
        this.setFadeDuration(compound.getInt("fadeDuration"));
        this.tickCount = compound.getInt("tickCount");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound)
    {
        compound.putFloat("radius", this.getRadius());
        compound.putFloat("magnitude", this.getMagnitude());
        compound.putInt("duration", this.getDuration());
        compound.putInt("fadeDuration", this.getFadeDuration());
        compound.putInt("tickCount", this.tickCount);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() 
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static void cameraShake(Level world, Vec3 position, float radius, float magnitude, int duration, int fadeDuration)
    {
        if(!world.isClientSide) 
        {
            EntityCameraShake cameraShake = new EntityCameraShake(world, position, radius, magnitude, duration, fadeDuration);
            world.addFreshEntity(cameraShake);
        }
    }
}

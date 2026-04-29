package com.min01.unleashed.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.joml.Math;

import com.google.common.collect.ImmutableList;
import com.min01.unleashed.capabilities.DashCapabilityImpl;
import com.min01.unleashed.capabilities.IDashCapability;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class UnleashedUtil 
{
	public static final Method GET_ENTITY = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
	
    public static float rotlerp(float start, float end, float maxStep) 
    {
        float delta = Mth.wrapDegrees(end - start);
        float clampedDelta = Mth.clamp(delta, -maxStep, maxStep);
        return Mth.wrapDegrees(start + clampedDelta);
    }
    
	public static void moveStructurePiece(Structure.GenerationContext pContext, Heightmap.Types types, StructurePiece piece, StructureTemplate template, Rotation rotation, Mirror mirror, Consumer<Integer> consumer)
	{
		ChunkPos chunkPos = pContext.chunkPos();
		ChunkGenerator chunkGenerator = pContext.chunkGenerator();
		RandomSource random = pContext.random();
		RandomState randomState = pContext.randomState();
		LevelHeightAccessor heightAccessor = pContext.heightAccessor();
		BlockPos blockPos = chunkPos.getWorldPosition();
		BlockPos blockPos1 = new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
		BoundingBox boundingBox = template.getBoundingBox(blockPos, rotation, blockPos1, mirror);
		BlockPos blockPos2 = boundingBox.getCenter();
		int i = chunkGenerator.getBaseHeight(blockPos2.getX(), blockPos2.getZ(), types, heightAccessor, randomState);
		int j = findSuitableY(types, random, chunkGenerator, i, piece.getBoundingBox(), heightAccessor, randomState);
		consumer.accept(j);
	}
	
	//copied from RuinedPortalStructure
	public static int findSuitableY(Heightmap.Types types, RandomSource pRandom, ChunkGenerator pChunkGenerator, int pHeight, BoundingBox pBox, LevelHeightAccessor pLevel, RandomState pRandomState)
	{
		int j = pLevel.getMinBuildHeight() + 15;
		int i = pHeight;
		List<BlockPos> list1 = ImmutableList.of(new BlockPos(pBox.minX(), 0, pBox.minZ()), new BlockPos(pBox.maxX(), 0, pBox.minZ()), new BlockPos(pBox.minX(), 0, pBox.maxZ()), new BlockPos(pBox.maxX(), 0, pBox.maxZ()));
		List<NoiseColumn> list = list1.stream().map(t ->
		{
			return pChunkGenerator.getBaseColumn(t.getX(), t.getZ(), pLevel, pRandomState);
		}).collect(Collectors.toList());
		
		int l;
		for(l = i; l > j; --l) 
		{
			int i1 = 0;
			for(NoiseColumn noisecolumn : list)
			{
				BlockState blockstate = noisecolumn.getBlock(l);
				if(types.isOpaque().test(blockstate)) 
				{
					++i1;
					if(i1 == 3)
					{
						return l;
					}
				}
			}
		}
		return l;
	}
	
    public static Vec3 generateNewTarget(Mob mob, Predicate<BlockState> predicate) 
    {
        Level world = mob.level;
        Vec3 radius = new Vec3(35, 15, 35);
        for(int i = 0; i < 10; i++)
        {
        	Vec3 pos = getSpreadPosition(mob, radius);
        	HitResult hitResult = world.clip(new ClipContext(mob.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, mob));
        	if(hitResult instanceof BlockHitResult blockHit)
        	{
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(targetPos);
                if(predicate.test(blockState))
                {
                	return blockHit.getLocation();
                }
        	}
        }
        return null;
    }
    
	public static BlockPos getGroundPos(BlockGetter level, double x, double startY, double z)
    {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(x, startY, z);
        do
        {
        	mutablePos.move(Direction.DOWN);
        }
        while((level.getBlockState(mutablePos).isAir() || !level.getFluidState(mutablePos).isEmpty() || !level.getBlockState(mutablePos).isCollisionShapeFullBlock(level, mutablePos)) && mutablePos.getY() > level.getMinBuildHeight());
        return mutablePos.immutable();
    }
	
	public static void tickDash(LivingEntity entity)
	{
		IDashCapability cap = entity.getCapability(DashCapabilityImpl.DASH).orElse(new DashCapabilityImpl(entity));
		cap.tick(entity);
	}
	
	public static void setDashTick(LivingEntity entity, int tick)
	{
		IDashCapability cap = entity.getCapability(DashCapabilityImpl.DASH).orElse(new DashCapabilityImpl(entity));
		cap.setDashTick(tick);
	}
	
	public static boolean isDash(LivingEntity entity)
	{
		IDashCapability cap = entity.getCapability(DashCapabilityImpl.DASH).orElse(new DashCapabilityImpl(entity));
		return cap.isDash();
	}
	
	public static int getDashTick(LivingEntity entity)
	{
		IDashCapability cap = entity.getCapability(DashCapabilityImpl.DASH).orElse(new DashCapabilityImpl(entity));
		return cap.getDashTick();
	}
	
	@SuppressWarnings("unchecked")
	public static Iterable<Entity> getAllEntities(Level level)
	{
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) GET_ENTITY.invoke(level);
			return entities.getAll();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Vec2 lookAt(Vec3 startPos, Vec3 pos)
	{
		Vec3 vec3 = startPos;
		double d0 = pos.x - vec3.x;
		double d1 = pos.y - vec3.y;
		double d2 = pos.z - vec3.z;
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		float xRot = Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * (double)(180.0F / (float)Math.PI))));
		float yRot = Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * (double)(180.0F / (float)Math.PI)) - 90.0F);
	    return new Vec2(xRot, yRot);
	}
	
	public static Vec3 getSpreadPosition(Level level, Vec3 startPos, double range)
	{
        double x = startPos.x + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        double y = startPos.y + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        double z = startPos.z + (level.random.nextDouble() - level.random.nextDouble()) * range + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static void dashToward(Entity entity, float scale)
	{
        float x = (float) Math.cos(Math.toRadians(entity.getYHeadRot() + 90));
        float z = (float) Math.sin(Math.toRadians(entity.getYHeadRot() + 90));
        entity.push(x * scale, 0, z * scale);
	}
	
	public static Vec3 getSpreadPosition(Entity entity, Vec3 range)
	{
        double x = entity.getX() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range.x + 0.5D;
        double y = entity.getY() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range.y + 0.5D;
        double z = entity.getZ() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range.z + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static Vec3 getSpreadPosition(Entity entity, double range)
	{
        double x = entity.getX() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        double y = entity.getY() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        double z = entity.getZ() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * range + 0.5D;
        return new Vec3(x, y, z);
	}
	
    public static Entity teleportEntityToDimension(Entity entity, ServerLevel serverLevel, BlockPos pos)
    {
        if(entity.level.dimension() != serverLevel.dimension())
        {
            entity.moveTo(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, entity.getYRot(), 0.0F);
        }
        
        if(entity instanceof ServerPlayer serverPlayer) 
        {
        	serverPlayer.teleportTo(serverLevel, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, entity.getYRot(), entity.getXRot());
            return serverPlayer;
        }

        entity.unRide();
        entity.changeDimension(serverLevel);
        Entity teleportedEntity = entity.getType().create(serverLevel);
        if(teleportedEntity == null)
        {
        	return entity;
        }
        teleportedEntity.restoreFrom(entity);
        teleportedEntity.moveTo(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, entity.getYRot(), entity.getXRot());
        teleportedEntity.setYHeadRot(entity.getYRot());
        serverLevel.addDuringTeleport(teleportedEntity);
    	return teleportedEntity;
    }
	
	public static void getClientLevel(Consumer<Level> consumer)
	{
		LogicalSidedProvider.CLIENTWORLD.get(LogicalSide.CLIENT).filter(ClientLevel.class::isInstance).ifPresent(level -> 
		{
			consumer.accept(level);
		});
	}
	
	public static Vec3 getVelocityTowards(Vec3 from, Vec3 to, float speed)
	{
		Vec3 motion = to.subtract(from).normalize();
		return motion.scale(speed);
	}
	
	public static void createRandomSpherePos(RandomSource random, float radius, Consumer<Vec3> consumer)
	{
        float x = random.nextFloat();
        float y = random.nextFloat();
        Vec3 pos = new Vec3(radius, 0.0F, 0.0F);
        pos = pos.yRot(y * 2 * (float) Math.PI);
        pos = pos.xRot(x * 2 * (float) Math.PI);
        consumer.accept(pos);
	}
	
	public static Vec3 getLookPos(Vec2 rotation, Vec3 position, double left, double up, double forwards) 
	{
		Vec2 vec2 = rotation;
		Vec3 vec3 = position;
		float f = Mth.cos((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f1 = Mth.sin((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f2 = Mth.cos(-vec2.x * ((float)Math.PI / 180.0F));
		float f3 = Mth.sin(-vec2.x * ((float)Math.PI / 180.0F));
		float f4 = Mth.cos((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		float f5 = Mth.sin((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		Vec3 vec31 = new Vec3((double)(f * f2), (double)f3, (double)(f1 * f2));
		Vec3 vec32 = new Vec3((double)(f * f4), (double)f5, (double)(f1 * f4));
		Vec3 vec33 = vec31.cross(vec32).scale(-1.0D);
		double d0 = vec31.x * forwards + vec32.x * up + vec33.x * left;
		double d1 = vec31.y * forwards + vec32.y * up + vec33.y * left;
		double d2 = vec31.z * forwards + vec32.z * up + vec33.z * left;
		return new Vec3(vec3.x + d0, vec3.y + d1, vec3.z + d2);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid)
	{
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) GET_ENTITY.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
    public static double getMeleeAttackRangeSqr(float width, LivingEntity target, float multiplier)
    {
    	return (double)(width * multiplier * width * multiplier + target.getBbWidth());
    }
    
    public static double getMeleeAttackRangeSqr(Entity owner, LivingEntity target, float multiplier)
    {
    	return (double)(owner.getBbWidth() * multiplier * owner.getBbWidth() * multiplier + target.getBbWidth());
    }
    
    public static boolean isWithinMeleeAttackRange(Vec3 pos, float width, LivingEntity target, float multiplier)
    {
    	double d0 = pos.distanceToSqr(target.getX(), target.getY(), target.getZ());
    	return d0 <= getMeleeAttackRangeSqr(width, target, multiplier);
    }

    public static boolean isWithinMeleeAttackRange(Entity owner, LivingEntity target, float multiplier)
    {
    	double d0 = owner.distanceToSqr(target.getX(), target.getY(), target.getZ());
    	return d0 <= getMeleeAttackRangeSqr(owner, target, multiplier);
    }
}

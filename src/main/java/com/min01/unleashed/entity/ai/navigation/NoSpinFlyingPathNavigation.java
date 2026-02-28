package com.min01.unleashed.entity.ai.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.FlyNodeEvaluator;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;

public class NoSpinFlyingPathNavigation extends FlyingPathNavigation
{
	public static final float EPSILON = 1.0E-8F;
    public float distanceModifier = 0.5F;
    
    public NoSpinFlyingPathNavigation(Mob entity, Level world) 
    {
        super(entity, world);
    }
    
    public NoSpinFlyingPathNavigation(Mob entity, Level world, float distanceModifier) 
    {
        super(entity, world);
        this.distanceModifier = distanceModifier;
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes)
    {
        this.nodeEvaluator = new FlyNodeEvaluator();
        this.nodeEvaluator.setCanPassDoors(true);
        return new PatchedPathFinder(this.nodeEvaluator, maxVisitedNodes);
    }

    @Override
    protected void followThePath() 
    {
        Path path = Objects.requireNonNull(this.path);
        Vec3 entityPos = this.getTempMobPos();
        int pathLength = path.getNodeCount();
        for(int i = path.getNextNodeIndex(); i < path.getNodeCount(); i++)
        {
            if(path.getNode(i).y != Math.floor(entityPos.y)) 
            {
                pathLength = i;
                break;
            }
        }
        Vec3 base = entityPos.add(-this.mob.getBbWidth() * this.distanceModifier, 0.0F, -this.mob.getBbWidth() * this.distanceModifier);
        Vec3 max = base.add(this.mob.getBbWidth(), this.mob.getBbHeight(), this.mob.getBbWidth());
        if(this.tryShortcut(path, new Vec3(this.mob.getX(), this.mob.getY(), this.mob.getZ()), pathLength, base, max)) 
        {
            if(this.isAt(path, this.distanceModifier) || this.atElevationChange(path) && this.isAt(path, this.mob.getBbWidth() * this.distanceModifier)) 
            {
                path.setNextNodeIndex(path.getNextNodeIndex() + 1);
            }
        }
        this.doStuckDetection(entityPos);
    }

    private boolean isAt(Path path, float threshold) 
    {
        final Vec3 pathPos = path.getNextEntityPos(this.mob);
        return Mth.abs((float) (this.mob.getX() - pathPos.x)) < threshold && Mth.abs((float) (this.mob.getZ() - pathPos.z)) < threshold;
    }

    private boolean atElevationChange(Path path) 
    {
        int curr = path.getNextNodeIndex();
        int end = Math.min(path.getNodeCount(), curr + Mth.ceil(this.mob.getBbWidth() * this.distanceModifier) + 1);
        int currY = path.getNode(curr).y;
        for(int i = curr + 1; i < end; i++) 
        {
            if(path.getNode(i).y != currY) 
            {
                return true;
            }
        }
        return false;
    }

    private boolean tryShortcut(Path path, Vec3 entityPos, int pathLength, Vec3 base, Vec3 max) 
    {
        for(int i = pathLength; --i > path.getNextNodeIndex();)
        {
            Vec3 vec = path.getEntityPosAtNode(this.mob, i).subtract(entityPos);
            if(this.sweep(vec, base, max))
            {
                path.setNextNodeIndex(i);
                return false;
            }
        }
        return true;
    }

    // Based off of https://github.com/andyhall/voxel-aabb-sweep/blob/d3ef85b19c10e4c9d2395c186f9661b052c50dc7/index.js
    private boolean sweep(Vec3 vec3, Vec3 base, Vec3 max)
    {
        float t = 0.0F;
        float max_t = (float) vec3.length();
        if(max_t < EPSILON) 
        {
        	return true;
        }
        float[] tr = new float[3];
        int[] ldi = new int[3];
        int[] tri = new int[3];
        int[] step = new int[3];
        float[] tDelta = new float[3];
        float[] tNext = new float[3];
        float[] normed = new float[3];
        for(int i = 0; i < 3; i++) 
        {
            float value = element(vec3, i);
            boolean dir = value >= 0.0F;
            step[i] = dir ? 1 : -1;
            float lead = element(dir ? max : base, i);
            tr[i] = element(dir ? base : max, i);
            ldi[i] = leadEdgeToInt(lead, step[i]);
            tri[i] = trailEdgeToInt(tr[i], step[i]);
            normed[i] = value / max_t;
            tDelta[i] = Mth.abs(max_t / value);
            float dist = dir ? (ldi[i] + 1 - lead) : (lead - ldi[i]);
            tNext[i] = tDelta[i] < Float.POSITIVE_INFINITY ? tDelta[i] * dist : Float.POSITIVE_INFINITY;
        }
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        do 
        {
            // stepForward
            int axis = (tNext[0] < tNext[1]) ? ((tNext[0] < tNext[2]) ? 0 : 2) : ((tNext[1] < tNext[2]) ? 1 : 2);
            float dt = tNext[axis] - t;
            t = tNext[axis];
            ldi[axis] += step[axis];
            tNext[axis] += tDelta[axis];
            for(int i = 0; i < 3; i++)
            {
                tr[i] += dt * normed[i];
                tri[i] = trailEdgeToInt(tr[i], step[i]);
            }
            // checkCollision
            int stepx = step[0];
            int x0 = (axis == 0) ? ldi[0] : tri[0];
            int x1 = ldi[0] + stepx;
            int stepy = step[1];
            int y0 = (axis == 1) ? ldi[1] : tri[1];
            int y1 = ldi[1] + stepy;
            int stepz = step[2];
            int z0 = (axis == 2) ? ldi[2] : tri[2];
            int z1 = ldi[2] + stepz;
            for(int x = x0; x != x1; x += stepx) 
            {
                for(int z = z0; z != z1; z += stepz)
                {
                    for(int y = y0; y != y1; y += stepy) 
                    {
                        BlockState block = this.level.getBlockState(pos.set(x, y, z));
                        if(!block.isPathfindable(this.level, pos, PathComputationType.AIR))
                        {
                        	return false;
                        }
                    }
                    BlockPathTypes below = this.nodeEvaluator.getBlockPathType(this.level, x, y0 - 1, z);
                    BlockPathTypes in = this.nodeEvaluator.getBlockPathType(this.level, x, y0, z, this.mob);
                    float priority = this.mob.getPathfindingMalus(in);
                    if(priority < 0.0F || priority >= 8.0F)
                    {
                    	return false;
                    }
                    if(!this.mob.getType().fireImmune())
                    {
                        if(in == BlockPathTypes.DAMAGE_FIRE || in == BlockPathTypes.DANGER_FIRE || in == BlockPathTypes.DAMAGE_OTHER || below == BlockPathTypes.LAVA) 
                        {
                        	return false;
                        }
                    }
                }
            }
        } 
        while(t <= max_t);
        return true;
    }

    protected static int leadEdgeToInt(float coord, int step) 
    {
        return Mth.floor(coord - step * EPSILON);
    }

    protected static int trailEdgeToInt(float coord, int step) 
    {
        return Mth.floor(coord + step * EPSILON);
    }

    protected static float element(Vec3 v, int i)
    {
        switch(i) 
        {
            case 0: return (float) v.x;
            case 1: return (float) v.y;
            case 2: return (float) v.z;
            default: return 0.0F;
        }
    }
    
    public static class PatchedPathFinder extends PathFinder
    {
        public PatchedPathFinder(NodeEvaluator processor, int maxVisitedNodes) 
        {
            super(processor, maxVisitedNodes);
        }

        @Nullable
        @Override
        public Path findPath(PathNavigationRegion regionIn, Mob mob, Set<BlockPos> targetPositions, float maxRange, int accuracy, float searchDepthMultiplier)
        {
            Path path = super.findPath(regionIn, mob, targetPositions, maxRange, accuracy, searchDepthMultiplier);
            return path == null ? null : new PatchedPath(path);
        }

        static class PatchedPath extends Path 
        {
            public PatchedPath(Path original)
            {
                super(copyPathPoints(original), original.getTarget(), original.canReach());
            }

            @Override
            public Vec3 getEntityPosAtNode(Entity entity, int index)
            {
                Node point = this.getNode(index);
                double d0 = point.x + Mth.floor(entity.getBbWidth() + 1.0F) * 0.5D;
                double d1 = point.y;
                double d2 = point.z + Mth.floor(entity.getBbWidth() + 1.0F) * 0.5D;
                return new Vec3(d0, d1, d2);
            }

            private static List<Node> copyPathPoints(Path original)
            {
                List<Node> points = new ArrayList<>();
                for(int i = 0; i < original.getNodeCount(); i++)
                {
                    points.add(original.getNode(i));
                }
                return points;
            }
        }
    }
}
package com.min01.unleashed.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.min01.unleashed.world.UnleashedWorlds;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

@Mixin(Player.class)
public class MixinPlayer
{
    @Inject(method = "findRespawnPositionAndUseSpawnBlock", at = @At("HEAD"), cancellable = true)
    private static void findRespawnPositionAndUseSpawnBlock(ServerLevel pServerLevel, BlockPos pSpawnBlockPos, float pPlayerOrientation, boolean pIsRespawnForced, boolean pRespawnAfterWinningTheGame, CallbackInfoReturnable<Optional<Vec3>> cir)
    {
    	if(pServerLevel.dimension() == UnleashedWorlds.CELESTIAL_FIELD)
    	{
    		cir.setReturnValue(Optional.of(new Vec3((double)pSpawnBlockPos.getX() + 0.5D, (double)pSpawnBlockPos.getY() + 0.1D, (double)pSpawnBlockPos.getZ() + 0.5D)));
    	}
    }
}

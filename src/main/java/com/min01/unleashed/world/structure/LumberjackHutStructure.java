package com.min01.unleashed.world.structure;

import java.util.Optional;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.util.UnleashedUtil;
import com.min01.unleashed.world.UnleashedStructures;
import com.mojang.serialization.Codec;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class LumberjackHutStructure extends Structure
{
	public static final Codec<LumberjackHutStructure> CODEC = simpleCodec(LumberjackHutStructure::new);
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(BossesUnleashed.MODID, "lumberjack_hut");
	
	public LumberjackHutStructure(StructureSettings pSettings)
	{
		super(pSettings);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext pContext)
	{
		return onTopOfChunkCenter(pContext, Heightmap.Types.WORLD_SURFACE_WG, builder -> 
		{
			StructureTemplateManager manager = pContext.structureTemplateManager();
			ChunkPos chunkPos = pContext.chunkPos();
			BlockPos blockPos = chunkPos.getWorldPosition();
			RandomSource random = pContext.random();
			Rotation rotation = Util.getRandom(Rotation.values(), random);
			StructureTemplate template = manager.getOrCreate(LOCATION);
			LumberjackHutStructurePiece piece = new LumberjackHutStructurePiece(manager, LOCATION, blockPos);
			UnleashedUtil.moveStructurePiece(pContext, Heightmap.Types.WORLD_SURFACE_WG, piece, template, rotation, Mirror.NONE, t -> 
			{
				piece.move(0, t, 0);
			});
			builder.addPiece(piece);
		});
	}
	
	@Override
	public StructureType<?> type() 
	{
		return UnleashedStructures.LUMBERJACK_HUT.get();
	}
}
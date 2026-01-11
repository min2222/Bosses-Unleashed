package com.min01.unleashed.world.structure;

import com.min01.unleashed.world.UnleashedStructures;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class LumberjackHutStructurePiece extends TemplateStructurePiece 
{
	public static final RandomSource RAND = RandomSource.create();
	public static final StructurePlaceSettings SETTINGS = new StructurePlaceSettings().addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).setRotation(Util.getRandom(Rotation.values(), RAND));

    public LumberjackHutStructurePiece(StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, BlockPos pTemplatePosition) 
    {
    	super(UnleashedStructures.LUMBERJACK_HUT_PIECE.get(), 0, pStructureTemplateManager, pLocation, pLocation.toString(), SETTINGS, pTemplatePosition);
    }

    public LumberjackHutStructurePiece(StructureTemplateManager pStructureTemplateManager, CompoundTag pTag) 
    {
    	super(UnleashedStructures.LUMBERJACK_HUT_PIECE.get(), pTag, pStructureTemplateManager, pPlaceSettingsFactory -> 
    	{
    		return SETTINGS;
    	});
    }

	@Override
	protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox)
	{
		
	}
}

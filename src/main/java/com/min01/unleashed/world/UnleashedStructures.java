package com.min01.unleashed.world;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.world.structure.LumberjackHutStructure;
import com.min01.unleashed.world.structure.LumberjackHutStructurePiece;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedStructures
{
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, BossesUnleashed.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, BossesUnleashed.MODID);

    public static final RegistryObject<StructureType<LumberjackHutStructure>> LUMBERJACK_HUT = STRUCTURE_TYPES.register("lumberjack_hut", () -> () -> LumberjackHutStructure.CODEC);

    public static final RegistryObject<StructurePieceType.StructureTemplateType> LUMBERJACK_HUT_PIECE = STRUCTURE_PIECE_TYPES.register("lumberjack_hut_piece", () -> LumberjackHutStructurePiece::new);
}

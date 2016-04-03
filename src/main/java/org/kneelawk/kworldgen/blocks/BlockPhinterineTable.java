package org.kneelawk.kworldgen.blocks;

import java.util.List;

import org.kneelawk.kworldgen.blocks.variants.PhinterineVariants;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockPhinterineTable extends BlockWEnumVariants {

	public BlockPhinterineTable(Material blockMaterialIn,
			MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockPhinterineTable(Material materialIn) {
		super(materialIn);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public List<EnumVariantInfo> getEnumClasses() {
		return ImmutableList.of(
				new EnumVariantInfo("phinterine", PhinterineVariants.class),
				new EnumVariantInfo("wood", BlockPlanks.EnumType.class));
	}
}

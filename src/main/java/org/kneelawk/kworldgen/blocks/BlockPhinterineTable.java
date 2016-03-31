package org.kneelawk.kworldgen.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPhinterineTable extends KWGBlock {

	public BlockPhinterineTable(Material blockMaterialIn, MapColor blockMapColorIn) {
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

	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	public int getLightOpacity(IBlockState state) {
		return 0;
	}

	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	public boolean isVisuallyOpaque() {
		return false;
	}

	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
}

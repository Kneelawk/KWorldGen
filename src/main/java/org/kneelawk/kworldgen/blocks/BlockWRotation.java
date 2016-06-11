package org.kneelawk.kworldgen.blocks;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWRotation extends KWGBlock {

	public static final String AXIS_FIELD_NAME = "axis";
	public static final PropertyEnum<EnumFacing.Axis> AXIS =
			PropertyEnum.create(AXIS_FIELD_NAME, EnumFacing.Axis.class);

	public BlockWRotation(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockWRotation(Material materialIn) {
		super(materialIn);
	}

	@Override
	public BlockStateContainer getBlockState() {
		return new BlockStateContainer(this, AXIS);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AXIS, getAxisFromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return getMetaFromAxis(state.getValue(AXIS));
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return getDefaultState().withProperty(AXIS, facing.getAxis());
	}

	public static EnumFacing.Axis getAxisFromMeta(int meta) {
		EnumFacing.Axis[] values = EnumFacing.Axis.values();
		return values[meta % values.length];
	}

	public static int getMetaFromAxis(EnumFacing.Axis axis) {
		return axis.ordinal();
	}
}

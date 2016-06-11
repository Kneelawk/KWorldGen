package org.kneelawk.kworldgen.blocks;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class BlockWRotation extends KWGBlock implements IBlockWMeta {

	public static final String AXIS_FIELD_NAME = "axis";
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum
			.create(AXIS_FIELD_NAME, EnumFacing.Axis.class);

	public BlockWRotation(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockWRotation(Material materialIn) {
		super(materialIn);
	}

	@Override
	public Map<String, Integer> getPossibleMetaValues() {
		ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
		for (EnumFacing.Axis axis : EnumFacing.Axis.values()) {
			builder.put(AXIS_FIELD_NAME + "=" + axis.getName(), axis.ordinal());
		}
		return builder.build();
	}

	// TODO more on BlockWRotation class
}

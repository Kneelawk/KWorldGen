package org.kneelawk.kworldgen.blocks;

import java.util.List;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockWRotation extends BlockWVariants {

	public BlockWRotation(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockWRotation(Material materialIn) {
		super(materialIn);
	}

	@Override
	public List<EnumVariantInfo> getEnumClasses() {
		return null;
	}
}

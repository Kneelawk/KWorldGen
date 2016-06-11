package org.kneelawk.kworldgen.blocks;

import java.util.List;

import org.kneelawk.kworldgen.blocks.variants.PhinterineVariants;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockPhinterine extends BlockWVariants {

	public BlockPhinterine(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockPhinterine(Material materialIn) {
		super(materialIn);
	}

	@Override
	public List<EnumVariantInfo> getEnumClasses() {
		return ImmutableList
				.of(new EnumVariantInfo("type", PhinterineVariants.class));
	}

}

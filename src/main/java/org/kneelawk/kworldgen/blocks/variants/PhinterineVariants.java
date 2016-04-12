package org.kneelawk.kworldgen.blocks.variants;

import net.minecraft.util.IStringSerializable;

public enum PhinterineVariants implements IStringSerializable {
	BRICKS, BLOCK, TILES, CHISELED;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}

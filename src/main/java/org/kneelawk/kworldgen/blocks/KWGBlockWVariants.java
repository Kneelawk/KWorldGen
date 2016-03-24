package org.kneelawk.kworldgen.blocks;

import java.util.HashMap;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import org.kneelawk.kworldgen.blocks.property.StringProperty;

public class KWGBlockWVariants extends KWGBlock {
	
	private StringProperty property;
	private HashMap<String, Integer> metaMap;

	public KWGBlockWVariants(Material blockMaterialIn,
			MapColor blockMapColorIn, String... types) {
		super(blockMaterialIn, blockMapColorIn);
		property = new StringProperty("type", types);
		metaMap = new HashMap<String, Integer>();
		buildMetaMap(types);
	}

	public KWGBlockWVariants(Material materialIn, String... types) {
		super(materialIn);
		property = new StringProperty("type", types);
		metaMap = new HashMap<String, Integer>();
		buildMetaMap(types);
	}
	
	protected void buildMetaMap(String[] types) {
		for (int i = 0; i < types.length; i++) {
			metaMap.put(types[i], i);
		}
	}
}

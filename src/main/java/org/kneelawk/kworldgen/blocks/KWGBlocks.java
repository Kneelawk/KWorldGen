package org.kneelawk.kworldgen.blocks;

import java.util.HashMap;

import org.kneelawk.kworldgen.config.KWGConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KWGBlocks {
	private static HashMap<String, Block> blocks = new HashMap<String, Block>();

	public static void init() {
		add(new KWGBlock(Material.rock), "blockPhinterine", CreativeTabs.tabBlock);
	}

	public static Block add(Block block, String name, CreativeTabs tab) {
		if (!KWGConfig.isBlockEnabled(name))
			return block;
		block.setUnlocalizedName(name);
		block.setCreativeTab(tab);
		// TODO block textures
		// Block textures are really different now
		GameRegistry.registerBlock(block, name);
		blocks.put(name, block);
		return block;
	}
}

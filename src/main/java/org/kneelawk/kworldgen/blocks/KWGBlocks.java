package org.kneelawk.kworldgen.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.kneelawk.kworldgen.blocks.items.ItemBlockWMeta;
import org.kneelawk.kworldgen.config.KWGConfig;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class KWGBlocks {
	private static HashMap<String, Block> blocks = new HashMap<String, Block>();
	private static HashBasedTable<String, String, Integer> metaMaps = HashBasedTable
			.create();

	public static void init() {
		add(new BlockPhinterine(Material.rock), "blockPhinterine",
				ItemBlockWMeta.class, CreativeTabs.tabBlock);
		add(new BlockPhinterineTable(Material.rock), "blockPhinterineTable",
				ItemBlockWMeta.class, CreativeTabs.tabDecorations);
	}

	public static Block add(Block block, String name, CreativeTabs tab) {
		if (!KWGConfig.isBlockEnabled(name))
			return block;
		block.setUnlocalizedName(name);
		block.setCreativeTab(tab);
		if (block instanceof IBlockWMeta) {
			addMapToTable(metaMaps, name,
					((IBlockWMeta) block).getPossibleMetaValues());
		} else {
			metaMaps.put(name, "normal", 0);
		}
		GameRegistry.registerBlock(block, name);
		blocks.put(name, block);
		return block;
	}

	public static Block add(Block block, String name,
			Class<? extends ItemBlock> itemClass, CreativeTabs tab) {
		if (!KWGConfig.isBlockEnabled(name))
			return block;
		block.setUnlocalizedName(name);
		block.setCreativeTab(tab);
		if (block instanceof IBlockWMeta) {
			addMapToTable(metaMaps, name,
					((IBlockWMeta) block).getPossibleMetaValues());
		} else {
			metaMaps.put(name, "normal", 0);
		}
		GameRegistry.registerBlock(block, itemClass, name);
		blocks.put(name, block);
		return block;
	}

	private static <R, C, V> void addMapToTable(Table<R, C, V> table, R row,
			Map<C, V> map) {
		Set<Entry<C, V>> set = map.entrySet();
		for (Entry<C, V> entry : set) {
			table.put(row, entry.getKey(), entry.getValue());
		}
	}

	public static Block getBlock(String name) {
		return blocks.get(name);
	}

	public static HashMap<String, Block> getBlockList() {
		return blocks;
	}

	public static Map<String, Integer> getMetas(String name) {
		return metaMaps.row(name);
	}
}

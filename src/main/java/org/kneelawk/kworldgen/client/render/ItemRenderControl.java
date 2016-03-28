package org.kneelawk.kworldgen.client.render;

import java.util.Map.Entry;
import java.util.Set;

import org.kneelawk.kworldgen.blocks.KWGBlocks;
import org.kneelawk.kworldgen.log.KWGLog;
import org.kneelawk.kworldgen.ref.ModRef;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRenderControl {
	public static void registerItems() {

	}

	public static void registerBlocks() {
		KWGLog.info("Registering block item renders...");
		Set<Entry<String, Block>> blocks = KWGBlocks.getBlockList().entrySet();
		for (Entry<String, Block> entry : blocks) {
			KWGLog.info("Registering block item renders for: " + entry.getKey());
			Set<Entry<String, Integer>> metaValues = KWGBlocks.getMetas(
					entry.getKey()).entrySet();
			KWGLog.info("Block meta map: " + KWGBlocks.getMetas(entry.getKey()));
			for (Entry<String, Integer> e : metaValues) {
				registerBlock(entry.getValue(), e.getValue(), entry.getKey(),
						e.getKey());
				KWGLog.info("Registering: " + entry.getKey() + ", slot: "
						+ e.getKey() + ", meta: " + e.getValue());
			}
		}
	}

	public static void registerItem(Item item, int meta, String name,
			String slot) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(ModRef.MOD_ID + ":" + name, slot));
	}

	public static void registerItem(Item item, String name, String slot) {
		registerItem(item, 0, name, slot);
	}

	public static void registerBlock(Block block, int meta, String name,
			String slot) {
		registerItem(Item.getItemFromBlock(block), meta, name, slot);
	}

	public static void registerBlock(Block block, int meta, String name) {
		registerBlock(block, meta, name, "normal");
	}

	public static void registerBlock(Block block, String name) {
		registerBlock(block, 0, name, "normal");
	}
}

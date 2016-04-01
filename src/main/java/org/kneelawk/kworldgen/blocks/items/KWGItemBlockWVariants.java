package org.kneelawk.kworldgen.blocks.items;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.kneelawk.kworldgen.blocks.BlockWVariants;

public class KWGItemBlockWVariants extends ItemBlock {

	protected HashMap<Integer, String> typeMap;

	public KWGItemBlockWVariants(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		if (!(block instanceof BlockWVariants))
			throw new IllegalArgumentException(
					"KWGItemBlockWVariants should only be used with KWGBlockWVariants");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()
				+ "."
				+ ((BlockWVariants) block).getNameFromMeta(stack
						.getItemDamage());
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

}
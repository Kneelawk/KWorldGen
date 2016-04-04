package org.kneelawk.kworldgen.blocks.items;

import java.util.List;

import org.kneelawk.kworldgen.blocks.BlockWEnumVariants;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWEnumVariants extends ItemBlock {

	public ItemBlockWEnumVariants(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		if (!(block instanceof BlockWEnumVariants))
			throw new IllegalArgumentException(
					"ItemBlockWEnumVariants should only be used with BlockWEnumVariants");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = ((BlockWEnumVariants) block)
				.getNameFromMeta(stack.getItemDamage());
		if (name == null || "".equals(name))
			return super.getUnlocalizedName(stack);
		return super.getUnlocalizedName(stack) + "." + name;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}

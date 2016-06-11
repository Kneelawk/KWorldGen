package org.kneelawk.kworldgen.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.kneelawk.kworldgen.blocks.IBlockWMeta;

public class ItemBlockWMeta extends ItemBlock {

	public ItemBlockWMeta(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		if (!(block instanceof IBlockWMeta))
			throw new IllegalArgumentException(
					"ItemBlockWEnumVariants should only be used with BlockWEnumVariants");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = ((IBlockWMeta) block).getNameFromMeta(stack
				.getItemDamage());
		if (name == null || "".equals(name))
			return super.getUnlocalizedName(stack);
		return super.getUnlocalizedName(stack) + "." + name;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}

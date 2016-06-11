package org.kneelawk.kworldgen.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.kneelawk.kworldgen.blocks.IBlockWMetaName;

public class ItemBlockWMeta extends ItemBlock {

	public ItemBlockWMeta(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (!(block instanceof IBlockWMetaName))
			return super.getUnlocalizedName(stack);
		String name = ((IBlockWMetaName) block).getNameFromMeta(stack
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

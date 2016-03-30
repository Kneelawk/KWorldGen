package org.kneelawk.kworldgen.blocks;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.kneelawk.kworldgen.blocks.property.StringProperty;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;

public class KWGBlockWVariants extends KWGBlock implements IBlockWMeta {

	public static final String TYPE_PROPERTY_NAME = "type";

	public static StringProperty tmp_typeProperty;

	protected StringProperty property;
	protected HashBiMap<String, Integer> metaMap;

	public KWGBlockWVariants(Material blockMaterialIn,
			MapColor blockMapColorIn, String... types) {
		super(blockMaterialIn, blockMapColorIn);
		property = tmp_typeProperty;
		metaMap = HashBiMap.create();
		buildMetaMap(types);
	}

	public KWGBlockWVariants(Material materialIn, String... types) {
		super(materialIn);
		property = tmp_typeProperty;
		metaMap = HashBiMap.create();
		buildMetaMap(types);
	}

	protected void buildMetaMap(String[] types) {
		for (int i = 0; i < types.length; i++) {
			metaMap.put(types[i], i);
		}
	}

	@Override
	public Map<String, Integer> getPossibleMetaValues() {
		// Used to turn any property data this block is able to have into a map
		// of metadata values
		ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
		Set<Entry<String, Integer>> set = metaMap.entrySet();
		for (Entry<String, Integer> entry : set) {
			builder.put(TYPE_PROPERTY_NAME + "=" + entry.getKey(),
					entry.getValue());
		}
		return builder.build();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(property,
				metaMap.inverse().get(Integer.valueOf(meta)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return metaMap.get(state.getValue(property));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return metaMap.get(state.getValue(property));
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return getDefaultState().withProperty(property,
				metaMap.inverse().get(Integer.valueOf(meta)));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		Set<Integer> metas = metaMap.values();
		for (Integer meta : metas) {
			list.add(new ItemStack(itemIn, 1, meta));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (property == null)
			return new BlockStateContainer(this, tmp_typeProperty);
		return new BlockStateContainer(this, property);
	}

	public String getNameFromMeta(int meta) {
		return metaMap.inverse().get(Integer.valueOf(meta));
	}
}

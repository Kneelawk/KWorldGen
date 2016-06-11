package org.kneelawk.kworldgen.blocks;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kneelawk.kworldgen.log.KWGLog;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockWVariants extends KWGBlock
		implements IBlockWMeta {
	public static final String PROPERTY_AFFILIATOR = "#";
	public static final String PROPERTY_SEPERATOR = ".";

	/**
	 * Properties are arranged from most significant property to least
	 * significant property, starting with the most significant property. The
	 * most significant property effects the highest portion of the metadata,
	 * while the least significant property effects the lowest portion of the
	 * metadata. The size of each portion is determined by the number of enum
	 * values each property can have.
	 */
	protected List<IProperty> properties;
	protected List<BiMap<Integer, Enum>> ordinalsList;
	protected BiMap<String, Integer> possibleMeta;
	protected int maxMeta;

	public BlockWVariants(Material blockMaterialIn,
			MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	public BlockWVariants(Material materialIn) {
		super(materialIn);
	}

	/**
	 * Gets a list of the enum classes this block should account for. This
	 * method is called before initialization.
	 * 
	 * @return
	 */
	public abstract List<EnumVariantInfo> getEnumClasses();

	@Override
	protected BlockStateContainer createBlockState() {
		assurePropertyInitialization();
		return new BlockStateContainer(this,
				properties.toArray(new IProperty[properties.size()]));
	}

	protected void assurePropertyInitialization() {
		if (properties == null) {
			// Initialize builders.
			ImmutableList.Builder<IProperty> propertiesBuilder =
					ImmutableList.builder();
			ImmutableList.Builder<BiMap<Integer, Enum>> ordinalsListBuilder =
					ImmutableList.builder();
			ImmutableBiMap.Builder<String, Integer> possibleMetaBuilder =
					ImmutableBiMap.builder();

			// Assign values to the contents of properties and ordinalsList.
			List<EnumVariantInfo> classList = getEnumClasses();
			maxMeta = classList.size() == 0 ? 0 : 1;
			for (EnumVariantInfo info : classList) {
				PropertyEnum pe = PropertyEnum.create(info.getName(),
						info.getEnumClass());
				propertiesBuilder.add(pe);
				Collection vals = pe.getAllowedValues();
				maxMeta *= vals.size();
				ImmutableBiMap.Builder<Integer, Enum> ordinals =
						ImmutableBiMap.builder();
				for (Object o : vals) {
					Enum e = (Enum) o;
					ordinals.put(e.ordinal(), e);
				}
				ordinalsListBuilder.add(ordinals.build());
			}
			properties = propertiesBuilder.build();
			ordinalsList = ordinalsListBuilder.build();

			// Assign values to the contents of possibleMeta here, because
			// initialization of possibleMeta requires the other lists and maps
			// be initialized first.
			for (int meta = 0; meta < maxMeta; meta++) {
				List<Enum> enums = getEnumsFromMeta(meta);
				String key = "";
				for (int i = 0; i < enums.size(); i++) {
					if (i > 0)
						key += ",";
					key += properties.get(i).getName() + "="
							+ ((IStringSerializable) enums.get(i)).getName();
				}
				possibleMetaBuilder.put(key, meta);
			}
			possibleMeta = possibleMetaBuilder.build();
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		int max = properties.size() - 1;
		int metaCpy = meta;
		for (int i = max; i >= 0; i--) {
			int ord = metaCpy % ordinalsList.get(i).size();
			metaCpy /= ordinalsList.get(i).size();
			state = state.withProperty(properties.get(i),
					ordinalsList.get(i).get(ord));
		}
		if (metaCpy != 0)
			KWGLog.warn(getUnlocalizedName() + ": Invalid item meta: " + meta);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		for (int i = 0; i < properties.size(); i++) {
			if (i > 0) {
				meta *= ordinalsList.get(i).size();
			}
			meta += ((Enum) state.getValue(properties.get(i))).ordinal();
		}
		return meta;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public Map<String, Integer> getPossibleMetaValues() {
		return possibleMeta;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return getStateFromMeta(meta);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab,
			List<ItemStack> list) {
		if (properties.size() == 0) {
			super.getSubBlocks(itemIn, tab, list);
			return;
		}
		for (int i = 0; i < maxMeta; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	public int getMetaFromEnums(List<Enum> enums) {
		int meta = 0;
		if (enums.size() != properties.size())
			return -1;
		for (int i = 0; i < properties.size(); i++) {
			if (!properties.get(i).getValueClass()
					.isAssignableFrom(enums.get(i).getClass())) {
				return -1;
			}
			if (i > 0) {
				meta *= ordinalsList.get(i).size();
			}
			meta += enums.get(i).ordinal();
		}
		return meta;
	}

	public List<Enum> getEnumsFromMeta(int meta) {
		// used instead of ImmutableList.Builder because of the Lists's
		// add(index, value) function.
		List<Enum> builder = Lists.newArrayList();
		int max = properties.size() - 1;
		int metaCpy = meta;
		for (int i = max; i >= 0; i--) {
			int ord = metaCpy % ordinalsList.get(i).size();
			metaCpy /= ordinalsList.get(i).size();
			// insert at the start of the list to keep elements in the right
			// order
			builder.add(0, ordinalsList.get(i).get(ord));
		}
		if (metaCpy != 0)
			KWGLog.warn(getUnlocalizedName() + ": Invalid item meta: " + meta);
		return ImmutableList.copyOf(builder);
	}

	public String getNameFromMeta(int meta) {
		List<Enum> enums = getEnumsFromMeta(meta);
		String name = "";
		for (int i = 0; i < enums.size(); i++) {
			if (i > 0)
				name += PROPERTY_SEPERATOR;
			name += properties.get(i).getName() + PROPERTY_AFFILIATOR
					+ ((IStringSerializable) enums.get(i)).getName();
		}
		return name;
	}
}

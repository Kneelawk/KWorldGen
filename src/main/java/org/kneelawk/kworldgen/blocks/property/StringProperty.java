package org.kneelawk.kworldgen.blocks.property;

import java.util.Arrays;
import java.util.Collection;

import net.minecraft.block.properties.IProperty;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

public class StringProperty implements IProperty<String> {

	private String name;
	private ImmutableSet values;

	public StringProperty(String name, Collection<String> values) {
		this.name = name;
		this.values = ImmutableSet.copyOf(values);
	}

	public StringProperty(String name, String... values) {
		this(name, Lists.newArrayList(values));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<String> getAllowedValues() {
		return values;
	}

	@Override
	public Class<String> getValueClass() {
		return String.class;
	}

	@Override
	public Optional<String> parseValue(String value) {
		return values.contains(value) ? Optional.of(value) : Optional
				.<String> absent();
	}

	@Override
	public String getName(String value) {
		return value;
	}

}

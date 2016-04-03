package org.kneelawk.kworldgen.blocks;

public class EnumVariantInfo {
	private String name;
	private Class enumClass;

	public EnumVariantInfo(String name, Class enumClass) {
		this.name = name;
		this.enumClass = enumClass;
	}

	public String getName() {
		return name;
	}

	public Class getEnumClass() {
		return enumClass;
	}
}

package org.kneelawk.kworldgen.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class KWGConfig {
	private static Configuration cfg;

	public static void init(File file) {
		cfg = new Configuration(file);
	}

	public static boolean isBlockEnabled(String name) {
		return cfg.getBoolean(name, "blocks", true, "Is " + name + " enabled?");
	}

	public static void save() {
		cfg.save();
	}
}

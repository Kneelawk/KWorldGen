package org.kneelawk.kworldgen.proxy;

import org.kneelawk.kworldgen.blocks.KWGBlocks;
import org.kneelawk.kworldgen.config.KWGConfig;
import org.kneelawk.kworldgen.log.KWGLog;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		KWGLog.init(event.getModLog());
		KWGConfig.init(event.getSuggestedConfigurationFile());
		KWGBlocks.init();
		KWGConfig.save();
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}

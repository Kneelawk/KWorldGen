package org.kneelawk.kworldgen;

import org.kneelawk.kworldgen.proxy.CommonProxy;
import org.kneelawk.kworldgen.ref.CPRef;
import org.kneelawk.kworldgen.ref.ModRef;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModRef.MOD_ID)
public class KWorldGen {
	@Instance
	private static KWorldGen instance;

	@SidedProxy(clientSide = CPRef.CLIENT_PROXY, serverSide = CPRef.SERVER_PROXY)
	private static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static KWorldGen getInstance() {
		return instance;
	}

	public static CommonProxy getProxy() {
		return proxy;
	}
}

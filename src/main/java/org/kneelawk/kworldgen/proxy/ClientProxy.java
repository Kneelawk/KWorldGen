package org.kneelawk.kworldgen.proxy;

import org.kneelawk.kworldgen.client.render.BlockRenderControl;
import org.kneelawk.kworldgen.client.render.ItemRenderControl;
import org.kneelawk.kworldgen.log.KWGLog;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemRenders() {
		ItemRenderControl.registerItems();
		ItemRenderControl.registerBlocks();
		BlockRenderControl.registerBlocks();
	}
}

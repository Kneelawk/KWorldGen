package org.kneelawk.kworldgen.client.render;

import org.kneelawk.kworldgen.client.render.model.EmptyStateMapper;
import org.kneelawk.kworldgen.client.render.model.SimpleStateMapper;
import org.kneelawk.kworldgen.ref.ModRef;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BlockRenderControl {
	public static void registerBlocks() {
		// only used for registering special renders
	}

	public static void registerEmptyRender(Block block) {
		ModelLoader.setCustomStateMapper(block, new EmptyStateMapper());
	}

	public static void registerSimpleRender(Block block, String name, String slot) {
		ModelLoader.setCustomStateMapper(block,
				new SimpleStateMapper(new ModelResourceLocation(ModRef.MOD_ID + ":" + name, slot)));
	}
}

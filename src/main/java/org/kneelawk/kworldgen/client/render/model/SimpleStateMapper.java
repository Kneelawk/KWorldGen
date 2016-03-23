package org.kneelawk.kworldgen.client.render.model;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class SimpleStateMapper extends StateMapperBase {
	
	private ModelResourceLocation loc;
	
	public SimpleStateMapper(ModelResourceLocation loc) {
		this.loc = loc;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		return loc;
	}

}

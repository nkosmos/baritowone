/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils;

import javax.annotation.Nullable;

import baritone.api.utils.BetterBlockPos;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Brady
 * @since 11/5/2019
 */
@SuppressWarnings("NullableProblems")
public final class BlockStateInterfaceAccessWrapper implements IBlockAccess {

    private final BlockStateInterface bsi;

    BlockStateInterfaceAccessWrapper(BlockStateInterface bsi) {
        this.bsi = bsi;
    }

	@Override
	public Block getBlock(int p_147439_1_, int p_147439_2_, int p_147439_3_) {
		return bsi.get0(p_147439_1_, p_147439_2_, p_147439_3_).getBlock();
	}

	@Override
	public TileEntity getTileEntity(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLightBrightnessForSkyBlocks(int p_72802_1_, int p_72802_2_, int p_72802_3_, int p_72802_4_) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockMetadata(int p_72805_1_, int p_72805_2_, int p_72805_3_) {
		return XBlockStateSerializer.getMetaFromState(bsi.get0(p_72805_1_, p_72805_2_, p_72805_3_));
	}

	@Override
	public int isBlockProvidingPowerTo(int x, int y, int z, int directionIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAirBlock(int x, int y, int z) {
		return getBlock(x, y, z).getMaterial() == Material.air;
	}

	@Override
	public BiomeGenBase getBiomeGenForCoords(int x, int z) {
		return BiomeGenForest.forest;
	}

	@Override
	public int getHeight() {
		return 255;
	}

	@Override
	public boolean extendedLevelsInChunkCache() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSideSolid(int x, int y, int z, ForgeDirection side, boolean _default) {
		if(getBlock(x, y, z).isSideSolid(this, x, y, z, side)) {
			return true;
		}
		return _default;
	}

}

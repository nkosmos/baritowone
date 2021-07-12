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
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

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

    @Nullable
    @Override
    public TileEntity getTileEntity(BetterBlockPos pos) {
        return null;
    }

    @Override
    public int getCombinedLight(BetterBlockPos pos, int lightValue) {
        return 0;
    }

    @Override
    public IBlockState getBlockState(BetterBlockPos pos) {
        // BlockStateInterface#get0(BlockPos) btfo!
        return this.bsi.get0(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isAirBlock(BetterBlockPos pos) {
        return this.bsi.get0(pos.getX(), pos.getY(), pos.getZ()).getBlock().getMaterial() == Material.air;
    }

    @Override
    public int getStrongPower(BetterBlockPos pos, EnumFacing direction) {
        return 0;
    }

    @Override
    public WorldType getWorldType() {
        return this.bsi.world.getWorldType();
    }

	@Override
	public boolean isSideSolid(BetterBlockPos pos, EnumFacing side, boolean _default) {
		return getBlockState(pos).getBlock().isSideSolid(this, pos, side);
	}

	@Override
	public boolean extendedLevelsInChunkCache() {
		return false;
	}

	@Override
	public BiomeGenBase getBiomeGenForCoords(BetterBlockPos pos) {
		return BiomeGenForest.forest;
	}
}

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

package baritone.api.utils;

import baritone.api.BaritoneAPI;
import baritonex.utils.math.BlockPos;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author Brady
 * @since 12/14/2018
 */
public interface IPlayerController {

    void syncHeldItem();

    boolean hasBrokenBlock();

    boolean onPlayerDamageBlock(BlockPos pos, int side);

    void resetBlockRemoving();

    ItemStack windowClick(int windowId, int slotId, int mouseButton, int type, EntityPlayer player);

    boolean isInCreative();

    boolean processRightClickBlock(EntityPlayerSP player, World world, BlockPos pos, int direction, Vec3 vec);

    boolean processRightClick(EntityPlayerSP player, World world);

    void clickBlock(BlockPos loc, int facing);

    void setHittingBlock(boolean hittingBlock);

    default double getBlockReachDistance() {
        return this.isInCreative() ? 5.0F : BaritoneAPI.getSettings().blockReachDistance.value;
    }
}

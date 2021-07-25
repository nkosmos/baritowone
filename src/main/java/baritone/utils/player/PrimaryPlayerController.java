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

package baritone.utils.player;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerController;
import baritone.utils.accessor.IPlayerControllerMP;
import baritonex.utils.XHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Implementation of {@link IPlayerController} that chains to the primary player controller's methods
 *
 * @author Brady
 * @since 12/14/2018
 */
public enum PrimaryPlayerController implements IPlayerController, Helper {

    INSTANCE;

    @Override
    public void syncHeldItem() {
        ((IPlayerControllerMP) mc.playerController).callSyncCurrentPlayItem();
    }

    @Override
    public boolean hasBrokenBlock() {
        return ((IPlayerControllerMP) mc.playerController).getCurrentBlock().getY() == -1;
    }

    @Override
    public boolean onPlayerDamageBlock(BetterBlockPos pos, EnumFacing side) {
    	if(mc.theWorld.getBlock(pos.x, pos.y, pos.z).getMaterial() == Material.air) {
    		return false;
    	}
        mc.playerController.onPlayerDamageBlock(pos.x, pos.y, pos.z, XHelper.facingToInt(side));
        return mc.thePlayer.isCurrentToolAdventureModeExempt(pos.x, pos.y, pos.z);
    }

    @Override
    public void resetBlockRemoving() {
        mc.playerController.resetBlockRemoving();
    }

    @Override
    public ItemStack windowClick(int windowId, int slotId, int mouseButton, int type, EntityPlayer player) {
        return mc.playerController.windowClick(windowId, slotId, mouseButton, type, player);
    }

    @Override
    public boolean isCreative() {
    	return mc.playerController.isInCreativeMode();
    }

    @Override
    public boolean processRightClickBlock(EntityPlayerSP player, World world, BetterBlockPos pos, int direction, Vec3 vec) {
        return mc.playerController.onPlayerRightClick(player, (WorldClient) world, player.getHeldItem(), pos.x, pos.y, pos.z, direction, vec);
    }

    @Override
    public boolean processRightClick(EntityPlayerSP player, World world) {
        return mc.playerController.sendUseItem(player, world, player.getHeldItem());
    }

    @Override
    public void clickBlock(BetterBlockPos loc, EnumFacing face) {
        mc.playerController.clickBlock(loc.x, loc.y, loc.z, XHelper.facingToInt(face));
    }

    @Override
    public void setHittingBlock(boolean hittingBlock) {
        ((IPlayerControllerMP) mc.playerController).setIsHittingBlock(hittingBlock);
    }
}

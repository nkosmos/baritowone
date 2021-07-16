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

import baritone.Baritone;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;
import baritonex.utils.XHelper;
import net.minecraft.util.MovingObjectPosition;

public class BlockPlaceHelper implements Helper {

    private final IPlayerContext ctx;
    private int rightClickTimer;

    BlockPlaceHelper(IPlayerContext playerContext) {
        this.ctx = playerContext;
    }

    public void tick(boolean rightClickRequested) {
        if (rightClickTimer > 0) {
            rightClickTimer--;
            return;
        }
        MovingObjectPosition mouseOver = ctx.objectMouseOver();
        if (!rightClickRequested || mouseOver == null || mouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        rightClickTimer = Baritone.settings().rightClickSpeed.value;
        
        if (ctx.playerController().processRightClickBlock(ctx.player(), ctx.world(), BetterBlockPos.from(mouseOver), mouseOver.sideHit, mouseOver.hitVec)) {
            ctx.player().swingItem();
            return;
        }
        if (!XHelper.isEmpty(ctx.player().getHeldItem()) && ctx.playerController().processRightClick(ctx.player(), ctx.world())) {
            return;
        }
    }
}

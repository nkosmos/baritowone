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

package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import baritone.api.utils.BetterBlockPos;
import baritone.utils.accessor.IPlayerControllerMP;
import net.minecraft.client.multiplayer.PlayerControllerMP;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP implements IPlayerControllerMP {

    @Accessor
    @Override
    public abstract void setIsHittingBlock(boolean isHittingBlock);

    @Accessor
    public abstract int getCurrentBlockX();
    @Accessor
    public abstract int getCurrentBlockY();
    @Accessor
    public abstract int getCurrentBlockZ();
    
    @Override
    public BetterBlockPos getCurrentBlock() {
    	return new BetterBlockPos(getCurrentBlockX(), getCurrentBlockY(), getCurrentBlockZ());
    }

    @Invoker
    @Override
    public abstract void callSyncCurrentPlayItem();
}

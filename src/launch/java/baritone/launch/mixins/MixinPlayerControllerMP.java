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

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import baritone.api.utils.BetterBlockPos;
import baritone.utils.accessor.IPlayerControllerMP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP implements IPlayerControllerMP {

	@Shadow
	@Final
	private Minecraft mc;

	@Shadow
	private float curBlockDamageMP;

	@Shadow
	private boolean isHittingBlock;

	@Shadow
	@Final
	private NetHandlerPlayClient netClientHandler;

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

	@Overwrite
	public void resetBlockRemoving() {
		if (this.isHittingBlock) {
			this.netClientHandler.addToSendQueue(
					new C07PacketPlayerDigging(1, getCurrentBlockX(), getCurrentBlockY(), getCurrentBlockZ(), -1));
			this.isHittingBlock = false;
			this.curBlockDamageMP = 0.0F;
			this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.getEntityId(), getCurrentBlockX(),
					getCurrentBlockY(), getCurrentBlockZ(), -1);
		}
	}
}

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

package baritone.cache;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import baritone.api.cache.IContainerMemory;
import baritone.api.cache.IRememberedInventory;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.IPlayerContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerMemory implements IContainerMemory {

    /**
     * The current remembered inventories
     */
    private final Map<BetterBlockPos, RememberedInventory> inventories = new HashMap<>();


    public ContainerMemory(Path saveTo) {

    }

    public synchronized void save() throws IOException {
    }

    public synchronized void setup(BetterBlockPos pos, int windowId, int slotCount) {
        RememberedInventory inventory = inventories.computeIfAbsent(pos, x -> new RememberedInventory());
        inventory.windowId = windowId;
        inventory.size = slotCount;
    }

    public synchronized Optional<RememberedInventory> getInventoryFromWindow(int windowId) {
        return inventories.values().stream().filter(i -> i.windowId == windowId).findFirst();
    }

    @Override
    public final synchronized RememberedInventory getInventoryByPos(BetterBlockPos pos) {
        return inventories.get(pos);
    }

    @Override
    public final synchronized Map<BetterBlockPos, IRememberedInventory> getRememberedInventories() {
        // make a copy since this map is modified from the packet thread
        return new HashMap<>(inventories);
    }

    public static List<ItemStack> readItemStacks(byte[] bytes) throws IOException {
        PacketBuffer in = new PacketBuffer(Unpooled.wrappedBuffer(bytes));
        return readItemStacks(in);
    }

    public static List<ItemStack> readItemStacks(PacketBuffer in) throws IOException {
        int count = in.readInt();
        List<ItemStack> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(in.readItemStackFromBuffer());
        }
        return result;
    }

    public static byte[] writeItemStacks(List<ItemStack> write) {
        ByteBuf buf = Unpooled.buffer(0, Integer.MAX_VALUE);
        PacketBuffer out = new PacketBuffer(buf);
        out = writeItemStacks(write, out);
        return out.array();
    }

    public static PacketBuffer writeItemStacks(List<ItemStack> write, PacketBuffer out2) {
        PacketBuffer out = out2; // avoid reassigning an argument LOL
        out = new PacketBuffer(out.writeInt(write.size()));
        for (ItemStack stack : write) {
            try {
				out.writeItemStackToBuffer(stack);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return out;
    }

    /**
     * An inventory that we are aware of.
     * <p>
     * Associated with a {@link BlockPos} in {@link ContainerMemory#inventories}.
     */
    public static class RememberedInventory implements IRememberedInventory {

        /**
         * The list of items in the inventory
         */
        private final List<ItemStack> items;

        /**
         * The last known window ID of the inventory
         */
        private int windowId;

        /**
         * The size of the inventory
         */
        private int size;

        private RememberedInventory() {
            this.items = new ArrayList<>();
        }

        @Override
        public final List<ItemStack> getContents() {
            return Collections.unmodifiableList(this.items);
        }

        @Override
        public final int getSize() {
            return this.size;
        }

        public void updateFromOpenWindow(IPlayerContext ctx) {
            items.clear();
            items.addAll(ctx.player().openContainer.getInventory().subList(0, size));
        }
    }
}

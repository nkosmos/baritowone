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

package baritone.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;

import baritone.Baritone;
import baritone.api.event.events.TickEvent;
import baritone.utils.ToolSet;
import baritonex.utils.XHelper;
import baritonex.utils.data.XEnumFacing;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public final class InventoryBehavior extends Behavior {

    public InventoryBehavior(Baritone baritone) {
        super(baritone);
    }

    @Override
    public void onTick(TickEvent event) {
        if (!Baritone.settings().allowInventory.value) {
            return;
        }
        if (event.getType() == TickEvent.Type.OUT) {
            return;
        }
        if (ctx.player().openContainer != ctx.player().inventoryContainer) {
            // we have a crafting table or a chest or something open
            return;
        }
        if (firstValidThrowaway() >= 9) { // aka there are none on the hotbar, but there are some in main inventory
            swapWithHotBar(firstValidThrowaway(), 8);
        }
        int pick = bestToolAgainst(Blocks.stone, ItemPickaxe.class);
        if (pick >= 9) {
            swapWithHotBar(pick, 0);
        }
    }

    public void attemptToPutOnHotbar(int inMainInvy, Predicate<Integer> disallowedHotbar) {
        OptionalInt destination = getTempHotbarSlot(disallowedHotbar);
        if (destination.isPresent()) {
            swapWithHotBar(inMainInvy, destination.getAsInt());
        }
    }
    
    public OptionalInt getTempHotbarSlot(Predicate<Integer> disallowedHotbar) {
        // we're using 0 and 8 for pickaxe and throwaway
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (XHelper.isEmpty(ctx.player().inventory.mainInventory[i]) && !disallowedHotbar.test(i)) {
                candidates.add(i);
            }
        }
        if (candidates.isEmpty()) {
            for (int i = 1; i < 8; i++) {
                if (!disallowedHotbar.test(i)) {
                    candidates.add(i);
                }
            }
        }
        if (candidates.isEmpty()) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(candidates.get(new Random().nextInt(candidates.size())));
    }

    private void swapWithHotBar(int inInventory, int inHotbar) {
        ctx.playerController().windowClick(ctx.player().inventoryContainer.windowId, inInventory < 9 ? inInventory + 36 : inInventory, inHotbar, 2, ctx.player());
    }

    private int firstValidThrowaway() { // TODO offhand idk
    	List<ItemStack> invy = Arrays.asList(ctx.player().inventory.mainInventory);
        for (int i = 0; i < invy.size(); i++) {
            if (Baritone.settings().acceptableThrowawayItems.value.contains(invy.get(i).getItem())) {
                return i;
            }
        }
        return -1;
    }

    private int bestToolAgainst(Block against, Class<? extends ItemTool> cla$$) {
    	List<ItemStack> invy = Arrays.asList(ctx.player().inventory.mainInventory);
        int bestInd = -1;
        double bestSpeed = -1;
        for (int i = 0; i < invy.size(); i++) {
            ItemStack stack = invy.get(i);
            if (XHelper.isEmpty(stack)) {
                continue;
            }
            if (Baritone.settings().itemSaver.value && (stack.getMetadata() + Baritone.settings().itemSaverThreshold.value) >= stack.getMaxDurability() && stack.getMaxDurability() > 1) {
                continue;
            }
            if (cla$$.isInstance(stack.getItem())) {
                double speed = ToolSet.calculateSpeedVsBlock(stack, against); // takes into account enchants
                if (speed > bestSpeed) {
                    bestSpeed = speed;
                    bestInd = i;
                }
            }
        }
        return bestInd;
    }

    public boolean hasGenericThrowaway() {
        for (Item item : Baritone.settings().acceptableThrowawayItems.value) {
            if (throwaway(false, stack -> item.equals(stack.getItem()))) {
                return true;
            }
        }
        return false;
    }

    public boolean selectThrowawayForLocation(boolean select, int x, int y, int z) {
    	IBlockState maybe = baritone.getBuilderProcess().placeAt(x, y, z, baritone.bsi.get0(x, y, z));
        if (maybe != null && throwaway(select, stack -> {
        	Block block = ((ItemBlock) stack.getItem()).blockInstance;
        	return stack.getItem() instanceof ItemBlock 
        			&& maybe.equals(
        					XBlockStateSerializer.getStateFromMeta(
        							block, 
        							block.onBlockPlaced(
        									ctx.world(), 
        									ctx.playerFeet().x, 
        									ctx.playerFeet().y, 
        									ctx.playerFeet().z, 
        									XEnumFacing.UP.toSideHit(), 
        									(float) ctx.player().posX, 
        									(float) ctx.player().boundingBox.minY, 
        									(float) ctx.player().posZ, 
        									stack.getItem().getMetadata(stack.getMetadata())
        							)
        					)
        			);
        })) {
            return true; // gotem
        }
        if (maybe != null && throwaway(select, stack -> stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).blockInstance.equals(maybe.getBlock()))) {
            return true;
        }
        for (Item item : Baritone.settings().acceptableThrowawayItems.value) {
            if (throwaway(select, stack -> item.equals(stack.getItem()))) {
                return true;
            }
        }
        return false;
    }

    public boolean throwaway(boolean select, Predicate<? super ItemStack> desired) {
        return throwaway(select, desired, Baritone.settings().allowInventory.value);
    }

    public boolean throwaway(boolean select, Predicate<? super ItemStack> desired, boolean allowInventory) {
        EntityPlayerSP p = ctx.player();
        List<ItemStack> inv = Arrays.asList(p.inventory.mainInventory);
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.get(i);
            if(XHelper.isEmpty(item)) continue;
            // this usage of settings() is okay because it's only called once during pathing
            // (while creating the CalculationContext at the very beginning)
            // and then it's called during execution
            // since this function is never called during cost calculation, we don't need to migrate
            // acceptableThrowawayItems to the CalculationContext
            if (desired.test(item)) {
                if (select) {
                    p.inventory.currentItem = i;
                }
                return true;
            }
        }

        if (allowInventory) {
            for (int i = 9; i < 36; i++) {
                if (desired.test(inv.get(i))) {
                    swapWithHotBar(i, 7);
                    if (select) {
                        p.inventory.currentItem = 7;
                    }
                    return true;
                }
            }
        }

        return false;
    }
}

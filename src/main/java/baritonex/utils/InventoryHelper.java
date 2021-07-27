package baritonex.utils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

	/**
	 * thx minecraft
	 * @param stack1
	 * @param stack2
	 * @return boolean
	 */
	private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }
	
	/**
	 * thx minecraft (slightly modified)
	 * @param stack
	 * @return int
	 */
    public static int getSlotFor(EntityPlayerSP player, ItemStack stack) {
        for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
            if (player.inventory.mainInventory[i] != null && stackEqualExact(stack, player.inventory.mainInventory[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean isHotbar(int index) {
        return index >= 0 && index < 9;
    }
	
}

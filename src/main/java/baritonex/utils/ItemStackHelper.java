package baritonex.utils;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackHelper {
	
	public static final ItemStack EMPTY = new ItemStack((Item)null);
	
	public static boolean isEmpty(ItemStack stack) {
		if(stack == null)
			return true;
		if(stack == EMPTY)
			return true;
		if(stack.getItem() == null)
			return true;
		if(stack.stackSize <= 0)
			return true;
		if(stack.getItemDamage() < -32768 || stack.getItemDamage() > 65535)
			return true;
		if(stack.getItem() == Item.getItemFromBlock(Blocks.air))
			return true;
		return false;
	}

}

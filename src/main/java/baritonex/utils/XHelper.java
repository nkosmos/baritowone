package baritonex.utils;

import baritone.api.utils.accessor.IItemStack;
import net.minecraft.item.ItemStack;

public class XHelper {
	
	public static boolean isEmpty(ItemStack itemStack) {
		return itemStack == null ? true : ((IItemStack)(Object)itemStack).isEmpty();
	}

}

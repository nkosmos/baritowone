package baritonex.utils.registry;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackAccessor;

public class ItemStackRegistry {

	public static Map<ItemStack, ItemStackAccessor> accessors = Maps.newHashMap();
	public static Map<ItemStack, Integer> itemHashes = Maps.newHashMap();
	public static Map<ItemStack, Item> stackItems = Maps.newHashMap();
	
	public static int getHash(ItemStack itemStack) {
		return itemHashes.computeIfAbsent(itemStack, ItemStackRegistry::updateHash);
	}
	
	// on item change
	public static void updateItem(ItemStack itemStack, Item item) {
		stackItems.put(itemStack, item);
		updateHash(itemStack);
	}
	
	// on metadata change
	public static int updateHash(ItemStack itemStack) {
		Item item = stackItems.getOrDefault(itemStack, null);
		ItemStackAccessor accessor = accessors.computeIfAbsent(itemStack, ItemStackAccessor::new);
		return itemHashes.put(itemStack, item == null ? -1 : item.hashCode() + accessor.getMetadata());
	}
	
}

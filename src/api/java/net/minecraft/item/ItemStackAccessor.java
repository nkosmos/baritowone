package net.minecraft.item;

public class ItemStackAccessor {
	
	private final ItemStack itemStack;
	
	public ItemStackAccessor(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	public int getMetadata() {
		return itemStack.metadata;
	}

}

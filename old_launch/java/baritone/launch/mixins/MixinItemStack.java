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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.api.utils.accessor.IItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements IItemStack {

	private static final ItemStack EMPTY = new ItemStack((Item)null);
	
    @Shadow
    private Item item;

    @Shadow
    private int stackSize;
    
    @Shadow
    private int itemDamage;

    @Unique
    private int baritoneHash;

    private void recalculateHash() {
        baritoneHash = item == null ? -1 : item.hashCode() + itemDamage;
    }

    @Inject(
            method = "<init>*",
            at = @At("RETURN")
    )
    private void onInit(CallbackInfo ci) {
        recalculateHash();
    }

    @Inject(
            method = "setItemDamage",
            at = @At("TAIL")
    )
    private void onItemDamageSet(CallbackInfo ci) {
        recalculateHash();
    }

    @Override
    public int getBaritoneHash() {
        return baritoneHash;
    }
    
    @Override
    public boolean isEmpty() {
        return (ItemStack)(Object)this == EMPTY ? true : (item != null && item != Item.getItemFromBlock(Blocks.air) ? (this.stackSize <= 0 ? true : this.itemDamage < -32768 || this.itemDamage > 65535) : true);
    }
}
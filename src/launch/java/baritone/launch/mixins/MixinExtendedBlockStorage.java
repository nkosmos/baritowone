package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.utils.accessor.IExtendedBlockStorage;
import baritonex.reoptimize.BlockStateContainer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.property.IExtendedBlockState;

@Mixin(ExtendedBlockStorage.class)
public class MixinExtendedBlockStorage implements IExtendedBlockStorage {
	
	@Unique
	private BlockStateContainer bsc;
	
	@Shadow
	private int blockRefCount;
	
	@Shadow
    private int tickRefCount;
	
	@Inject(
            method = "<init>*",
            at = @At("RETURN")
    )
    private void onInit(CallbackInfo ci) {
        bsc = new BlockStateContainer();
    }

	@Overwrite
	public IBlockState get(int x, int y, int z) {
		return this.bsc.get(x, y, z);
	}
	
	@Overwrite
	public void set(int x, int y, int z, IBlockState state) {
        if (state instanceof IExtendedBlockState) {
            state = ((IExtendedBlockState)state).getClean();
        }
        IBlockState iblockstate = this.get(x, y, z);
        Block block = iblockstate.getBlock();
        Block block1 = state.getBlock();
        if (block != Blocks.air) {
            --this.blockRefCount;
            if (block.getTickRandomly()) {
                --this.tickRefCount;
            }
        }
        if (block1 != Blocks.air) {
            ++this.blockRefCount;
            if (block1.getTickRandomly()) {
                ++this.tickRefCount;
            }
        }
        this.bsc.set(x, y, z, state);
    }


	@Override
	public BlockStateContainer getBSCData() {
		return bsc;
	}

}

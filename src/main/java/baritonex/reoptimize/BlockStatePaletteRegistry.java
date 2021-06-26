package baritonex.reoptimize;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteRegistry
implements IBlockStatePalette {
    @Override
    public int idFor(IBlockState p_idFor_1_) {
        int lvt_2_1_ = Block.BLOCK_STATE_IDS.get(p_idFor_1_);
        return lvt_2_1_ == -1 ? 0 : lvt_2_1_;
    }

    @Override
    public IBlockState getBlockState(int p_getBlockState_1_) {
        IBlockState lvt_2_1_ = Block.BLOCK_STATE_IDS.getByValue(p_getBlockState_1_);
        return lvt_2_1_ == null ? Blocks.air.getDefaultState() : lvt_2_1_;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void read(PacketBuffer p_read_1_) {
        p_read_1_.readVarIntFromBuffer();
    }

    @Override
    public void write(PacketBuffer p_write_1_) {
        p_write_1_.writeVarIntToBuffer(0);
    }

    @Override
    public int getSerializedState() {
        return PacketBuffer.getVarIntSize(0);
    }
}

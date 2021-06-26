package baritonex.reoptimize;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteLinear
implements IBlockStatePalette {
    private final IBlockState[] states;
    private final IBlockStatePaletteResizer resizeHandler;
    private final int bits;
    private int arraySize;

    public BlockStatePaletteLinear(int p_i47088_1_, IBlockStatePaletteResizer p_i47088_2_) {
        this.states = new IBlockState[1 << p_i47088_1_];
        this.bits = p_i47088_1_;
        this.resizeHandler = p_i47088_2_;
    }

    @Override
    public int idFor(IBlockState p_idFor_1_) {
        int lvt_2_2_;
        for (int lvt_2_1_ = 0; lvt_2_1_ < this.arraySize; ++lvt_2_1_) {
            if (this.states[lvt_2_1_] != p_idFor_1_) continue;
            return lvt_2_1_;
        }
        if ((lvt_2_2_ = this.arraySize++) < this.states.length) {
            this.states[lvt_2_2_] = p_idFor_1_;
            return lvt_2_2_;
        }
        return this.resizeHandler.onResize(this.bits + 1, p_idFor_1_);
    }

    @Override
    @Nullable
    public IBlockState getBlockState(int p_getBlockState_1_) {
        if (p_getBlockState_1_ >= 0 && p_getBlockState_1_ < this.arraySize) {
            return this.states[p_getBlockState_1_];
        }
        return null;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void read(PacketBuffer p_read_1_) {
        this.arraySize = p_read_1_.readVarIntFromBuffer();
        for (int lvt_2_1_ = 0; lvt_2_1_ < this.arraySize; ++lvt_2_1_) {
            this.states[lvt_2_1_] = Block.BLOCK_STATE_IDS.getByValue(p_read_1_.readVarIntFromBuffer());
        }
    }

    @Override
    public void write(PacketBuffer p_write_1_) {
        p_write_1_.writeVarIntToBuffer(this.arraySize);
        for (int lvt_2_1_ = 0; lvt_2_1_ < this.arraySize; ++lvt_2_1_) {
            p_write_1_.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.states[lvt_2_1_]));
        }
    }

    @Override
    public int getSerializedState() {
        int lvt_1_1_ = PacketBuffer.getVarIntSize(this.arraySize);
        for (int lvt_2_1_ = 0; lvt_2_1_ < this.arraySize; ++lvt_2_1_) {
            lvt_1_1_ += PacketBuffer.getVarIntSize(Block.BLOCK_STATE_IDS.get(this.states[lvt_2_1_]));
        }
        return lvt_1_1_;
    }
}
package baritonex.reoptimize;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteHashMap
implements IBlockStatePalette {
    private final IntIdentityHashBiMap<IBlockState> statePaletteMap;
    private final IBlockStatePaletteResizer paletteResizer;
    private final int bits;

    public BlockStatePaletteHashMap(int p_i47089_1_, IBlockStatePaletteResizer p_i47089_2_) {
        this.bits = p_i47089_1_;
        this.paletteResizer = p_i47089_2_;
        this.statePaletteMap = new IntIdentityHashBiMap(1 << p_i47089_1_);
    }

    @Override
    public int idFor(IBlockState p_idFor_1_) {
        int lvt_2_1_ = this.statePaletteMap.getId(p_idFor_1_);
        if (lvt_2_1_ == -1 && (lvt_2_1_ = this.statePaletteMap.add(p_idFor_1_)) >= 1 << this.bits) {
            lvt_2_1_ = this.paletteResizer.onResize(this.bits + 1, p_idFor_1_);
        }
        return lvt_2_1_;
    }

    @Override
    @Nullable
    public IBlockState getBlockState(int p_getBlockState_1_) {
        return this.statePaletteMap.get(p_getBlockState_1_);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void read(PacketBuffer p_read_1_) {
        this.statePaletteMap.clear();
        int lvt_2_1_ = p_read_1_.readVarIntFromBuffer();
        for (int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
            this.statePaletteMap.add(Block.BLOCK_STATE_IDS.getByValue(p_read_1_.readVarIntFromBuffer()));
        }
    }

    @Override
    public void write(PacketBuffer p_write_1_) {
        int lvt_2_1_ = this.statePaletteMap.size();
        p_write_1_.writeVarIntToBuffer(lvt_2_1_);
        for (int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
            p_write_1_.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.statePaletteMap.get(lvt_3_1_)));
        }
    }

    @Override
    public int getSerializedState() {
        int lvt_1_1_ = PacketBuffer.getVarIntSize(this.statePaletteMap.size());
        for (int lvt_2_1_ = 0; lvt_2_1_ < this.statePaletteMap.size(); ++lvt_2_1_) {
            lvt_1_1_ += PacketBuffer.getVarIntSize(Block.BLOCK_STATE_IDS.get(this.statePaletteMap.get(lvt_2_1_)));
        }
        return lvt_1_1_;
    }
}
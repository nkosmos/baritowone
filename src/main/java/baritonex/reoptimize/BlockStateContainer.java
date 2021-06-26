package baritonex.reoptimize;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStateContainer
implements IBlockStatePaletteResizer {
    private static final IBlockStatePalette REGISTRY_BASED_PALETTE = new BlockStatePaletteRegistry();
    protected static final IBlockState AIR_BLOCK_STATE = Blocks.air.getDefaultState();
    protected BitArray storage;
    protected IBlockStatePalette palette;
    private int bits = 0;

    public BlockStateContainer() {
        this.setBits(4);
    }

    private static int getIndex(int p_getIndex_0_, int p_getIndex_1_, int p_getIndex_2_) {
        return p_getIndex_1_ << 8 | p_getIndex_2_ << 4 | p_getIndex_0_;
    }

    private void setBits(int p_setBits_1_) {
        if (p_setBits_1_ == this.bits) {
            return;
        }
        this.bits = p_setBits_1_;
        if (this.bits <= 4) {
            this.bits = 4;
            this.palette = new BlockStatePaletteLinear(this.bits, this);
        } else if (this.bits <= 8) {
            this.palette = new BlockStatePaletteHashMap(this.bits, this);
        } else {
            this.palette = REGISTRY_BASED_PALETTE;
            this.bits = MathHelper.calculateLogBaseTwoDeBruijn(Block.BLOCK_STATE_IDS.objectList.size());
        }
        this.palette.idFor(AIR_BLOCK_STATE);
        this.storage = new BitArray(this.bits, 4096);
    }

    @Override
    public int onResize(int p_onResize_1_, IBlockState p_onResize_2_) {
        BitArray lvt_3_1_ = this.storage;
        IBlockStatePalette lvt_4_1_ = this.palette;
        this.setBits(p_onResize_1_);
        for (int lvt_5_1_ = 0; lvt_5_1_ < lvt_3_1_.size(); ++lvt_5_1_) {
            IBlockState lvt_6_1_ = lvt_4_1_.getBlockState(lvt_3_1_.getAt(lvt_5_1_));
            if (lvt_6_1_ == null) continue;
            this.set(lvt_5_1_, lvt_6_1_);
        }
        return this.palette.idFor(p_onResize_2_);
    }

    public void set(int p_set_1_, int p_set_2_, int p_set_3_, IBlockState p_set_4_) {
        this.set(BlockStateContainer.getIndex(p_set_1_, p_set_2_, p_set_3_), p_set_4_);
    }

    protected void set(int p_set_1_, IBlockState p_set_2_) {
        int lvt_3_1_ = this.palette.idFor(p_set_2_);
        this.storage.setAt(p_set_1_, lvt_3_1_);
    }

    public IBlockState get(int p_get_1_, int p_get_2_, int p_get_3_) {
        return this.get(BlockStateContainer.getIndex(p_get_1_, p_get_2_, p_get_3_));
    }

    protected IBlockState get(int p_get_1_) {
        IBlockState lvt_2_1_ = this.palette.getBlockState(this.storage.getAt(p_get_1_));
        return lvt_2_1_ == null ? AIR_BLOCK_STATE : lvt_2_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void read(PacketBuffer p_read_1_) {
        byte lvt_2_1_ = p_read_1_.readByte();
        if (this.bits != lvt_2_1_) {
            this.setBits(lvt_2_1_);
        }
        this.palette.read(p_read_1_);
        PacketBufferHelper.readLongArray(p_read_1_, this.storage.getBackingLongArray());
    }

    public void write(PacketBuffer p_write_1_) {
        p_write_1_.writeByte(this.bits);
        this.palette.write(p_write_1_);
        PacketBufferHelper.writeLongArray(p_write_1_, this.storage.getBackingLongArray());
    }

    @Nullable
    public NibbleArray getDataForNBT(byte[] p_getDataForNBT_1_, NibbleArray p_getDataForNBT_2_) {
        NibbleArray lvt_3_1_ = null;
        for (int lvt_4_1_ = 0; lvt_4_1_ < 4096; ++lvt_4_1_) {
            int lvt_5_1_ = Block.BLOCK_STATE_IDS.get(this.get(lvt_4_1_));
            int lvt_6_1_ = lvt_4_1_ & 0xF;
            int lvt_7_1_ = lvt_4_1_ >> 8 & 0xF;
            int lvt_8_1_ = lvt_4_1_ >> 4 & 0xF;
            if ((lvt_5_1_ >> 12 & 0xF) != 0) {
                if (lvt_3_1_ == null) {
                    lvt_3_1_ = new NibbleArray();
                }
                lvt_3_1_.set(lvt_6_1_, lvt_7_1_, lvt_8_1_, lvt_5_1_ >> 12 & 0xF);
            }
            p_getDataForNBT_1_[lvt_4_1_] = (byte)(lvt_5_1_ >> 4 & 0xFF);
            p_getDataForNBT_2_.set(lvt_6_1_, lvt_7_1_, lvt_8_1_, lvt_5_1_ & 0xF);
        }
        return lvt_3_1_;
    }

    public void setDataFromNBT(byte[] p_setDataFromNBT_1_, NibbleArray p_setDataFromNBT_2_, @Nullable NibbleArray p_setDataFromNBT_3_) {
        for (int lvt_4_1_ = 0; lvt_4_1_ < 4096; ++lvt_4_1_) {
            int lvt_5_1_ = lvt_4_1_ & 0xF;
            int lvt_6_1_ = lvt_4_1_ >> 8 & 0xF;
            int lvt_7_1_ = lvt_4_1_ >> 4 & 0xF;
            int lvt_8_1_ = p_setDataFromNBT_3_ == null ? 0 : p_setDataFromNBT_3_.get(lvt_5_1_, lvt_6_1_, lvt_7_1_);
            int lvt_9_1_ = lvt_8_1_ << 12 | (p_setDataFromNBT_1_[lvt_4_1_] & 0xFF) << 4 | p_setDataFromNBT_2_.get(lvt_5_1_, lvt_6_1_, lvt_7_1_);
            this.set(lvt_4_1_, Block.BLOCK_STATE_IDS.getByValue(lvt_9_1_));
        }
    }

    public int getSerializedSize() {
        return 1 + this.palette.getSerializedState() + PacketBuffer.getVarIntSize(this.storage.size()) + this.storage.getBackingLongArray().length * 8;
    }
    
    public IBlockState getAtPalette(int index) {
        return palette.getBlockState(index);
    }

    public int[] storageArray() {
        return storage.toArray();
    }
}

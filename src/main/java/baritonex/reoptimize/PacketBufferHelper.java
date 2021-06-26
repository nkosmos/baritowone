package baritonex.reoptimize;

import javax.annotation.Nullable;

import io.netty.handler.codec.DecoderException;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketBufferHelper {
	
	public static PacketBuffer writeLongArray(PacketBuffer origin, long[] p_writeLongArray_1_) {
		origin.writeVarIntToBuffer(p_writeLongArray_1_.length);
        for (int lvt_2_1_ = 0; lvt_2_1_ < p_writeLongArray_1_.length; ++lvt_2_1_) {
        	origin.writeLong(p_writeLongArray_1_[lvt_2_1_]);
        }
        return origin;
    }

    @SideOnly(value=Side.CLIENT)
    public static long[] readLongArray(PacketBuffer origin, @Nullable long[] p_readLongArray_1_) {
        return readLongArray(origin, p_readLongArray_1_, origin.readableBytes() / 8);
    }

    @SideOnly(value=Side.CLIENT)
    public static long[] readLongArray(PacketBuffer origin, @Nullable long[] p_readLongArray_1_, int p_readLongArray_2_) {
        int lvt_3_1_ = origin.readVarIntFromBuffer();
        if (p_readLongArray_1_ == null || p_readLongArray_1_.length != lvt_3_1_) {
            if (lvt_3_1_ > p_readLongArray_2_) {
                throw new DecoderException("LongArray with size " + lvt_3_1_ + " is bigger than allowed " + p_readLongArray_2_);
            }
            p_readLongArray_1_ = new long[lvt_3_1_];
        }
        for (int lvt_4_1_ = 0; lvt_4_1_ < p_readLongArray_1_.length; ++lvt_4_1_) {
            p_readLongArray_1_[lvt_4_1_] = origin.readLong();
        }
        return p_readLongArray_1_;
    }

}

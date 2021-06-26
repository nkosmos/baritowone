package baritonex.reoptimize;

import org.apache.commons.lang3.Validate;

import net.minecraft.util.MathHelper;

public class BitArray {
    private final long[] longArray;
    private final int bitsPerEntry;
    private final long maxEntryValue;
    private final int arraySize;

    public BitArray(int p_i46832_1_, int p_i46832_2_) {
        Validate.inclusiveBetween(1L, 32L, p_i46832_1_);
        this.arraySize = p_i46832_2_;
        this.bitsPerEntry = p_i46832_1_;
        this.maxEntryValue = (1L << p_i46832_1_) - 1L;
        this.longArray = new long[MathHelper.roundUp(p_i46832_2_ * p_i46832_1_, 64) / 64];
    }

    public void setAt(int p_setAt_1_, int p_setAt_2_) {
        Validate.inclusiveBetween(0L, this.arraySize - 1, p_setAt_1_);
        Validate.inclusiveBetween(0L, this.maxEntryValue, p_setAt_2_);
        int lvt_3_1_ = p_setAt_1_ * this.bitsPerEntry;
        int lvt_4_1_ = lvt_3_1_ / 64;
        int lvt_5_1_ = ((p_setAt_1_ + 1) * this.bitsPerEntry - 1) / 64;
        int lvt_6_1_ = lvt_3_1_ % 64;
        this.longArray[lvt_4_1_] = this.longArray[lvt_4_1_] & (this.maxEntryValue << lvt_6_1_ ^ 0xFFFFFFFFFFFFFFFFL) | ((long)p_setAt_2_ & this.maxEntryValue) << lvt_6_1_;
        if (lvt_4_1_ != lvt_5_1_) {
            int lvt_7_1_ = 64 - lvt_6_1_;
            int lvt_8_1_ = this.bitsPerEntry - lvt_7_1_;
            this.longArray[lvt_5_1_] = this.longArray[lvt_5_1_] >>> lvt_8_1_ << lvt_8_1_ | ((long)p_setAt_2_ & this.maxEntryValue) >> lvt_7_1_;
        }
    }

    public int getAt(int p_getAt_1_) {
        Validate.inclusiveBetween(0L, this.arraySize - 1, p_getAt_1_);
        int lvt_2_1_ = p_getAt_1_ * this.bitsPerEntry;
        int lvt_3_1_ = lvt_2_1_ / 64;
        int lvt_4_1_ = ((p_getAt_1_ + 1) * this.bitsPerEntry - 1) / 64;
        int lvt_5_1_ = lvt_2_1_ % 64;
        if (lvt_3_1_ == lvt_4_1_) {
            return (int)(this.longArray[lvt_3_1_] >>> lvt_5_1_ & this.maxEntryValue);
        }
        int lvt_6_1_ = 64 - lvt_5_1_;
        return (int)((this.longArray[lvt_3_1_] >>> lvt_5_1_ | this.longArray[lvt_4_1_] << lvt_6_1_) & this.maxEntryValue);
    }

    public long[] getBackingLongArray() {
        return this.longArray;
    }

    public int size() {
        return this.arraySize;
    }
    
    public int[] toArray() {
        int[] out = new int[arraySize];

        for (int idx = 0, kl = bitsPerEntry - 1; idx < arraySize; idx++, kl += bitsPerEntry) {
            final int i = idx * bitsPerEntry;
            final int j = i >> 6;
            final int l = i & 63;
            final int k = kl >> 6;
            final long jl = longArray[j] >>> l;

            if (j == k) {
                out[idx] = (int) (jl & maxEntryValue);
            } else {
                out[idx] = (int) ((jl | longArray[k] << (64 - l)) & maxEntryValue);
            }
        }

        return out;
    }
}

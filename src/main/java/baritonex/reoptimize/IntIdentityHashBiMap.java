package baritonex.reoptimize;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

public class IntIdentityHashBiMap<K> implements Iterable<K> {
    private static final Object EMPTY = null;
    private K[] values;
    private int[] intKeys;
    private K[] byId;
    private int nextFreeIndex;
    private int mapSize;

    public IntIdentityHashBiMap(int p_i46830_1_) {
        p_i46830_1_ = (int)((float)p_i46830_1_ / 0.8f);
        this.values = (K[]) new Object[p_i46830_1_];
        this.intKeys = new int[p_i46830_1_];
        this.byId = (K[]) new Object[p_i46830_1_];
    }

    public int getId(K p_getId_1_) {
        return this.getValue(this.getIndex(p_getId_1_, this.hashObject(p_getId_1_)));
    }

    @Nullable
    public K get(int p_get_1_) {
        return p_get_1_ >= 0 && p_get_1_ < this.byId.length ? (K)this.byId[p_get_1_] : null;
    }

    private int getValue(int p_getValue_1_) {
        return p_getValue_1_ == -1 ? -1 : this.intKeys[p_getValue_1_];
    }

    public int add(K p_add_1_) {
        int i = this.nextId();
        this.put(p_add_1_, i);
        return i;
    }

    private int nextId() {
        while (this.nextFreeIndex < this.byId.length && this.byId[this.nextFreeIndex] != null) {
            ++this.nextFreeIndex;
        }
        return this.nextFreeIndex;
    }

    private void grow(int p_grow_1_) {
        K[] ak = this.values;
        int[] aint = this.intKeys;
        this.values = (K[]) new Object[p_grow_1_];
        this.intKeys = new int[p_grow_1_];
        this.byId = (K[]) new Object[p_grow_1_];
        this.nextFreeIndex = 0;
        this.mapSize = 0;
        for (int i = 0; i < ak.length; ++i) {
            if (ak[i] == null) continue;
            this.put(ak[i], aint[i]);
        }
    }

    public void put(K p_put_1_, int p_put_2_) {
        int i = Math.max(p_put_2_, this.mapSize + 1);
        if ((float)i >= (float)this.values.length * 0.8f) {
            int j;
            for (j = this.values.length << 1; j < p_put_2_; j <<= 1) {
            }
            this.grow(j);
        }
        int k = this.findEmpty(this.hashObject(p_put_1_));
        this.values[k] = p_put_1_;
        this.intKeys[k] = p_put_2_;
        this.byId[p_put_2_] = p_put_1_;
        ++this.mapSize;
        if (p_put_2_ == this.nextFreeIndex) {
            ++this.nextFreeIndex;
        }
    }

    private int hashObject(K p_hashObject_1_) {
        return (getHash(System.identityHashCode(p_hashObject_1_)) & Integer.MAX_VALUE) % this.values.length;
    }

    private int getHash(int p_getHash_0_) {
        p_getHash_0_ ^= p_getHash_0_ >>> 16;
        p_getHash_0_ *= -2048144789;
        p_getHash_0_ ^= p_getHash_0_ >>> 13;
        p_getHash_0_ *= -1028477387;
        p_getHash_0_ ^= p_getHash_0_ >>> 16;
        return p_getHash_0_;
    }
    
    private int getIndex(K p_getIndex_1_, int p_getIndex_2_) {
        for (int i = p_getIndex_2_; i < this.values.length; ++i) {
            if (this.values[i] == p_getIndex_1_) {
                return i;
            }
            if (this.values[i] != EMPTY) continue;
            return -1;
        }
        for (int j = 0; j < p_getIndex_2_; ++j) {
            if (this.values[j] == p_getIndex_1_) {
                return j;
            }
            if (this.values[j] != EMPTY) continue;
            return -1;
        }
        return -1;
    }

    private int findEmpty(int p_findEmpty_1_) {
        for (int i = p_findEmpty_1_; i < this.values.length; ++i) {
            if (this.values[i] != EMPTY) continue;
            return i;
        }
        for (int j = 0; j < p_findEmpty_1_; ++j) {
            if (this.values[j] != EMPTY) continue;
            return j;
        }
        throw new RuntimeException("Overflowed :(");
    }

    @Override
    public Iterator<K> iterator() {
        return Iterators.filter(Iterators.forArray(this.byId), Predicates.notNull());
    }

    public void clear() {
        Arrays.fill(this.values, null);
        Arrays.fill(this.byId, null);
        this.nextFreeIndex = 0;
        this.mapSize = 0;
    }

    public int size() {
        return this.mapSize;
    }
}
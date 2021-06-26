package baritonex.reoptimize;

import net.minecraft.block.state.IBlockState;

interface IBlockStatePaletteResizer {
    public int onResize(int var1, IBlockState var2);
}
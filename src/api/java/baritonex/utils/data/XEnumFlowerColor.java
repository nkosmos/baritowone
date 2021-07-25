package baritonex.utils.data;

import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;

public enum XEnumFlowerColor
{
    YELLOW,
    RED;

    public BlockFlower getBlock()
    {
        return this == YELLOW ? Blocks.yellow_flower : Blocks.red_flower;
    }
}
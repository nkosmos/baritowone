package baritonex.reoptimize;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockStatePalette {
    public int idFor(IBlockState var1);

    @Nullable
    public IBlockState getBlockState(int var1);

    @SideOnly(value=Side.CLIENT)
    public void read(PacketBuffer var1);

    public void write(PacketBuffer var1);

    public int getSerializedState();
}


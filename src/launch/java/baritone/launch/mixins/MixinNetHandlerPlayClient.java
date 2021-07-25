/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.launch.mixins;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.type.EventState;
import baritone.cache.CachedChunk;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;

/**
 * @author Brady
 * @since 8/3/2018
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(
            method = "handleChunkData",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/world/chunk/Chunk.fillChunk([BIZ)V"
            )
    )
    private void preRead(S21PacketChunkData packetIn, CallbackInfo ci) {
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
        	EntityClientPlayerMP player = ibaritone.getPlayerContext().player();
            if (player != null && player.sendQueue == (NetHandlerPlayClient) (Object) this) {
                ibaritone.getGameEventHandler().onChunkEvent(
                        new ChunkEvent(
                                EventState.PRE,
                                packetIn.func_149274_i() ? ChunkEvent.Type.POPULATE_FULL : ChunkEvent.Type.POPULATE_PARTIAL,
                                packetIn.func_149273_e(),
                                packetIn.func_149271_f()
                        )
                );
            }
        }
    }

    @Inject(
            method = "handleChunkData",
            at = @At("RETURN")
    )
    private void postHandleChunkData(S21PacketChunkData packetIn, CallbackInfo ci) {
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
        	EntityClientPlayerMP player = ibaritone.getPlayerContext().player();
            if (player != null && player.sendQueue == (NetHandlerPlayClient) (Object) this) {
                ibaritone.getGameEventHandler().onChunkEvent(
                        new ChunkEvent(
                                EventState.POST,
                                packetIn.func_149274_i() ? ChunkEvent.Type.POPULATE_FULL : ChunkEvent.Type.POPULATE_PARTIAL,
                                packetIn.func_149273_e(),
                                packetIn.func_149271_f()
                        )
                );
            }
        }
    }

    @Inject(
            method = "handleBlockChange",
            at = @At("RETURN")
    )
    private void postHandleBlockChange(S23PacketBlockChange packetIn, CallbackInfo ci) {
        if (!Baritone.settings().repackOnAnyBlockChange.value) {
            return;
        }
        if (!CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains(packetIn.field_148883_d)) {
            return;
        }
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
        	EntityClientPlayerMP player = ibaritone.getPlayerContext().player();
            if (player != null && player.sendQueue == (NetHandlerPlayClient) (Object) this) {
                ibaritone.getGameEventHandler().onChunkEvent(
                        new ChunkEvent(
                                EventState.POST,
                                ChunkEvent.Type.POPULATE_FULL,
                                packetIn.func_148879_d() >> 4,
                                packetIn.func_148877_f() >> 4
                        )
                );
            }
        }
    }

    @Inject(
            method = "handleMultiBlockChange",
            at = @At("RETURN")
    )
    private void postHandleMultiBlockChange(S22PacketMultiBlockChange packetIn, CallbackInfo ci) {
        if (!Baritone.settings().repackOnAnyBlockChange.value) {
            return;
        }
        if (packetIn.func_148922_e() == 0) {
            return;
        }
        
        List<Block> updateBlocks = new ArrayList<>();
        
        int chunkXPos = packetIn.func_148920_c().chunkXPos;
        int chunkZPos = packetIn.func_148920_c().chunkZPos;
        
        int chunkBaseX = chunkXPos * 16;
        int chunkBaseZ = chunkZPos * 16;

        if (packetIn.func_148921_d() != null) {
            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(packetIn.func_148921_d()));

            try {
                for (int k = 0; k < packetIn.func_148922_e(); ++k) {
                    datainputstream.readShort(); // useless
                    short short2 = datainputstream.readShort();
                    int blockID = short2 >> 4 & 4095;
                    updateBlocks.add(Block.getBlockById(blockID));
                }
            } catch (IOException ioexception) {
                ;
            }
        }
        
        https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.15
        {
            for (Block update : updateBlocks) {
                if (CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains(update)) {
                    break https;
                }
            }
            return;
        }
        
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
        	EntityClientPlayerMP player = ibaritone.getPlayerContext().player();
            if (player != null && player.sendQueue == (NetHandlerPlayClient) (Object) this) {
                ibaritone.getGameEventHandler().onChunkEvent(
                        new ChunkEvent(
                                EventState.POST,
                                ChunkEvent.Type.POPULATE_FULL,
                                chunkXPos,
                                chunkZPos                                
                        )
                );
            }
        }
    }
}

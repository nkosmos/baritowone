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

import static org.lwjgl.opengl.GL11.GL_CONSTANT_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_CONSTANT_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ZERO;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import baritone.Baritone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ChunkRenderContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;

@Mixin(ChunkRenderContainer.class)
public class MixinChunkRenderContainer {

    @Redirect( // avoid creating CallbackInfo at all costs; this is called 40k times per second
            method = "preRenderChunk",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/renderer/chunk/RenderChunk.getPosition()Lnet/minecraft/util/BlockPos;"
            )
    )
    private BlockPos getPosition(RenderChunk renderChunkIn) {
        if (Baritone.settings().renderCachedChunks.value && !Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().theWorld.getChunkFromBlockCoords(renderChunkIn.getPosition()).isEmpty()) {
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL14.glBlendColor(0, 0, 0, Baritone.settings().cachedChunksOpacity.value);
            OpenGlHelper.glBlendFunc(GL_CONSTANT_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA, GL_ONE, GL_ZERO);
        }
        return renderChunkIn.getPosition();
    }
}

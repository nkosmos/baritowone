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

package baritone.utils;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ZERO;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import baritone.api.BaritoneAPI;
import baritone.api.Settings;
import baritone.api.utils.Helper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;

public interface IRenderer {

    Tessellator tessellator = Tessellator.getInstance();
    WorldRenderer buffer = tessellator.getWorldRenderer();
    RenderManager renderManager = Helper.mc.getRenderManager();
    Settings settings = BaritoneAPI.getSettings();

    static void glColor(Color color, float alpha) {
        float[] colorComponents = color.getColorComponents(null);
        GL11.glColor4f(colorComponents[0], colorComponents[1], colorComponents[2], alpha);
    }

    static void startLines(Color color, float alpha, float lineWidth, boolean ignoreDepth) {
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        glColor(color, alpha);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);

        if (ignoreDepth) {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    static void startLines(Color color, float lineWidth, boolean ignoreDepth) {
        startLines(color, .4f, lineWidth, ignoreDepth);
    }

    static void endLines(boolean ignoredDepth) {
        if (ignoredDepth) {
        	GL11.glEnable(GL11.GL_DEPTH_TEST);
        }

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    static void drawAABB(AxisAlignedBB aabb) {
        AxisAlignedBB toDraw = aabb.offset(-renderManager.viewerPosX, -renderManager.viewerPosY, -renderManager.viewerPosZ);

        buffer.begin(GL_LINES, DefaultVertexFormats.POSITION);
        // bottom
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.minZ).endVertex();
        // top
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.minZ).endVertex();
        // corners
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.minZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.maxX, toDraw.maxY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.minY, toDraw.maxZ).endVertex();
        buffer.pos(toDraw.minX, toDraw.maxY, toDraw.maxZ).endVertex();
        tessellator.draw();
    }

    static void drawAABB(AxisAlignedBB aabb, double expand) {
        drawAABB(aabb.expand(expand, expand, expand));
    }
}

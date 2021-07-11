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

import static baritone.api.command.IBaritoneChatControl.FORCE_COMMAND_PREFIX;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW_MATRIX;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION_MATRIX;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_VIEWPORT;
import static org.lwjgl.opengl.GL11.GL_ZERO;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collections;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class GuiClick extends GuiScreen {

    // My name is Brady and I grant leijurv permission to use this pasted code
    private final FloatBuffer MODELVIEW = BufferUtils.createFloatBuffer(16);
    private final FloatBuffer PROJECTION = BufferUtils.createFloatBuffer(16);
    private final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
    private final FloatBuffer TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);

    private BlockPos clickStart;
    private BlockPos currentMouseOver;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int mx = Mouse.getX();
        int my = Mouse.getY();
        Vec3 near = toWorld(mx, my, 0);
        Vec3 far = toWorld(mx, my, 1); // "Use 0.945 that's what stack overflow says" - leijurv
        if (near != null && far != null) {
        	Vec3 viewerPos = new Vec3(mc.getRenderManager().viewerPosX, mc.getRenderManager().viewerPosY, mc.getRenderManager().viewerPosZ);
            MovingObjectPosition result = mc.theWorld.rayTraceBlocks(near.add(viewerPos), far.add(viewerPos), false, false, true);
            if (result != null && result.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                currentMouseOver = result.getBlockPos();
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (currentMouseOver != null) { //Catch this, or else a click into void will result in a crash
            if (mouseButton == 0) {
                if (clickStart != null && !clickStart.equals(currentMouseOver)) {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().removeAllSelections();
                    BaritoneAPI.getProvider().getPrimaryBaritone().getSelectionManager().addSelection(BetterBlockPos.from(clickStart), BetterBlockPos.from(currentMouseOver));
                    IChatComponent component = new ChatComponentText("Selection made! For usage: " + Baritone.settings().prefix.value + "help sel");
                    component.getChatStyle()
                            .setColor(EnumChatFormatting.WHITE)
                            .setChatClickEvent(new ClickEvent(
                                    ClickEvent.Action.RUN_COMMAND,
                                    FORCE_COMMAND_PREFIX + "help sel"
                            ));
                    Helper.HELPER.logDirect(component);
                    clickStart = null;
                } else {
                    BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(currentMouseOver));
                }
            } else if (mouseButton == 1) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().setGoalAndPath(new GoalBlock(currentMouseOver.up()));
            }
        }
        clickStart = null;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        clickStart = currentMouseOver;
    }

    public void onRender() {
        GL11.glGetFloat(GL_MODELVIEW_MATRIX, (FloatBuffer) MODELVIEW.clear());
        GL11.glGetFloat(GL_PROJECTION_MATRIX, (FloatBuffer) PROJECTION.clear());
        GL11.glGetInteger(GL_VIEWPORT, (IntBuffer) VIEWPORT.clear());

        if (currentMouseOver != null) {
            Entity e = mc.getRenderViewEntity();
            // drawSingleSelectionBox WHEN?
            PathRenderer.drawManySelectionBoxes(e, Collections.singletonList(currentMouseOver), Color.CYAN);
            if (clickStart != null && !clickStart.equals(currentMouseOver)) {
                GL11.glEnable(GL11.GL_BLEND); // GlStateManager.enableBlend();
                OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
                GL11.glColor4f(Color.RED.getColorComponents(null)[0], Color.RED.getColorComponents(null)[1], Color.RED.getColorComponents(null)[2], 0.4F);
                GL11.glLineWidth(Baritone.settings().pathRenderLineWidthPixels.value);
                GL11.glDisable(GL11.GL_TEXTURE_2D); // GlStateManager.disableTexture2D();
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST); // GlStateManager.disableDepth();
                BetterBlockPos a = new BetterBlockPos(currentMouseOver);
                BetterBlockPos b = new BetterBlockPos(clickStart);
                IRenderer.drawAABB(AxisAlignedBB.getBoundingBox(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z), Math.max(a.x, b.x) + 1, Math.max(a.y, b.y) + 1, Math.max(a.z, b.z) + 1));
                GL11.glEnable(GL11.GL_DEPTH_TEST);

                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_BLEND);
            }
        }
    }

    private Vec3 toWorld(double x, double y, double z) {
        boolean result = GLU.gluUnProject((float) x, (float) y, (float) z, MODELVIEW, PROJECTION, VIEWPORT, (FloatBuffer) TO_WORLD_BUFFER.clear());
        if (result) {
            return Vec3.createVectorHelper(TO_WORLD_BUFFER.get(0), TO_WORLD_BUFFER.get(1), TO_WORLD_BUFFER.get(2));
        }
        return null;
    }
}

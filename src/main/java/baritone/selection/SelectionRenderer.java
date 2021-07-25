package baritone.selection;

import baritone.Baritone;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.listener.AbstractGameEventListener;
import baritone.api.selection.ISelection;
import baritone.api.utils.BetterBlockPos;
import baritone.utils.IRenderer;
import net.minecraft.util.AxisAlignedBB;

public class SelectionRenderer implements IRenderer, AbstractGameEventListener {

    public static final double SELECTION_BOX_EXPANSION = .005D;

    private final SelectionManager manager;

    SelectionRenderer(Baritone baritone, SelectionManager manager) {
        this.manager = manager;
        baritone.getGameEventHandler().registerEventListener(this);
    }

    public static void renderSelections(ISelection[] selections) {
        float opacity = settings.selectionOpacity.value;
        boolean ignoreDepth = settings.renderSelectionIgnoreDepth.value;
        float lineWidth = settings.selectionLineWidth.value;

        if (!settings.renderSelection.value) {
            return;
        }

        IRenderer.startLines(settings.colorSelection.value, opacity, lineWidth, ignoreDepth);

        for (ISelection selection : selections) {
            IRenderer.drawAABB(selection.aabb(), SELECTION_BOX_EXPANSION);
        }

        if (settings.renderSelectionCorners.value) {
            IRenderer.glColor(settings.colorSelectionPos1.value, opacity);

            for (ISelection selection : selections) {
            	BetterBlockPos p1 = selection.pos1();
            	BetterBlockPos p2 = p1.add(1, 1, 1);
                IRenderer.drawAABB(AxisAlignedBB.getBoundingBox(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z));
            }

            IRenderer.glColor(settings.colorSelectionPos2.value, opacity);

            for (ISelection selection : selections) {
            	BetterBlockPos p1 = selection.pos2();
            	BetterBlockPos p2 = p1.add(1, 1, 1);
            	IRenderer.drawAABB(AxisAlignedBB.getBoundingBox(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z));
            }
        }

        IRenderer.endLines(ignoreDepth);
    }

    @Override
    public void onRenderPass(RenderEvent event) {
        renderSelections(manager.getSelections());
    }
}

package baritonex.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Optional;

import baritone.api.utils.accessor.IItemStack;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;

public class XHelper {
	
	// PROPERTY
	
	private static Map<PropertyEnum, Map<String, Object>> nameToValueCache = new HashMap<>();
	
	public static Optional parseValue(IProperty property, String string){
		if(property instanceof PropertyBool) {
			return Optional.of(Boolean.parseBoolean(string));
		}
		if(property instanceof PropertyInteger) {
			Collection<Integer> allowed = property.getAllowedValues();
			try {
				Integer val = Integer.valueOf(string);
				return allowed.contains(val) ? Optional.of(val) : Optional.absent();
			} catch(NumberFormatException e) {
				return Optional.absent();
			}
		}
		if(property instanceof PropertyEnum) {
			PropertyEnum p = (PropertyEnum)property;
			if(!nameToValueCache.containsKey(p)) {
				Map<String, Object> map = new HashMap<>();
				for(Object o : p.getAllowedValues()) {
					if(o instanceof IStringSerializable) {
						map.put(((IStringSerializable)o).getName(), o);
					}
				}
				nameToValueCache.put(p, map);
			}
			return Optional.fromNullable(nameToValueCache.get(p).get(string));
		}
		
		return Optional.absent();
	}
	
	// INVENTORY
	
	/**
	 * thx minecraft
	 * @param stack1
	 * @param stack2
	 * @return boolean
	 */
	private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }
	
	/**
	 * thx minecraft (slightly modified)
	 * @param stack
	 * @return int
	 */
    public static int getSlotFor(EntityPlayerSP player, ItemStack stack) {
        for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
            if (player.inventory.mainInventory[i] != null && stackEqualExact(stack, player.inventory.mainInventory[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean isHotbar(int index) {
        return index >= 0 && index < 9;
    }

    // ITEMSTACK
    
	public static boolean isEmpty(ItemStack itemStack) {
		return itemStack == null ? true : ((IItemStack) (Object) itemStack).isEmpty();
	}

	// RENDERER
	
	public static void renderBeamSegment(double x, double y, double z, double partialTicks, double textureScale,
			double totalWorldTime, int yOffset, int height, float[] colors, double beamRadius, double glowRadius) {
		int i = yOffset + height;
		GL11.glTexParameteri(3553, 10242, 10497);
		GL11.glTexParameteri(3553, 10243, 10497);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		OpenGlHelper.glBlendFunc(770, 1, 1, 0);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
		double d0 = totalWorldTime + partialTicks;
		double d1 = height < 0 ? d0 : -d0;
		double d2 = MathHelper.func_181162_h(d1 * 0.2D - (double) MathHelper.floor_double(d1 * 0.1D)); // MathHelper.frac
		float f = colors[0];
		float f1 = colors[1];
		float f2 = colors[2];
		double d3 = d0 * 0.025D * -1.5D;
		double d4 = 0.5D + Math.cos(d3 + 2.356194490192345D) * beamRadius;
		double d5 = 0.5D + Math.sin(d3 + 2.356194490192345D) * beamRadius;
		double d6 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * beamRadius;
		double d7 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * beamRadius;
		double d8 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * beamRadius;
		double d9 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * beamRadius;
		double d10 = 0.5D + Math.cos(d3 + 5.497787143782138D) * beamRadius;
		double d11 = 0.5D + Math.sin(d3 + 5.497787143782138D) * beamRadius;
		double d13 = 1.0D;
		double d14 = -1.0D + d2;
		double d15 = (double) height * textureScale * (0.5D / beamRadius) + d14;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d4, y + (double) yOffset, z + d5).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d6, y + (double) yOffset, z + d7).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d10, y + (double) yOffset, z + d11).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d8, y + (double) yOffset, z + d9).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d6, y + (double) yOffset, z + d7).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d10, y + (double) yOffset, z + d11).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d8, y + (double) yOffset, z + d9).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d4, y + (double) yOffset, z + d5).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
		vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
		tessellator.draw();
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDepthMask(false);
		d3 = 0.5D - glowRadius;
		d4 = 0.5D - glowRadius;
		d5 = 0.5D + glowRadius;
		d6 = 0.5D - glowRadius;
		d7 = 0.5D - glowRadius;
		d8 = 0.5D + glowRadius;
		d9 = 0.5D + glowRadius;
		d10 = 0.5D + glowRadius;
		d11 = 0.0D;
		d13 = -1.0D + d2;
		d14 = (double) height * textureScale + d13;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d3, y + (double) yOffset, z + d4).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d5, y + (double) yOffset, z + d6).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d9, y + (double) yOffset, z + d10).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d7, y + (double) yOffset, z + d8).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d5, y + (double) yOffset, z + d6).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d9, y + (double) yOffset, z + d10).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d7, y + (double) yOffset, z + d8).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d3, y + (double) yOffset, z + d4).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
		vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
		tessellator.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
	}

}

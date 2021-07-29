package baritonex.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.IProperty;
import baritonex.utils.property.impl.PropertyBool;
import baritonex.utils.property.impl.PropertyEnum;
import baritonex.utils.property.impl.PropertyInteger;
import baritonex.utils.registry.ItemStackRegistry;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackAccessor;
import net.minecraft.util.MathHelper;

public class XHelper {
	// je fais koi du kou?
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
				for(Enum o : (Collection<Enum>)p.getAllowedValues()) {
					map.put(o.name(), o);
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
    
    private static final ItemStack EMPTY = new ItemStack((Item)null);
    
	public static boolean isEmpty(ItemStack itemStack) { // what the fuck
		Item item = ItemStackRegistry.stackItems.getOrDefault(itemStack, null);
		ItemStackAccessor accessor = ItemStackRegistry.accessors.computeIfAbsent(itemStack, ItemStackAccessor::new);
		return itemStack == null 
				? true 
				: (itemStack == EMPTY 
						? true 
						: (item != null && item != Item.getItemFromBlock(Blocks.air) 
								? (itemStack.stackSize <= 0 
										? true 
										: accessor.getMetadata() < -32768 || accessor.getMetadata() > 65535) 
								: true
						)
				)
		;
	}
	
	// ENUMFACING
	
	private static final Map<Integer, XEnumFacing> HIT_LOOKUP = Maps.newHashMap();

	public static XEnumFacing sideToFacing(int sidehit) {
		return sidehit == -1 ? null : HIT_LOOKUP.get(sidehit);
	}

	public static int facingToInt(XEnumFacing facing) {
		return HIT_LOOKUP.entrySet().stream().filter(e -> e.getValue() == facing).map(Map.Entry::getKey).findFirst().orElse(-1);
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
		Tessellator tessellator = Tessellator.instance;
		double d0 = totalWorldTime + partialTicks;
		double d1 = height < 0 ? d0 : -d0;
		double someValue = d1 * 0.2D - (double) MathHelper.floor_double(d1 * 0.1D); // MathHelper.frac
		double d2 = someValue - Math.floor(someValue);
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
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_F(f, f1, f2);
		tessellator.addVertexWithUV(x + d4, y + (double) i, z + d5, 1.0D, d15);
		tessellator.addVertexWithUV(x + d4, y + (double) yOffset, z + d5, 1.0D, d14);
		tessellator.addVertexWithUV(x + d6, y + (double) yOffset, z + d7, 0.0D, d14);
		tessellator.addVertexWithUV(x + d6, y + (double) i, z + d7, 0.0D, d15);
		tessellator.addVertexWithUV(x + d10, y + (double) i, z + d11, 1.0D, d15);
		tessellator.addVertexWithUV(x + d10, y + (double) yOffset, z + d11, 1.0D, d14);
		tessellator.addVertexWithUV(x + d8, y + (double) yOffset, z + d9, 0.0D, d14);
		tessellator.addVertexWithUV(x + d8, y + (double) i, z + d9, 0.0D, d15);
		tessellator.addVertexWithUV(x + d6, y + (double) i, z + d7, 1.0D, d15);
		tessellator.addVertexWithUV(x + d6, y + (double) yOffset, z + d7, 1.0D, d14);
		tessellator.addVertexWithUV(x + d10, y + (double) yOffset, z + d11, 0.0D, d14);
		tessellator.addVertexWithUV(x + d10, y + (double) i, z + d11, 0.0D, d15);
		tessellator.addVertexWithUV(x + d8, y + (double) i, z + d9, 1.0D, d15);
		tessellator.addVertexWithUV(x + d8, y + (double) yOffset, z + d9, 1.0D, d14);
		tessellator.addVertexWithUV(x + d4, y + (double) yOffset, z + d5, 0.0D, d14);
		tessellator.addVertexWithUV(x + d4, y + (double) i, z + d5, 0.0D, d15);
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
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_F(f, f1, f2);
		tessellator.addVertexWithUV(x + d3, y + (double) i, z + d4, 1.0D, d14);
		tessellator.addVertexWithUV(x + d3, y + (double) yOffset, z + d4, 1.0D, d13);
		tessellator.addVertexWithUV(x + d5, y + (double) yOffset, z + d6, 0.0D, d13);
		tessellator.addVertexWithUV(x + d5, y + (double) i, z + d6, 0.0D, d14);
		tessellator.addVertexWithUV(x + d9, y + (double) i, z + d10, 1.0D, d14);
		tessellator.addVertexWithUV(x + d9, y + (double) yOffset, z + d10, 1.0D, d13);
		tessellator.addVertexWithUV(x + d7, y + (double) yOffset, z + d8, 0.0D, d13);
		tessellator.addVertexWithUV(x + d7, y + (double) i, z + d8, 0.0D, d14);
		tessellator.addVertexWithUV(x + d5, y + (double) i, z + d6, 1.0D, d14);
		tessellator.addVertexWithUV(x + d5, y + (double) yOffset, z + d6, 1.0D, d13);
		tessellator.addVertexWithUV(x + d9, y + (double) yOffset, z + d10, 0.0D, d13);
		tessellator.addVertexWithUV(x + d9, y + (double) i, z + d10, 0.0D, d14);
		tessellator.addVertexWithUV(x + d7, y + (double) i, z + d8, 1.0D, d14);
		tessellator.addVertexWithUV(x + d7, y + (double) yOffset, z + d8, 1.0D, d13);
		tessellator.addVertexWithUV(x + d3, y + (double) yOffset, z + d4, 0.0D, d13);
		tessellator.addVertexWithUV(x + d3, y + (double) i, z + d4, 0.0D, d14);
		tessellator.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
	}
	
	// MATHS

	private static final double field_181163_d;
    private static final double[] field_181164_e;
    private static final double[] field_181165_f;
	
	public static double atan2(double p_181159_0_, double p_181159_2_) {
        final double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;
        if (Double.isNaN(d0)) {
            return Double.NaN;
        }
        final boolean flag = p_181159_0_ < 0.0;
        if (flag) {
            p_181159_0_ = -p_181159_0_;
        }
        final boolean flag2 = p_181159_2_ < 0.0;
        if (flag2) {
            p_181159_2_ = -p_181159_2_;
        }
        final boolean flag3 = p_181159_0_ > p_181159_2_;
        if (flag3) {
            final double d2 = p_181159_2_;
            p_181159_2_ = p_181159_0_;
            p_181159_0_ = d2;
        }
        final double d3 = func_181161_i(d0);
        p_181159_2_ *= d3;
        p_181159_0_ *= d3;
        final double d4 = field_181163_d + p_181159_0_;
        final int i = (int)Double.doubleToRawLongBits(d4);
        final double d5 = field_181164_e[i];
        final double d6 = field_181165_f[i];
        final double d7 = d4 - field_181163_d;
        final double d8 = p_181159_0_ * d6 - p_181159_2_ * d7;
        final double d9 = (6.0 + d8 * d8) * d8 * 0.16666666666666666;
        double d10 = d5 + d9;
        if (flag3) {
            d10 = 1.5707963267948966 - d10;
        }
        if (flag2) {
            d10 = 3.141592653589793 - d10;
        }
        if (flag) {
            d10 = -d10;
        }
        return d10;
    }
	
	public static double func_181161_i(double p_181161_0_) {
        final double d0 = 0.5 * p_181161_0_;
        long i = Double.doubleToRawLongBits(p_181161_0_);
        i = 6910469410427058090L - (i >> 1);
        p_181161_0_ = Double.longBitsToDouble(i);
        p_181161_0_ *= 1.5 - d0 * p_181161_0_ * p_181161_0_;
        return p_181161_0_;
    }
	
	static {
		HIT_LOOKUP.put(0, XEnumFacing.DOWN);
		HIT_LOOKUP.put(1, XEnumFacing.UP);
		HIT_LOOKUP.put(2, XEnumFacing.EAST);
		HIT_LOOKUP.put(3, XEnumFacing.WEST);
		HIT_LOOKUP.put(4, XEnumFacing.NORTH);
		HIT_LOOKUP.put(5, XEnumFacing.SOUTH);
		
		field_181163_d = Double.longBitsToDouble(4805340802404319232L);
        field_181164_e = new double[257];
        field_181165_f = new double[257];
        for (int j = 0; j < 257; ++j) {
            final double d0 = j / 256.0;
            final double d2 = Math.asin(d0);
            field_181165_f[j] = Math.cos(d2);
            field_181164_e[j] = d2;
        }
    }

}

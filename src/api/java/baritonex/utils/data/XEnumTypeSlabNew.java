package baritonex.utils.data;

import net.minecraft.block.material.MapColor;

public enum XEnumTypeSlabNew {
	RED_SANDSTONE(0, "red_sandstone", XEnumTypeSand.RED_SAND.getMapColor());

	private static final XEnumTypeSlabNew[] META_LOOKUP = new XEnumTypeSlabNew[values().length];
	private final int meta;
	private final String name;
	private final MapColor field_181069_e;

	private XEnumTypeSlabNew(int p_i46391_3_, String p_i46391_4_, MapColor p_i46391_5_) {
		this.meta = p_i46391_3_;
		this.name = p_i46391_4_;
		this.field_181069_e = p_i46391_5_;
	}

	public int getMetadata() {
		return this.meta;
	}

	public MapColor func_181068_c() {
		return this.field_181069_e;
	}

	public String toString() {
		return this.name;
	}

	public static XEnumTypeSlabNew byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String getName() {
		return this.name;
	}

	public String getUnlocalizedName() {
		return this.name;
	}

	static {
		for (XEnumTypeSlabNew blockstoneslabnew$enumtype : values()) {
			META_LOOKUP[blockstoneslabnew$enumtype.getMetadata()] = blockstoneslabnew$enumtype;
		}
	}
}

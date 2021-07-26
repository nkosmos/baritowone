package baritonex.utils.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

import baritonex.utils.data.XDirtType;
import baritonex.utils.data.XEnumAttachPosition;
import baritonex.utils.data.XEnumAxis;
import baritonex.utils.data.XEnumBlockHalfSlab;
import baritonex.utils.data.XEnumBlockHalfTrapdoor;
import baritonex.utils.data.XEnumDoorHalf;
import baritonex.utils.data.XEnumDyeColor;
import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumFlowerType;
import baritonex.utils.data.XEnumFlowerTypeForge;
import baritonex.utils.data.XEnumHalfStairs;
import baritonex.utils.data.XEnumHingePosition;
import baritonex.utils.data.XEnumOrientation;
import baritonex.utils.data.XEnumPartType;
import baritonex.utils.data.XEnumPistonType;
import baritonex.utils.data.XEnumPlantType;
import baritonex.utils.data.XEnumRailDirection;
import baritonex.utils.data.XEnumShapeStairs;
import baritonex.utils.data.XEnumSilverFish;
import baritonex.utils.data.XEnumTypeMushroom;
import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.data.XEnumTypePrismarine;
import baritonex.utils.data.XEnumTypeQuartz;
import baritonex.utils.data.XEnumTypeSand;
import baritonex.utils.data.XEnumTypeSandstone;
import baritonex.utils.data.XEnumTypeStone;
import baritonex.utils.data.XEnumTypeStoneSlab;
import baritonex.utils.data.XEnumTypeStonebrick;
import baritonex.utils.data.XEnumTypeTallGrass;
import baritonex.utils.data.XEnumTypeWall;
import baritonex.utils.data.XMode;
import baritonex.utils.data.XTrapDoorHalf;
import baritonex.utils.property.impl.PropertyBool;
import baritonex.utils.property.impl.PropertyDirection;
import baritonex.utils.property.impl.PropertyEnum;
import baritonex.utils.property.impl.PropertyInteger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockWood;
import net.minecraft.block.BlockWoodSlab;

public class Properties {
	
	public static final PropertyDirection ANVIL_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger ANVIL_DAMAGE = PropertyInteger.create("damage", 0, 2);
	
    public static final PropertyDirection BANNER_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger BANNER_ROTATION = PropertyInteger.create("rotation", 0, 15);
    
    public static final PropertyEnum<XEnumPartType> BED_PART = PropertyEnum.<XEnumPartType>create("part", XEnumPartType.class);
    public static final PropertyBool BED_OCCUPIED = PropertyBool.create("occupied");
    
    // fais pas celle là je m'en occuperai
    public static final PropertyBool[] BREWINGSTAND_HAS_BOTTLE = new PropertyBool[] {PropertyBool.create("has_bottle_0"), PropertyBool.create("has_bottle_1"), PropertyBool.create("has_bottle_2")};
    //
    
    public static final PropertyDirection BUTTON_FACING = PropertyDirection.create("facing");
    public static final PropertyBool BUTTON_POWERED = PropertyBool.create("powered");
    
    public static final PropertyInteger CACTUS_AGE = PropertyInteger.create("age", 0, 15);
    
    public static final PropertyInteger CAKE_BITES = PropertyInteger.create("bites", 0, 6);
    
    public static final PropertyEnum<XEnumDyeColor> CARPET_COLOR = PropertyEnum.<XEnumDyeColor>create("color", XEnumDyeColor.class);
    
    public static final PropertyInteger CAULDRON_LEVEL = PropertyInteger.create("level", 0, 3);
    
    public static final PropertyDirection CHEST_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyInteger COCOA_AGE = PropertyInteger.create("age", 0, 2);
    
    public static final PropertyEnum<XEnumDyeColor> COLORED_COLOR = PropertyEnum.<XEnumDyeColor>create("color", XEnumDyeColor.class);
    
    public static final PropertyBool COMMANDBLOCK_TRIGGERED = PropertyBool.create("triggered");
    
    public static final PropertyInteger Crops_AGE = PropertyInteger.create("age", 0, 7);
    
    public static final PropertyInteger DAYLIGHTDETECTOR_POWER = PropertyInteger.create("power", 0, 15);
    
    public static final PropertyDirection DIRECTIONAL_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyEnum<XDirtType> DIRT_VARIANT = PropertyEnum.<XDirtType>create("variant", XDirtType.class);
    public static final PropertyBool DIRT_SNOWY = PropertyBool.create("snowy");
    
    public static final PropertyDirection DISPENSER_FACING = PropertyDirection.create("facing");
    public static final PropertyBool DISPENSER_TRIGGERED = PropertyBool.create("triggered");
    
    public static final PropertyDirection DOOR_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool DOOR_OPEN = PropertyBool.create("open");
    public static final PropertyEnum<XEnumHingePosition> DOOR_HINGE = PropertyEnum.<XEnumHingePosition>create("hinge", XEnumHingePosition.class);
    public static final PropertyBool DOOR_POWERED = PropertyBool.create("powered");
    public static final PropertyEnum<XEnumDoorHalf> DOOR_HALF = PropertyEnum.<XEnumDoorHalf>create("half", XEnumDoorHalf.class);

    public static final PropertyEnum<XEnumPlantType> DOUBLEPLANT_VARIANT = PropertyEnum.<XEnumPlantType>create("variant", XEnumPlantType.class);
    public static final PropertyEnum<XEnumBlockHalfTrapdoor> DOUBLEPLANT_HALF = PropertyEnum.<XEnumBlockHalfTrapdoor>create("half", XEnumBlockHalfTrapdoor.class);
    public static final PropertyEnum<XEnumFacing> DOUBLEPLANT_FACING = DIRECTIONAL_FACING;
    
    public static final PropertyDirection ENDERCHEST_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyDirection ENDPORTALFRAME_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool ENDPORTALFRAME_EYE = PropertyBool.create("eye");
	
    public static final PropertyInteger FARMLAND_MOISTURE = PropertyInteger.create("moisture", 0, 7);
    
    /** Whether this fence connects in the northern direction */
    public static final PropertyBool FENCE_NORTH = PropertyBool.create("north");

    /** Whether this fence connects in the eastern direction */
    public static final PropertyBool FENCE_EAST = PropertyBool.create("east");

    /** Whether this fence connects in the southern direction */
    public static final PropertyBool FENCE_SOUTH = PropertyBool.create("south");

    /** Whether this fence connects in the western direction */
    public static final PropertyBool FENCE_WEST = PropertyBool.create("west");
    
    
    public static final PropertyBool FENCEGATE_OPEN = PropertyBool.create("open");
    public static final PropertyBool FENCEGATE_POWERED = PropertyBool.create("powered");
    public static final PropertyBool FENCEGATE_IN_WALL = PropertyBool.create("in_wall");
    
    public static final PropertyInteger FIRE_AGE = PropertyInteger.create("age", 0, 15);
    public static final PropertyBool FIRE_FLIP = PropertyBool.create("flip");
    public static final PropertyBool FIRE_ALT = PropertyBool.create("alt");
    public static final PropertyBool FIRE_NORTH = PropertyBool.create("north");
    public static final PropertyBool FIRE_EAST = PropertyBool.create("east");
    public static final PropertyBool FIRE_SOUTH = PropertyBool.create("south");
    public static final PropertyBool FIRE_WEST = PropertyBool.create("west");
    public static final PropertyInteger FIRE_UPPER = PropertyInteger.create("upper", 0, 2);
    
    public static final PropertyEnum<XEnumFlowerType> FLOWER_TYPE = PropertyEnum.<XEnumFlowerType>create("type", XEnumFlowerType.class, new Predicate<XEnumFlowerType>()
    {
        public boolean apply(XEnumFlowerType p_apply_1_)
        {
        	return true;
         //   return p_apply_1_.getBlockType() == BlockFlower.this.getBlockType();
        }
    });
    
    public static final PropertyInteger FLOWERPOT_LEGACY_DATA = PropertyInteger.create("legacy_data", 0, 15);
    public static final PropertyEnum<XEnumFlowerTypeForge> FLOWERPOT_CONTENTS = PropertyEnum.<XEnumFlowerTypeForge>create("contents", XEnumFlowerTypeForge.class);

    public static final PropertyDirection FURNACE_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyBool GRASS_SNOWY = PropertyBool.create("snowy");
    
    public static final PropertyDirection HOPPER_FACING = PropertyDirection.create("facing", new Predicate<XEnumFacing>()
    {
        public boolean apply(XEnumFacing p_apply_1_)
        {
            return p_apply_1_ != XEnumFacing.UP;
        }
    });
    public static final PropertyBool HOPPER_ENABLED = PropertyBool.create("enabled");
    
    public static final PropertyEnum<XEnumTypeMushroom> HUGEMUSHROOM_VARIANT = PropertyEnum.<XEnumTypeMushroom>create("variant", XEnumTypeMushroom.class);
    
    public static final PropertyBool JUKEBOX_HAS_RECORD = PropertyBool.create("has_record");

    public static final PropertyDirection LADDER_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyBool LEAVES_DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool LEAVES_CHECK_DECAY = PropertyBool.create("check_decay");
    
    public static final PropertyEnum<XEnumOrientation> LEVER_FACING = PropertyEnum.<XEnumOrientation>create("facing", XEnumOrientation.class);
    public static final PropertyBool LEVER_POWERED = PropertyBool.create("powered");
    
    public static final PropertyInteger LIQUID_LEVEL = PropertyInteger.create("level", 0, 15);

    public static final PropertyEnum<XEnumAxis> LOG_AXIS = PropertyEnum.<XEnumAxis>create("axis", XEnumAxis.class);

    public static final PropertyBool MYCELIUM_SNOWY = PropertyBool.create("snowy");
    
    public static final PropertyInteger NETHERWART_AGE = PropertyInteger.create("age", 0, 3);
    
    public static final PropertyEnum<XEnumTypePlanks> NEWLEAF_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class, new Predicate<XEnumTypePlanks>()
    {
        public boolean apply(XEnumTypePlanks p_apply_1_)
        {
            return p_apply_1_.getMetadata() >= 4;
        }
    });
    
    public static final PropertyEnum<XEnumTypePlanks> NEWLOG_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class, new Predicate<XEnumTypePlanks>()
    {
        public boolean apply(XEnumTypePlanks p_apply_1_)
        {
            return p_apply_1_.getMetadata() >= 4;
        }
    });
    
    public static final PropertyEnum<XEnumTypePlanks> OLDLEAF_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class, new Predicate<XEnumTypePlanks>()
    {
        public boolean apply(XEnumTypePlanks p_apply_1_)
        {
            return p_apply_1_.getMetadata() < 4;
        }
    });
    
    public static final PropertyEnum<XEnumTypePlanks> OLDLOG_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class, new Predicate<XEnumTypePlanks>()
    {
        public boolean apply(XEnumTypePlanks p_apply_1_)
        {
            return p_apply_1_.getMetadata() < 4;
        }
    });
    
    public static final PropertyBool PANE_NORTH = PropertyBool.create("north");
    public static final PropertyBool PANE_EAST = PropertyBool.create("east");
    public static final PropertyBool PANE_SOUTH = PropertyBool.create("south");
    public static final PropertyBool PANE_WEST = PropertyBool.create("west");
    
    public static final PropertyDirection PISTONBASE_FACING = PropertyDirection.create("facing");
    public static final PropertyBool PISTONBASE_EXTENDED = PropertyBool.create("extended");
    
    public static final PropertyDirection PISTONEXTENSION_FACING = PropertyDirection.create("facing");
    public static final PropertyEnum<XEnumPistonType> PISTONEXTENSION_TYPE = PropertyEnum.<XEnumPistonType>create("type", XEnumPistonType.class);
    public static final PropertyBool PISTONEXTENSION_SHORT = PropertyBool.create("short");
    
    public static final PropertyDirection PISTONMOVING_FACING = PISTONEXTENSION_FACING;
    public static final PropertyEnum<XEnumPistonType> PISTONMOVING_TYPE = PISTONEXTENSION_TYPE;
    
    public static final PropertyEnum<XEnumTypePlanks> PLANKS_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class);

    public static final PropertyEnum<XEnumFacing.Axis> PORTAL_AXIS = PropertyEnum.<XEnumFacing.Axis>create("axis", XEnumFacing.Axis.class, new XEnumFacing.Axis[] {XEnumFacing.Axis.X, XEnumFacing.Axis.Z});

    public static final PropertyBool PRESSUREPLATE_POWERED = PropertyBool.create("powered");
    
    public static final PropertyInteger PRESSUREPLATEWEIGHTED_POWER = PropertyInteger.create("power", 0, 15);
    
    public static final PropertyEnum<XEnumTypePrismarine> PRISMARINE_VARIANT = PropertyEnum.<XEnumTypePrismarine>create("variant", XEnumTypePrismarine.class);
    
    public static final PropertyEnum<XEnumTypeQuartz> QUARTZ_VARIANT = PropertyEnum.<XEnumTypeQuartz>create("variant", XEnumTypeQuartz.class);

    public static final PropertyEnum<XEnumRailDirection> RAIL_SHAPE = PropertyEnum.<XEnumRailDirection>create("shape", XEnumRailDirection.class);

    public static final PropertyEnum<XEnumRailDirection> RAILDETECTOR_SHAPE = PropertyEnum.<XEnumRailDirection>create("shape", XEnumRailDirection.class, new Predicate<XEnumRailDirection>()
    {
        public boolean apply(XEnumRailDirection p_apply_1_)
        {
            return p_apply_1_ != XEnumRailDirection.NORTH_EAST && p_apply_1_ != XEnumRailDirection.NORTH_WEST && p_apply_1_ != XEnumRailDirection.SOUTH_EAST && p_apply_1_ != XEnumRailDirection.SOUTH_WEST;
        }
    });
    public static final PropertyBool RAILDETECTOR_POWERED = PropertyBool.create("powered");
    
    public static final PropertyEnum<XEnumRailDirection> RAILPOWERED_SHAPE = PropertyEnum.<XEnumRailDirection>create("shape", XEnumRailDirection.class, new Predicate<XEnumRailDirection>()
    {
        public boolean apply(XEnumRailDirection p_apply_1_)
        {
            return p_apply_1_ != XEnumRailDirection.NORTH_EAST && p_apply_1_ != XEnumRailDirection.NORTH_WEST && p_apply_1_ != XEnumRailDirection.SOUTH_EAST && p_apply_1_ != XEnumRailDirection.SOUTH_WEST;
        }
    });
    public static final PropertyBool RAILPOWERED_POWERED = PropertyBool.create("powered");

    public static final PropertyBool REDSTONECOMPARATOR_POWERED = PropertyBool.create("powered");
    public static final PropertyEnum<XMode> REDSTONECOMPARATOR_MODE = PropertyEnum.<XMode>create("mode", XMode.class);

    public static final PropertyBool REDSTONEREPEATER_LOCKED = PropertyBool.create("locked");
    public static final PropertyInteger REDSTONEREPEATER_DELAY = PropertyInteger.create("delay", 1, 4);
    
    public static final PropertyEnum<XEnumAttachPosition> REDSTONEWIRE_NORTH = PropertyEnum.<XEnumAttachPosition>create("north", XEnumAttachPosition.class);
    public static final PropertyEnum<XEnumAttachPosition> REDSTONEWIRE_EAST = PropertyEnum.<XEnumAttachPosition>create("east", XEnumAttachPosition.class);
    public static final PropertyEnum<XEnumAttachPosition> REDSTONEWIRE_SOUTH = PropertyEnum.<XEnumAttachPosition>create("south", XEnumAttachPosition.class);
    public static final PropertyEnum<XEnumAttachPosition> REDSTONEWIRE_WEST = PropertyEnum.<XEnumAttachPosition>create("west", XEnumAttachPosition.class);    
    public static final PropertyInteger REDSTONEWIRE_POWER = PropertyInteger.create("power", 0, 15);
    
    public static final PropertyInteger REED_AGE = PropertyInteger.create("age", 0, 15);
    
    public static final PropertyEnum<XEnumFacing.Axis> ROTATEDPILLAR_AXIS = PropertyEnum.<XEnumFacing.Axis>create("axis", XEnumFacing.Axis.class);
    
    public static final PropertyEnum<XEnumTypeSand> SAND_VARIANT = PropertyEnum.<XEnumTypeSand>create("variant", XEnumTypeSand.class);

    public static final PropertyEnum<XEnumTypeSandstone> SANDSTONE_TYPE = PropertyEnum.<XEnumTypeSandstone>create("type", XEnumTypeSandstone.class);

    public static final PropertyEnum<XEnumTypePlanks> SAPLING_TYPE = PropertyEnum.<XEnumTypePlanks>create("type", XEnumTypePlanks.class);
    public static final PropertyInteger SAPLING_STAGE = PropertyInteger.create("stage", 0, 1);
    
    public static final PropertyEnum<XEnumSilverFish> SILVERFISH_VARIANT = PropertyEnum.<XEnumSilverFish>create("variant", XEnumSilverFish.class);

    public static final PropertyDirection SKULL_FACING = PropertyDirection.create("facing");
    public static final PropertyBool SKULL_NODROP = PropertyBool.create("nodrop");
    
    public static final PropertyEnum<XEnumBlockHalfSlab> SLAB_HALF = PropertyEnum.<XEnumBlockHalfSlab>create("half", XEnumBlockHalfSlab.class);

    public static final PropertyInteger SNOW_LAYERS = PropertyInteger.create("layers", 1, 8);

    public static final PropertyBool SPONGE_WET = PropertyBool.create("wet");
    
    public static final PropertyEnum<XEnumDyeColor> STAINEDGLASS_COLOR = PropertyEnum.<XEnumDyeColor>create("color", XEnumDyeColor.class);

    public static final PropertyEnum<XEnumDyeColor> STAINEDGLASSPANE_COLOR = PropertyEnum.<XEnumDyeColor>create("color", XEnumDyeColor.class);

    public static final PropertyDirection STAIRS_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL); 
    public static final PropertyEnum<XEnumHalfStairs> STAIRS_HALF = PropertyEnum.<XEnumHalfStairs>create("half", XEnumHalfStairs.class);
    public static final PropertyEnum<XEnumShapeStairs> STAIRS_SHAPE = PropertyEnum.<XEnumShapeStairs>create("shape", XEnumShapeStairs.class);
    
    public static final PropertyInteger STANDINGSIGN_ROTATION = PropertyInteger.create("rotation", 0, 15);
    
    public static final PropertyInteger STEM_AGE = PropertyInteger.create("age", 0, 7);
    public static final PropertyDirection STEM_FACING = PropertyDirection.create("facing", new Predicate<XEnumFacing>()
    {
        public boolean apply(XEnumFacing p_apply_1_)
        {
            return p_apply_1_ != XEnumFacing.DOWN;
        }
    });
    
    public static final PropertyEnum<XEnumTypeStone> STONE_VARIANT = PropertyEnum.<XEnumTypeStone>create("variant", XEnumTypeStone.class);

    public static final PropertyEnum<XEnumTypeStonebrick> STONEBRICK_VARIANT = PropertyEnum.<XEnumTypeStonebrick>create("variant", XEnumTypeStonebrick.class);
    
    public static final PropertyBool STONESLAB_SEAMLESS = PropertyBool.create("seamless");
    public static final PropertyEnum<XEnumTypeStoneSlab> STONESLAB_VARIANT = PropertyEnum.<XEnumTypeStoneSlab>create("variant", XEnumTypeStoneSlab.class);

    public static final PropertyEnum<XEnumTypeTallGrass> TALLGRASS_TYPE = PropertyEnum.<XEnumTypeTallGrass>create("type", XEnumTypeTallGrass.class);

    public static final PropertyBool TNT_EXPLODE = PropertyBool.create("explode");
    
    public static final PropertyDirection TORCH_FACING = PropertyDirection.create("facing", new Predicate<XEnumFacing>()
    {
        public boolean apply(XEnumFacing p_apply_1_)
        {
            return p_apply_1_ != XEnumFacing.DOWN;
        }
    });
    
    public static final PropertyDirection TRAPDOOR_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool TRAPDOOR_OPEN = PropertyBool.create("open");
    public static final PropertyEnum<XTrapDoorHalf> TRAPDOOR_HALF = PropertyEnum.<XTrapDoorHalf>create("half", XTrapDoorHalf.class);

    public static final PropertyBool TRIPWIRE_POWERED = PropertyBool.create("powered");
    public static final PropertyBool TRIPWIRE_SUSPENDED = PropertyBool.create("suspended");
    public static final PropertyBool TRIPWIRE_ATTACHED = PropertyBool.create("attached");
    public static final PropertyBool TRIPWIRE_DISARMED = PropertyBool.create("disarmed");
    public static final PropertyBool TRIPWIRE_NORTH = PropertyBool.create("north");
    public static final PropertyBool TRIPWIRE_EAST = PropertyBool.create("east");
    public static final PropertyBool TRIPWIRE_SOUTH = PropertyBool.create("south");
    public static final PropertyBool TRIPWIRE_WEST = PropertyBool.create("west");
    
    public static final PropertyDirection TRIPWIREHOOK_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool TRIPWIREHOOK_POWERED = PropertyBool.create("powered");
    public static final PropertyBool TRIPWIREHOOK_ATTACHED = PropertyBool.create("attached");
    public static final PropertyBool TRIPWIREHOOK_SUSPENDED = PropertyBool.create("suspended");
    
    public static final PropertyBool VINE_UP = PropertyBool.create("up");
    public static final PropertyBool VINE_NORTH = PropertyBool.create("north");
    public static final PropertyBool VINE_EAST = PropertyBool.create("east");
    public static final PropertyBool VINE_SOUTH = PropertyBool.create("south");
    public static final PropertyBool VINE_WEST = PropertyBool.create("west");
    
    // pas celle là
    public static final PropertyBool[] VINE_ALL_FACES = new PropertyBool[] {VINE_UP, VINE_NORTH, VINE_SOUTH, VINE_WEST, VINE_EAST};
    
    public static final PropertyBool WALL_UP = PropertyBool.create("up");
    public static final PropertyBool WALL_NORTH = PropertyBool.create("north");
    public static final PropertyBool WALL_EAST = PropertyBool.create("east");
    public static final PropertyBool WALL_SOUTH = PropertyBool.create("south");
    public static final PropertyBool WALL_WEST = PropertyBool.create("west");
    public static final PropertyEnum<XEnumTypeWall> WALL_VARIANT = PropertyEnum.<XEnumTypeWall>create("variant", XEnumTypeWall.class); // ça oui

    public static final PropertyDirection WALLSIGN_FACING = PropertyDirection.create("facing", XEnumFacing.Plane.HORIZONTAL);
    
    public static final PropertyEnum<XEnumTypePlanks> WOODSLAB_VARIANT = PropertyEnum.<XEnumTypePlanks>create("variant", XEnumTypePlanks.class);

    ////////////////////////////////
    
    public static final Map<Class<? extends Block>, IProperty[]> BLOCK_DEFAULT_PROPERTIES = Maps.newHashMap();
    
    static {
    	put(BlockWoodSlab.class, WOODSLAB_VARIANT);
    	put(BlockSign.class, WALLSIGN_FACING, STANDINGSIGN_ROTATION); //TODO: check
    	put(BlockWall.class, WALL_UP, WALL_NORTH, WALL_EAST, WALL_SOUTH, WALL_WEST, WALL_VARIANT);
    	put(BlockVine.class, VINE_UP, VINE_NORTH, VINE_EAST, VINE_SOUTH, VINE_WEST); 
    	put(BlockTripWireHook.class, TRIPWIREHOOK_FACING, TRIPWIREHOOK_POWERED, TRIPWIREHOOK_ATTACHED, TRIPWIREHOOK_SUSPENDED);
    	put(BlockTripWire.class, TRIPWIRE_POWERED, TRIPWIRE_SUSPENDED, TRIPWIRE_ATTACHED, TRIPWIRE_DISARMED, TRIPWIRE_NORTH, TRIPWIRE_EAST, TRIPWIRE_SOUTH, TRIPWIRE_WEST);
    	put(BlockTrapDoor.class, TRAPDOOR_FACING, TRAPDOOR_OPEN, TRAPDOOR_HALF);
    	put(BlockTorch.class, TORCH_FACING);
    	put(BlockTNT.class, TNT_EXPLODE);
    	put(BlockTallGrass.class, TALLGRASS_TYPE);
    	put(BlockStoneSlab.class, STONESLAB_SEAMLESS, STONESLAB_VARIANT);
    	put(BlockStoneBrick.class, STONEBRICK_VARIANT);
    	put(BlockStone.class, STONE_VARIANT);
    	put(BlockStem.class, STEM_AGE, STEM_FACING);
    	put(BlockStairs.class, STAIRS_FACING, STAIRS_HALF, STAIRS_SHAPE);
    	put(BlockStainedGlassPane.class, STAINEDGLASSPANE_COLOR);
    	put(BlockStainedGlass.class, STAINEDGLASS_COLOR);
    	put(BlockSponge.class, SPONGE_WET);
    	put(BlockSnow.class, SNOW_LAYERS);
    	put(BlockSlab.class, SLAB_HALF); 
    	put(BlockSkull.class, SKULL_FACING, SKULL_NODROP);
    	put(BlockSilverfish.class, SILVERFISH_VARIANT);
    	put(BlockSapling.class, SAPLING_TYPE, SAPLING_STAGE);
    	put(BlockSandStone.class, SANDSTONE_TYPE);
    	put(BlockSand.class, SAND_VARIANT);
    	put(BlockRotatedPillar.class, ROTATEDPILLAR_AXIS); 
    	put(BlockReed.class, REED_AGE);
    	put(BlockRedstoneWire.class, REDSTONEWIRE_NORTH, REDSTONEWIRE_EAST, REDSTONEWIRE_SOUTH, REDSTONEWIRE_WEST, REDSTONEWIRE_POWER);
    	put(BlockRedstoneRepeater.class, REDSTONEREPEATER_LOCKED, REDSTONEREPEATER_DELAY);
    	put(BlockRedstoneComparator.class, REDSTONECOMPARATOR_POWERED, REDSTONECOMPARATOR_MODE);
    	put(BlockRailPowered.class, RAILPOWERED_SHAPE, RAILPOWERED_POWERED);
    	put(BlockRailDetector.class, RAILDETECTOR_SHAPE, RAILDETECTOR_POWERED);
    	put(BlockRail.class, RAIL_SHAPE);
    	put(BlockQuartz.class, QUARTZ_VARIANT);
    	put(BlockPressurePlateWeighted.class, PRESSUREPLATEWEIGHTED_POWER);
    	put(BlockPressurePlate.class, PRESSUREPLATE_POWERED);
    	put(BlockPortal.class, PORTAL_AXIS);
    	put(BlockWood.class, PLANKS_VARIANT);
    	put(BlockPistonMoving.class, PISTONMOVING_FACING, PISTONMOVING_TYPE);
    	put(BlockPistonExtension.class, PISTONEXTENSION_FACING, PISTONEXTENSION_TYPE, PISTONEXTENSION_SHORT);
    	put(BlockPistonBase.class, PISTONBASE_FACING, PISTONBASE_EXTENDED);
    	put(BlockPane.class, PANE_NORTH, PANE_EAST, PANE_SOUTH, PANE_WEST);
    	put(BlockOldLog.class, OLDLOG_VARIANT);
    	put(BlockOldLeaf.class, OLDLEAF_VARIANT);
    	put(BlockNewLog.class, NEWLOG_VARIANT);
    	put(BlockNewLeaf.class, NEWLEAF_VARIANT);
    	put(BlockNetherWart.class, NETHERWART_AGE);
    	put(BlockMycelium.class, MYCELIUM_SNOWY);
    	put(BlockLog.class, LOG_AXIS); // jsp
    	put(BlockLiquid.class, LIQUID_LEVEL); // jsp
    	put(BlockLever.class, LEVER_FACING, LEVER_POWERED);
    	put(BlockLeaves.class, LEAVES_DECAYABLE, LEAVES_CHECK_DECAY); // jsp
    	put(BlockLadder.class, LADDER_FACING);
    	put(BlockJukebox.class, JUKEBOX_HAS_RECORD);
    	put(BlockHugeMushroom.class, HUGEMUSHROOM_VARIANT);
    	put(BlockHopper.class, HOPPER_FACING, HOPPER_ENABLED);
    	put(BlockGrass.class, GRASS_SNOWY);
    	put(BlockFurnace.class, FURNACE_FACING);
    	put(BlockFlowerPot.class, FLOWERPOT_LEGACY_DATA, FLOWERPOT_CONTENTS);
    	put(BlockFlower.class, FLOWER_TYPE); // xtrm help //TODO: check instance
    	put(BlockFire.class, FIRE_AGE, FIRE_FLIP, FIRE_ALT, FIRE_NORTH, FIRE_EAST, FIRE_SOUTH, FIRE_WEST, FIRE_UPPER);
    	put(BlockFenceGate.class, FENCEGATE_OPEN, FENCEGATE_POWERED, FENCEGATE_IN_WALL);
    	put(BlockFence.class, FENCE_NORTH, FENCE_EAST, FENCE_SOUTH, FENCE_WEST);
    	put(BlockFarmland.class, FARMLAND_MOISTURE);
    	put(BlockEndPortalFrame.class, ENDPORTALFRAME_FACING, ENDPORTALFRAME_EYE);
    	put(BlockEnderChest.class, ENDERCHEST_FACING);
    	put(BlockDoublePlant.class, DOUBLEPLANT_VARIANT, DOUBLEPLANT_HALF, DOUBLEPLANT_FACING);
    	put(BlockDoor.class, DOOR_FACING, DOOR_OPEN, DOOR_HINGE, DOOR_POWERED, DOOR_HALF);
    	put(BlockDispenser.class, DISPENSER_FACING, DISPENSER_TRIGGERED);
    	put(BlockDirt.class, DIRT_VARIANT, DIRT_SNOWY);
    	put(BlockDirectional.class, DIRECTIONAL_FACING); 
    	put(BlockDaylightDetector.class, DAYLIGHTDETECTOR_POWER);
    	put(BlockCrops.class, Crops_AGE);
    	put(BlockCommandBlock.class, COMMANDBLOCK_TRIGGERED);
    	put(BlockColored.class, COLORED_COLOR);
    	put(BlockCocoa.class, COCOA_AGE);
    	put(BlockChest.class, CHEST_FACING);
    	put(BlockCauldron.class, CAULDRON_LEVEL);
    	put(BlockCarpet.class, CARPET_COLOR);
    	put(BlockCake.class, CAKE_BITES);
    	put(BlockCactus.class, CACTUS_AGE);
    	put(BlockButton.class, BUTTON_FACING, BUTTON_POWERED);
    	put(BlockBrewingStand.class, BREWINGSTAND_HAS_BOTTLE[0], BREWINGSTAND_HAS_BOTTLE[1], BREWINGSTAND_HAS_BOTTLE[2]); 
    	put(BlockBed.class, BED_PART, BED_OCCUPIED);
    	// put(BlockBanner.class, BANNER_FACING, BANNER_ROTATION);
    	put(BlockAnvil.class, ANVIL_FACING, ANVIL_DAMAGE);
    }
    
    private static void put(Class<? extends Block> block, IProperty<?>... properties) {
    	BLOCK_DEFAULT_PROPERTIES.put(block, properties);
    }
	
    public static IProperty[] getDefaultProperties(Block block) {
    	List<IProperty> properties = new ArrayList<>();
    	Class<?> clazz = block.getClass();
    	while(clazz != Block.class) {
    		Collections.addAll(properties, BLOCK_DEFAULT_PROPERTIES.getOrDefault(clazz, new IProperty[0]));
    		clazz = clazz.getSuperclass();
    	}
    	return properties.toArray(new IProperty[properties.size()]);
    }
}

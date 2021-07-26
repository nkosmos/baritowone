package baritonex.utils.state.serialization;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.common.collect.Maps;

import baritone.api.utils.BetterBlockPos;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.impl.SAnvil;
import baritonex.utils.state.serialization.impl.SBed;
import baritonex.utils.state.serialization.impl.SBrewingStand;
import baritonex.utils.state.serialization.impl.SButton;
import baritonex.utils.state.serialization.impl.SCactus;
import baritonex.utils.state.serialization.impl.SCake;
import baritonex.utils.state.serialization.impl.SCarpet;
import baritonex.utils.state.serialization.impl.SCauldron;
import baritonex.utils.state.serialization.impl.SChest;
import baritonex.utils.state.serialization.impl.SCocoa;
import baritonex.utils.state.serialization.impl.SColored;
import baritonex.utils.state.serialization.impl.SCommandBlock;
import baritonex.utils.state.serialization.impl.SCrops;
import baritonex.utils.state.serialization.impl.SDaylightDetector;
import baritonex.utils.state.serialization.impl.SDirt;
import baritonex.utils.state.serialization.impl.SDispenser;
import baritonex.utils.state.serialization.impl.SDoor;
import baritonex.utils.state.serialization.impl.SDoublePlant;
import baritonex.utils.state.serialization.impl.SEndPortalFrame;
import baritonex.utils.state.serialization.impl.SEnderChest;
import baritonex.utils.state.serialization.impl.SFarmland;
import baritonex.utils.state.serialization.impl.SFence;
import baritonex.utils.state.serialization.impl.SFenceGate;
import baritonex.utils.state.serialization.impl.SFire;
import baritonex.utils.state.serialization.impl.SFlower;
import baritonex.utils.state.serialization.impl.SFlowerPot;
import baritonex.utils.state.serialization.impl.SFurnace;
import baritonex.utils.state.serialization.impl.SGrass;
import baritonex.utils.state.serialization.impl.SHopper;
import baritonex.utils.state.serialization.impl.SHugeMushroom;
import baritonex.utils.state.serialization.impl.SJukebox;
import baritonex.utils.state.serialization.impl.SLadder;
import baritonex.utils.state.serialization.impl.SLever;
import baritonex.utils.state.serialization.impl.SLiquid;
import baritonex.utils.state.serialization.impl.SMycelium;
import baritonex.utils.state.serialization.impl.SNetherWart;
import baritonex.utils.state.serialization.impl.SNewLeaf;
import baritonex.utils.state.serialization.impl.SNewLog;
import baritonex.utils.state.serialization.impl.SOldLeaf;
import baritonex.utils.state.serialization.impl.SOldLog;
import baritonex.utils.state.serialization.impl.SPane;
import baritonex.utils.state.serialization.impl.SPistonBase;
import baritonex.utils.state.serialization.impl.SPlanks;
import baritonex.utils.state.serialization.impl.SPortal;
import baritonex.utils.state.serialization.impl.SPressurePlate;
import baritonex.utils.state.serialization.impl.SPressurePlateWeighted;
import baritonex.utils.state.serialization.impl.SPumpkin;
import baritonex.utils.state.serialization.impl.SQuartz;
import baritonex.utils.state.serialization.impl.SRail;
import baritonex.utils.state.serialization.impl.SRailDetector;
import baritonex.utils.state.serialization.impl.SRailPowered;
import baritonex.utils.state.serialization.impl.SRedstoneComparator;
import baritonex.utils.state.serialization.impl.SRedstoneRepeater;
import baritonex.utils.state.serialization.impl.SRedstoneWire;
import baritonex.utils.state.serialization.impl.SReed;
import baritonex.utils.state.serialization.impl.SSandstone;
import baritonex.utils.state.serialization.impl.SSapling;
import baritonex.utils.state.serialization.impl.SSign;
import baritonex.utils.state.serialization.impl.SSilverfish;
import baritonex.utils.state.serialization.impl.SSkull;
import baritonex.utils.state.serialization.impl.SSnow;
import baritonex.utils.state.serialization.impl.SStainedGlass;
import baritonex.utils.state.serialization.impl.SStainedGlassPane;
import baritonex.utils.state.serialization.impl.SStairs;
import baritonex.utils.state.serialization.impl.SStem;
import baritonex.utils.state.serialization.impl.SStone;
import baritonex.utils.state.serialization.impl.SStoneSlab;
import baritonex.utils.state.serialization.impl.SStonebrick;
import baritonex.utils.state.serialization.impl.STNT;
import baritonex.utils.state.serialization.impl.STallGrass;
import baritonex.utils.state.serialization.impl.STorch;
import baritonex.utils.state.serialization.impl.STrapDoor;
import baritonex.utils.state.serialization.impl.STripWire;
import baritonex.utils.state.serialization.impl.STripWireHook;
import baritonex.utils.state.serialization.impl.SVine;
import baritonex.utils.state.serialization.impl.SWall;
import baritonex.utils.state.serialization.impl.SWoodSlab;
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
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPistonBase;
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
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSnow;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;

public class XBlockStateSerializer {
	
	public static final Map<Class<? extends Block>, StateSerializer> serializersInstances = Maps.newHashMap();
	public static final Map<Class<? extends Block>, Class<? extends StateSerializer>> serializers = Maps.newHashMap();
	
	public static final Map<Block, IBlockState> defaultStates = Maps.newHashMap();
	public static final Map<Block, BlockState> idkStates = Maps.newHashMap();
	
	public static IBlockState getStateFromMeta(Block block, int metadata) {
		StateSerializer serializer = getSerializer(block);
		return serializer.getStateFromMeta(metadata);
	}
	
	public static int getMetaFromState(IBlockState state) {
		Block block = state.getBlock();
		StateSerializer serializer = getSerializer(block);
		return serializer.getMetaFromState(state);
	}
	
	public static StateSerializer getSerializer(Block block) {		
		Class<?> clazz = block.getClass();
		if(serializersInstances.containsKey(clazz)) {
			return serializersInstances.get(clazz);
		}
		
		BlockState blockState = getBlockStateButItsNotActuallyABlockStateSoAFakeBlockStateIGuess(block);
		
		while(clazz != Block.class) {
			if(serializers.containsKey(clazz)) {
				Class<? extends StateSerializer> klass = serializers.get(clazz);
				try {
					StateSerializer serializer = klass.getConstructor(BlockState.class).newInstance(blockState);
					serializersInstances.put(block.getClass(), serializer);
					return serializer;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					System.out.println("fucked up mate");
					System.out.println(block.getClass() + " / " + klass);
				}
				break;
			}
			
			clazz = clazz.getSuperclass();
		}
		
		return new StateSerializer(blockState) {
			@Override
			public IBlockState getStateFromMeta(int meta) {
				return this.getDefaultState();
			}
			
			@Override
			public int getMetaFromState(IBlockState state) {
				return 0;
			}
			
			@Override
			public IBlockState defineDefaultState() {
				return this.blockState.getBaseState();
			}
		};
	}
	
	public static IBlockState getStateFromWorld(IBlockAccess access, int x, int y, int z) {
		return getStateFromMeta(access.getBlock(x, y, z), access.getBlockMetadata(x, y, z));
	}
	
	public static IBlockState getStateFromWorld(IBlockAccess access, BetterBlockPos bp) {
		return getStateFromWorld(access, bp.x, bp.y, bp.z);
	}
	
	public static IBlockState getStateFromChunk(Chunk access, int x, int y, int z) {
		return getStateFromMeta(access.getBlock(x & 15, y, z & 15), access.getBlockMetadata(x & 15, y, z & 15));
	}
	
	public static IBlockState getStateFromChunk(Chunk access, BetterBlockPos bp) {
		return getStateFromChunk(access, bp.x, bp.y, bp.z);
	}
	
	public static IBlockState getBlockState(Block block) {
		if(!defaultStates.containsKey(block)) {
			BlockState state = getBlockStateButItsNotActuallyABlockStateSoAFakeBlockStateIGuess(block);
			idkStates.put(block, state);
			defaultStates.put(block, state.getBaseState());
		}
		return defaultStates.get(block);
	}
	
	public static BlockState getBlockStateButItsNotActuallyABlockStateSoAFakeBlockStateIGuess(Block block) {
		if(!idkStates.containsKey(block)) {
			BlockState state = new BlockState(block, Properties.getDefaultProperties(block));
			idkStates.put(block, state);
			return state;
		}
		return idkStates.get(block);
	}
	
	static {
		serializers.put(BlockAnvil.class, SAnvil.class);
		serializers.put(BlockBed.class, SBed.class);
		serializers.put(BlockBrewingStand.class, SBrewingStand.class);
		serializers.put(BlockButton.class, SButton.class);
		serializers.put(BlockCactus.class, SCactus.class);
		serializers.put(BlockCake.class, SCake.class);
		serializers.put(BlockCarpet.class, SCarpet.class);
		serializers.put(BlockCauldron.class, SCauldron.class);
		serializers.put(BlockChest.class, SChest.class);
		serializers.put(BlockCocoa.class, SCocoa.class);
		serializers.put(BlockColored.class, SColored.class);
		serializers.put(BlockCommandBlock.class, SCommandBlock.class);
		serializers.put(BlockCrops.class, SCrops.class);
		serializers.put(BlockDaylightDetector.class, SDaylightDetector.class);
		serializers.put(BlockDirt.class, SDirt.class);
		serializers.put(BlockDispenser.class, SDispenser.class);
		serializers.put(BlockDoor.class, SDoor.class);
		serializers.put(BlockDoublePlant.class, SDoublePlant.class);
		serializers.put(BlockEnderChest.class, SEnderChest.class);
		serializers.put(BlockEndPortalFrame.class, SEndPortalFrame.class);
		serializers.put(BlockFarmland.class, SFarmland.class);
		serializers.put(BlockFence.class, SFence.class);
		serializers.put(BlockFenceGate.class, SFenceGate.class);
		serializers.put(BlockFire.class, SFire.class);
		serializers.put(BlockFlower.class, SFlower.class);
		serializers.put(BlockFlowerPot.class, SFlowerPot.class);
		serializers.put(BlockFurnace.class, SFurnace.class);
		serializers.put(BlockGrass.class, SGrass.class);
		serializers.put(BlockHopper.class, SHopper.class);
		serializers.put(BlockHugeMushroom.class, SHugeMushroom.class);
		serializers.put(BlockJukebox.class, SJukebox.class);
		serializers.put(BlockLadder.class, SLadder.class);
		serializers.put(BlockLever.class, SLever.class);
		serializers.put(BlockLiquid.class, SLiquid.class);
		serializers.put(BlockMycelium.class,SMycelium.class);
		serializers.put(BlockNetherWart.class, SNetherWart.class);
		serializers.put(BlockNewLeaf.class, SNewLeaf.class);
		serializers.put(BlockNewLog.class, SNewLog.class);
		serializers.put(BlockOldLeaf.class, SOldLeaf.class);
		serializers.put(BlockOldLog.class, SOldLog.class);
		serializers.put(BlockPane.class, SPane.class);
		serializers.put(BlockPistonBase.class, SPistonBase.class);
		serializers.put(BlockPistonMoving.class, SPistonBase.class);
		serializers.put(BlockWood.class, SPlanks.class); // clc
		serializers.put(BlockPortal.class, SPortal.class);
		serializers.put(BlockPressurePlate.class, SPressurePlate.class);
		serializers.put(BlockPressurePlateWeighted.class, SPressurePlateWeighted.class);
		serializers.put(BlockDirectional.class, SPumpkin.class); // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		serializers.put(BlockQuartz.class, SQuartz.class);
		serializers.put(BlockRail.class, SRail.class);
		serializers.put(BlockRailDetector.class, SRailDetector.class);
		serializers.put(BlockRailPowered.class, SRailPowered.class);
		serializers.put(BlockRedstoneComparator.class, SRedstoneComparator.class);
		serializers.put(BlockRedstoneRepeater.class, SRedstoneRepeater.class);
		serializers.put(BlockRedstoneWire.class, SRedstoneWire.class);
		serializers.put(BlockReed.class, SReed.class);
		serializers.put(BlockSandStone.class, SSandstone.class);
		serializers.put(BlockSapling.class, SSapling.class);
		serializers.put(BlockSign.class, SSign.class);
		serializers.put(BlockSilverfish.class, SSilverfish.class);
		serializers.put(BlockSkull.class, SSkull.class);
		serializers.put(BlockSnow.class, SSnow.class);
		serializers.put(BlockStainedGlass.class, SStainedGlass.class);
		serializers.put(BlockStainedGlassPane.class, SStainedGlassPane.class);
		serializers.put(BlockStairs.class, SStairs.class);
		serializers.put(BlockStem.class, SStem.class);
		serializers.put(BlockStone.class, SStone.class);
		serializers.put(BlockStoneBrick.class, SStonebrick.class);
		serializers.put(BlockStoneSlab.class, SStoneSlab.class);
		serializers.put(BlockTallGrass.class, STallGrass.class);
		serializers.put(BlockTNT.class, STNT.class);
		serializers.put(BlockTorch.class, STorch.class);
		serializers.put(BlockTrapDoor.class, STrapDoor.class);
		serializers.put(BlockTripWire.class, STripWire.class);
		serializers.put(BlockTripWireHook.class, STripWireHook.class);
		serializers.put(BlockVine.class, SVine.class);
		serializers.put(BlockWall.class, SWall.class);
		serializers.put(BlockWoodSlab.class, SWoodSlab.class);		
	}
}

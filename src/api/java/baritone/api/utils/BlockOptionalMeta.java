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

package baritone.api.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import baritone.api.utils.accessor.IItemStack;
import baritonex.utils.data.XEnumAxis;
import baritonex.utils.data.XEnumBlockHalfSlab;
import baritonex.utils.data.XEnumBlockHalfTrapdoor;
import baritonex.utils.data.XEnumDoorHalf;
import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumHalfStairs;
import baritonex.utils.data.XEnumHingePosition;
import baritonex.utils.data.XEnumOrientation;
import baritonex.utils.data.XEnumPartType;
import baritonex.utils.data.XEnumRailDirection;
import baritonex.utils.data.XEnumShapeStairs;
import baritonex.utils.data.XEnumTypeQuartz;
import baritonex.utils.property.IProperty;
import baritonex.utils.property.Properties;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public final class BlockOptionalMeta {

    private final Block block;
    private final int meta;
    private final boolean noMeta;
    private final Set<IBlockState> blockstates;
    private final ImmutableSet<Integer> stateHashes;
    private final ImmutableSet<Integer> stackHashes;
    private static final Pattern pattern = Pattern.compile("^(.+?)(?::(\\d+))?$");
    private static final Map<Object, Object> normalizations;

    public BlockOptionalMeta(@Nonnull Block block, @Nullable Integer meta) {
        this.block = block;
        this.noMeta = meta == null;
        this.meta = noMeta ? 0 : meta;
        this.blockstates = getStates(block, meta);
        this.stateHashes = getStateHashes(blockstates);
        this.stackHashes = getStackHashes(blockstates);
    }

    public BlockOptionalMeta(@Nonnull Block block) {
        this(block, null);
    }

    public BlockOptionalMeta(@Nonnull String selector) {
        Matcher matcher = pattern.matcher(selector);

        if (!matcher.find()) {
            throw new IllegalArgumentException("invalid block selector");
        }

        MatchResult matchResult = matcher.toMatchResult();
        noMeta = matchResult.group(2) == null;

        String id = BlockUtils.ensureNamespaced(matchResult.group(1));

        if (!Block.blockRegistry.containsKey(id)) {
            throw new IllegalArgumentException("Invalid block ID");
        }

        block = (Block)Block.blockRegistry.getObject(id);
        meta = noMeta ? 0 : Integer.parseInt(matchResult.group(2));
        blockstates = getStates(block, getMeta());
        stateHashes = getStateHashes(blockstates);
        stackHashes = getStackHashes(blockstates);
    }

    static {
        Map<Object, Object> _normalizations = new HashMap<>();
        Consumer<Enum> put = instance -> _normalizations.put(instance.getClass(), instance);
        put.accept(XEnumFacing.NORTH); // TODO: Properties > 
        put.accept(XEnumFacing.Axis.Y);
        put.accept(XEnumAxis.Y);
        put.accept(XEnumHalfStairs.BOTTOM);
        put.accept(XEnumShapeStairs.STRAIGHT);
        put.accept(XEnumOrientation.DOWN_X);
        put.accept(XEnumBlockHalfTrapdoor.LOWER);
        put.accept(XEnumBlockHalfSlab.BOTTOM);
        put.accept(XEnumDoorHalf.LOWER);
        put.accept(XEnumHingePosition.LEFT);
        put.accept(XEnumPartType.HEAD);
        put.accept(XEnumRailDirection.NORTH_SOUTH);
        _normalizations.put(Properties.BED_OCCUPIED, false);
        _normalizations.put(Properties.BREWINGSTAND_HAS_BOTTLE[0], false);
        _normalizations.put(Properties.BREWINGSTAND_HAS_BOTTLE[1], false);
        _normalizations.put(Properties.BREWINGSTAND_HAS_BOTTLE[2], false);
        _normalizations.put(Properties.BUTTON_POWERED, false);
        // _normalizations.put(BlockCactus.AGE, 0);
        // _normalizations.put(BlockCauldron.LEVEL, 0);
        // _normalizations.put(BlockChorusFlower.AGE, 0);
        // _normalizations.put(BlockCocoa.AGE, 0);
        // _normalizations.put(BlockCrops.AGE, 0);
        _normalizations.put(Properties.DIRT_SNOWY, false);
        _normalizations.put(Properties.DOOR_OPEN, false);
        _normalizations.put(Properties.DOOR_POWERED, false);
        // _normalizations.put(BlockFarmland.MOISTURE, 0);
        _normalizations.put(Properties.FENCE_NORTH, false);
        _normalizations.put(Properties.FENCE_EAST, false);
        _normalizations.put(Properties.FENCE_WEST, false);
        _normalizations.put(Properties.FENCE_SOUTH, false);
        // _normalizations.put(BlockFenceGate.POWERED, false);
        // _normalizations.put(BlockFenceGate.IN_WALL, false);
        _normalizations.put(Properties.FIRE_AGE, 0);
        _normalizations.put(Properties.FIRE_NORTH, false);
        _normalizations.put(Properties.FIRE_EAST, false);
        _normalizations.put(Properties.FIRE_SOUTH, false);
        _normalizations.put(Properties.FIRE_WEST, false);
        _normalizations.put(Properties.FIRE_UPPER, false);
        // _normalizations.put(BlockFrostedIce.AGE, 0);
        _normalizations.put(Properties.GRASS_SNOWY, false);
        // _normalizations.put(BlockHopper.ENABLED, true);
        // _normalizations.put(BlockLever.POWERED, false);
        // _normalizations.put(BlockLiquid.LEVEL, 0);
        // _normalizations.put(BlockMycelium.SNOWY, false);
        // _normalizations.put(BlockNetherWart.AGE, false);
        _normalizations.put(Properties.LEAVES_CHECK_DECAY, false);
        // _normalizations.put(BlockLeaves.DECAYABLE, false);
        // _normalizations.put(BlockObserver.POWERED, false);
        _normalizations.put(Properties.PANE_NORTH, false);
        _normalizations.put(Properties.PANE_EAST, false);
        _normalizations.put(Properties.PANE_WEST, false);
        _normalizations.put(Properties.PANE_SOUTH, false);
        // _normalizations.put(BlockPistonBase.EXTENDED, false);
        // _normalizations.put(BlockPressurePlate.POWERED, false);
        // _normalizations.put(BlockPressurePlateWeighted.POWER, false);
        _normalizations.put(XEnumTypeQuartz.LINES_X, XEnumTypeQuartz.LINES_Y);
        _normalizations.put(XEnumTypeQuartz.LINES_Z, XEnumTypeQuartz.LINES_Y);
        // _normalizations.put(BlockRailDetector.POWERED, false);
        // _normalizations.put(BlockRailPowered.POWERED, false);
        _normalizations.put(Properties.REDSTONEWIRE_NORTH, false);
        _normalizations.put(Properties.REDSTONEWIRE_EAST, false);
        _normalizations.put(Properties.REDSTONEWIRE_SOUTH, false);
        _normalizations.put(Properties.REDSTONEWIRE_WEST, false);
        // _normalizations.put(BlockReed.AGE, false);
        _normalizations.put(Properties.SAPLING_STAGE, 0); 
        _normalizations.put(Properties.SKULL_NODROP, false); 
//        _normalizations.put(BlockStandingSign.ROTATION, 0);
        _normalizations.put(Properties.STEM_AGE, 0); 
        _normalizations.put(Properties.TRIPWIRE_NORTH, false);
        _normalizations.put(Properties.TRIPWIRE_EAST, false);
        _normalizations.put(Properties.TRIPWIRE_WEST, false);
        _normalizations.put(Properties.TRIPWIRE_SOUTH, false);
        _normalizations.put(Properties.VINE_NORTH, false);
        _normalizations.put(Properties.VINE_EAST, false);
        _normalizations.put(Properties.VINE_SOUTH, false);
        _normalizations.put(Properties.VINE_WEST, false);
        _normalizations.put(Properties.VINE_UP, false);
        _normalizations.put(Properties.WALL_UP, false);
        _normalizations.put(Properties.WALL_NORTH, false);
        _normalizations.put(Properties.WALL_EAST, false);
        _normalizations.put(Properties.WALL_WEST, false);
        _normalizations.put(Properties.WALL_SOUTH, false);
        normalizations = Collections.unmodifiableMap(_normalizations);
    }

    public static <C extends Comparable<C>, P extends IProperty<C>> P castToIProperty(Object value) {
        //noinspection unchecked
        return (P) value;
    }

    public static <C extends Comparable<C>, P extends IProperty<C>> C castToIPropertyValue(P iproperty, Object value) {
        //noinspection unchecked
        return (C) value;
    }

    /**
     * Normalizes the specified blockstate by setting meta-affecting properties which
     * are not being targeted by the meta parameter to their default values.
     * <p>
     * For example, block variant/color is the primary target for the meta value, so properties
     * such as rotation/facing direction will be set to default values in order to nullify
     * the effect that they have on the state's meta value.
     *
     * @param state The state to normalize
     * @return The normalized block state
     */
    public static IBlockState normalize(IBlockState state) {
        IBlockState newState = state;

        for (IProperty<?> property : state.getProperties().keySet()) {
            Class<?> valueClass = property.getValueClass();
            if (normalizations.containsKey(property)) {
                try {
                    newState = newState.withProperty(
                            castToIProperty(property),
                            castToIPropertyValue(property, normalizations.get(property))
                    );
                } catch (IllegalArgumentException ignored) {}
            } else if (normalizations.containsKey(state.getValue(property))) {
                try {
                    newState = newState.withProperty(
                            castToIProperty(property),
                            castToIPropertyValue(property, normalizations.get(state.getValue(property)))
                    );
                } catch (IllegalArgumentException ignored) {}
            } else if (normalizations.containsKey(valueClass)) {
                try {
                    newState = newState.withProperty(
                            castToIProperty(property),
                            castToIPropertyValue(property, normalizations.get(valueClass))
                    );
                } catch (IllegalArgumentException ignored) {}
            }
        }

        return newState;
    }

    /**
     * Evaluate the target meta value for the specified state. The target meta value is
     * most often that which is influenced by the variant/color property of the block state.
     *
     * @param state The state to check
     * @return The target meta of the state
     * @see #normalize(IBlockState)
     */
    public static int stateMeta(IBlockState state) {
        return XBlockStateSerializer.getMetaFromState(normalize(state));
    }

    private static Set<IBlockState> getStates(@Nonnull Block block, @Nullable Integer meta) {
        return XBlockStateSerializer.getBlockStateButItsNotActuallyABlockStateSoAFakeBlockStateIGuess(block)
        		.getValidStates()
        		.stream()
                .filter(blockstate -> meta == null || stateMeta(blockstate) == meta)
                .collect(Collectors.toSet());
    }

    private static ImmutableSet<Integer> getStateHashes(Set<IBlockState> blockstates) {
        return ImmutableSet.copyOf(
                blockstates.stream()
                        .map(IBlockState::hashCode)
                        .toArray(Integer[]::new)
        );
    }

    private static ImmutableSet<Integer> getStackHashes(Set<IBlockState> blockstates) {
        //noinspection ConstantConditions
        return ImmutableSet.copyOf(
                blockstates.stream()
                        .map(state -> {
                        	int meta = XBlockStateSerializer.getMetaFromState(state);
                        	return new ItemStack(
                                    state.getBlock().getItemDropped(meta, new Random(), 0),
                                    state.getBlock().damageDropped(meta)
                            );
                        })
                        .map(stack -> ((IItemStack) (Object) stack).getBaritoneHash())
                        .toArray(Integer[]::new)
        );
    }

    public Block getBlock() {
        return block;
    }

    public Integer getMeta() {
        return noMeta ? null : meta;
    }

    public boolean matches(@Nonnull Block block) {
        return block == this.block;
    }

    public boolean matches(@Nonnull IBlockState blockstate) {
        Block block = blockstate.getBlock();
        return block == this.block && stateHashes.contains(blockstate.hashCode());
    }

    public boolean matches(ItemStack stack) {
        //noinspection ConstantConditions
        int hash = ((IItemStack) (Object) stack).getBaritoneHash();

        if (noMeta) {
            hash -= stack.getMetadata();
        }

        return stackHashes.contains(hash);
    }

    @Override
    public String toString() {
        return String.format("BlockOptionalMeta{block=%s,meta=%s}", block, getMeta());
    }

    public static IBlockState blockStateFromStack(ItemStack stack) {
        //noinspection deprecation
        return XBlockStateSerializer.getStateFromMeta(Block.getBlockFromItem(stack.getItem()), stack.getMetadata());
    }

    public IBlockState getAnyBlockState() {
        if (blockstates.size() > 0) {
            return blockstates.iterator().next();
        }

        return null;
    }
}

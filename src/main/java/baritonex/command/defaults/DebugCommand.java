package baritonex.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.Helper;
import baritone.cache.WorldScanner;
import baritonex.utils.Debugger;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;

public class DebugCommand extends Command {

	public DebugCommand(IBaritone baritone) {
		super(baritone, "debugLmfao");
	}

	@Override
	public void execute(String label, IArgConsumer args) throws CommandException {
		Debugger.INSTANCE.start("debugLmfao");
		List<BlockPos> bp = WorldScanner.INSTANCE.scanChunkRadius(baritone.getPlayerContext(), new BlockOptionalMetaLookup(Blocks.diamond_ore), 64, 10, 10);
		
		for(BlockPos b : bp) {
			Helper.HELPER.logDirect(b + "");
		}
		Debugger.INSTANCE.end();
	}

	@Override
	public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
		return Stream.of("lmfao", "gay", "fuckOff", "nigger");
	}

	@Override
	public String getShortDesc() {
		return "ur mom gay lmfao";
	}

	@Override
	public List<String> getLongDesc() {
		return Arrays.asList(
                "Suck my fat cock.",
                "",
                "This can be useful in situations where you're under the fucking desk.",
                "",
                "Usage:",
                "> nigger"
        );
	}

}

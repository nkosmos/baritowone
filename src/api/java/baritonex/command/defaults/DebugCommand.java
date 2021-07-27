package baritonex.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;

public class DebugCommand extends Command {

    public DebugCommand(IBaritone baritone) {
        super(baritone, "debuwug");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        
        
        logDirect("You are running Baritowone >w<");
        logDirect("version? idk <3");
        logDirect("" + (ctx.player().boundingBox.maxX - ctx.player().boundingBox.minX));
        logDirect(ctx.player().posY + " / " + ctx.player().boundingBox.minY);
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Debug";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList(
                "UwU.",
                "",
                "Usage:",
                "> debuwug - COCK"
        );
    }
}

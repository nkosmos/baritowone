package baritonex.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;
import baritone.api.utils.IPlayerContext;

public class DebugCommand extends Command {

    public DebugCommand(IBaritone baritone) {
        super(baritone, "debuwug");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        
        ctx.player().setSneaking(true);
        logDirect("You are running Baritowone >w<");
        logDirect("version? idk <3");
        logDirect("" + (ctx.player().boundingBox.maxX - ctx.player().boundingBox.minX));
        logDirect(ctx.player().posY + " / " + ctx.player().boundingBox.minY);
        logDirect("" + ctx.player().getEyeHeight() + " / " + IPlayerContext.eyeHeight(false));
        logDirect((ctx.player().posY + ctx.player().getEyeHeight()) + " / " + (ctx.player().boundingBox.minY + IPlayerContext.eyeHeight(false)));
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

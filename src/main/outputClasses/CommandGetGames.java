package main.outputClasses;

public class CommandGetGames {

    private final String command;

    private final int output;

    public CommandGetGames(final String command, final int output) {
        this.command = command;
        this.output = output;
    }

    public final String getCommand() {
        return command;
    }

    public final int getOutput() {
        return output;
    }

}

package main.outputClasses;

public class CommandGetCardAtPosError {

    private final String command;

    private final String output;

    private final int x;

    private final int y;

    public CommandGetCardAtPosError(final String command, final String output,
                                    final int x, final int y) {
        this.command = command;
        this.output = output;
        this.x = x;
        this.y = y;
    }

    public final String getCommand() {
        return command;
    }

    public final String getOutput() {
        return output;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

}

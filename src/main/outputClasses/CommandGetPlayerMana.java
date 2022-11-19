package main.outputClasses;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "command", "PlayerIdx", "output"})
public class CommandGetPlayerMana {

    private final String command;

    private final int playerIdx;

    private final int output;

    public CommandGetPlayerMana(final String command, final int playerIdx,
                                final int output) {
        this.command = command;
        this.playerIdx = playerIdx;
        this.output = output;
    }

    public final String getCommand() {
        return command;
    }

    public final int getPlayerIdx() {
        return playerIdx;
    }

    public final int getOutput() {
        return output;
    }

}

package main.outputClasses;

public class CommandPlaceCard {

    private final String command;

    private final int handIdx;

    private final String error;

    public CommandPlaceCard(final String command, final int handIdx, final String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.error = error;
    }

    public final String getCommand() {
        return command;
    }

    public final int getHandIdx() {
        return handIdx;
    }

    public final String getError() {
        return error;
    }

}

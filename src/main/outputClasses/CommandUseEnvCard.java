package main.outputClasses;

public class CommandUseEnvCard {

    private final String command;

    private final int handIdx;

    private final int affectedRow;

    private final String error;

    public CommandUseEnvCard(final String command, final int handIdx,
                             final int affectedRow, final String error) {
        this.command = command;
        this.handIdx = handIdx;
        this.affectedRow = affectedRow;
        this.error = error;
    }

    public final String getCommand() {
        return command;
    }

    public final int getHandIdx() {
        return handIdx;
    }

    public final int getAffectedRow() {
        return affectedRow;
    }

    public final String getError() {
        return error;
    }

}

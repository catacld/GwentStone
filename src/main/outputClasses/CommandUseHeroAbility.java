package main.outputClasses;

public class CommandUseHeroAbility {

    private final String command;

    private final int affectedRow;

    private final String error;

    public CommandUseHeroAbility(final String command, final int affectedRow, final String error) {
        this.command = command;
        this.affectedRow = affectedRow;
        this.error = error;
    }

    public final String getCommand() {
        return command;
    }

    public final int getAffectedRow() {
        return affectedRow;
    }

    public final String getError() {
        return error;
    }

}

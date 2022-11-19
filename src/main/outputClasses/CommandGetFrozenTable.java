package main.outputClasses;

import main.cards.MinionCard;

import java.util.ArrayList;

public class CommandGetFrozenTable {

    private final String command;

    private final ArrayList<MinionCard> output;

    public CommandGetFrozenTable(final String command, final ArrayList<MinionCard> output) {
        this.command = command;
        this.output = output;
    }

    public final String getCommand() {
        return command;
    }

    public final ArrayList<MinionCard> getOutput() {
        return output;
    }

}

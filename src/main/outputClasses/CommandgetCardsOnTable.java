package main.outputClasses;

import main.cards.Card;

import java.util.ArrayList;

public class CommandgetCardsOnTable {

    private final String command;
    private final ArrayList<ArrayList<Card>> output;

    public CommandgetCardsOnTable(final String command, final ArrayList output) {
        this.command = command;
        this.output = output;
    }

    public final String getCommand() {
        return command;
    }

    public final ArrayList<ArrayList<Card>> getOutput() {
        return output;
    }

}

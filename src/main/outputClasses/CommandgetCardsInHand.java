package main.outputClasses;

import main.cards.Card;

import java.util.ArrayList;

public class CommandgetCardsInHand {

    private final String command;

    private final int playerIdx;

    private final ArrayList<Card> output;

    public CommandgetCardsInHand(final String command, final int playerIdx,
                                 final ArrayList<Card> output) {
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

    public final ArrayList<Card> getOutput() {
        return output;
    }

}

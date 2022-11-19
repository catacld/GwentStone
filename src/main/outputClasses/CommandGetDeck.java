package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.cards.Card;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandGetDeck {

    private final String command;

    private final ArrayList<Card> output;
    private final Integer playerIdx;

    public CommandGetDeck(final String command, final ArrayList<Card> deck,
                          final Integer playerIdx) {
        this.command = command;
        this.output = deck;
        this.playerIdx = playerIdx;
    }

    public final String getCommand() {
        return command;
    }

    public final ArrayList<Card> getOutput() {
        return output;
    }

    public final Integer getPlayerIdx() {
        return playerIdx;
    }

}

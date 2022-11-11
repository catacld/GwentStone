package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.cards.Card;
import main.cards.HeroCard;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class commandGetDeck {

    private String command;

    private ArrayList<Card> output = null;
    private Integer playerIdx = null;

    public commandGetDeck(String command, ArrayList<Card> deck, Integer playerIdx) {
        this.command = command;
        this.output = deck;
        this.playerIdx = playerIdx;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<Card> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<Card> output) {
        this.output = output;
    }

    public Integer getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(Integer playerIdx) {
        this.playerIdx = playerIdx;
    }
}

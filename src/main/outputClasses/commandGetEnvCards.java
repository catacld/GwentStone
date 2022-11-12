package main.outputClasses;

import main.cards.Card;

import java.util.ArrayList;

public class commandGetEnvCards {

    String command;

    int playerIdx;

    ArrayList<Card> output;

    public commandGetEnvCards(String command, int playerIdx, ArrayList<Card> output) {
        this.command = command;
        this.playerIdx = playerIdx;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public ArrayList<Card> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<Card> output) {
        this.output = output;
    }
}

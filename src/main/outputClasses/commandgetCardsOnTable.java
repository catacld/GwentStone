package main.outputClasses;

import main.cards.Card;

import java.util.ArrayList;

public class commandgetCardsOnTable {

    private String command;
    private ArrayList<ArrayList<Card>> output;

    public commandgetCardsOnTable(String command, ArrayList output) {
        this.command = command;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<ArrayList<Card>> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<ArrayList<Card>> output) {
        this.output = output;
    }
}

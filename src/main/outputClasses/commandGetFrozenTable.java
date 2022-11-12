package main.outputClasses;

import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class commandGetFrozenTable {

    private String command;

    private ArrayList<MinionCard> output;

    public commandGetFrozenTable(String command, ArrayList<MinionCard> output) {
        this.command = command;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<MinionCard> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<MinionCard> output) {
        this.output = output;
    }
}

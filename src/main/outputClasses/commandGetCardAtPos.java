package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.cards.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class commandGetCardAtPos {

    private String command;

    private Card output;

    int x;

    int y;



    public commandGetCardAtPos(String command,Card output, int x, int y) {
        this.command = command;
        this.output = output;
        this.x = x;
        this.y = y;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Card getOutput() {
        return output;
    }

    public void setOutput(Card output) {
        this.output = output;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

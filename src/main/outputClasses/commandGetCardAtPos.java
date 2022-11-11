package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.cards.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class commandGetCardAtPos {

    private String command;

    private Card output;

    private String error;


    public commandGetCardAtPos(String command, Card output, String error) {
        this.command = command;
        this.output = output;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

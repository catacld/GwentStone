package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.cards.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandGetCardAtPos {

    private final String command;

    private final Card output;

    private final int x;

    private final int y;



    public CommandGetCardAtPos(final String command, final Card output,
                               final int x, final int y) {
        this.command = command;
        this.output = output;
        this.x = x;
        this.y = y;
    }

    public final String getCommand() {
        return command;
    }

    public final Card getOutput() {
        return output;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

}

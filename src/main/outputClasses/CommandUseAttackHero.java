package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandUseAttackHero {

    private final String command;

    private final Coordinates cardAttacker;

    private final String error;

    private final String gameEnded;

    public CommandUseAttackHero(final String command, final int x1, final int y1,
                                final String error, final String gameEnded) {
        this.command = command;
        this.cardAttacker = new Coordinates(x1, y1);
        this.error = error;
        this.gameEnded = gameEnded;
    }

    public final String getCommand() {
        return command;
    }

    public final Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public final String getError() {
        return error;
    }

    public final String getGameEnded() {
        return gameEnded;
    }

}

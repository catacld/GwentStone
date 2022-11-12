package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class commandUseAttackHero {

    private String command;

    private Coordinates cardAttacker;

    private String error ;

    private String gameEnded;

    public commandUseAttackHero(String command, int x1, int y1, String error, String gameEnded) {
        this.command = command;
        this.cardAttacker = new Coordinates(x1, y1);
        this.error = error;
        this.gameEnded = gameEnded;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coordinates cardAttacker) {
        this.cardAttacker = cardAttacker;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(String gameEnded) {
        this.gameEnded = gameEnded;
    }
}

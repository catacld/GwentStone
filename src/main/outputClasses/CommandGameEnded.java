package main.outputClasses;

public class CommandGameEnded {

    private final String gameEnded;

    public CommandGameEnded(final String gameEnded) {
        this.gameEnded = gameEnded;
    }

    public final String getGameEnded() {
        return gameEnded;
    }

}

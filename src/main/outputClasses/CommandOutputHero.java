package main.outputClasses;

import main.cards.HeroCard;

public class CommandOutputHero {

    private final String command;
    private final Integer playerIdx;

    private final HeroCard output;


    public CommandOutputHero(final String command, final HeroCard hero,
                             final Integer playerIdx) {
        this.command = command;
        this.output = hero;
        this.playerIdx = playerIdx;
    }

    public final String getCommand() {
        return command;
    }

    public final HeroCard getOutput() {
        return output;
    }

    public final Integer getPlayerIdx() {
        return playerIdx;
    }

}

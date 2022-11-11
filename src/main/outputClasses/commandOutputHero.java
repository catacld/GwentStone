package main.outputClasses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import main.cards.Card;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;
import java.util.Objects;

public class commandOutputHero {

    private String command;
    private Integer playerIdx = null;

    private HeroCard output = null;


    public commandOutputHero(String command, HeroCard hero, Integer playerIdx) {
        this.command = command;
        this.output = hero;
        this.playerIdx = playerIdx;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public HeroCard getOutput() {
        return output;
    }

    public void setOutput(HeroCard hero) {
        this.output = hero;
    }

    public Integer getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(Integer playerIdx) {
        this.playerIdx = playerIdx;
    }
}

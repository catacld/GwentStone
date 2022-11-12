package main.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class HeroCard extends Card{

    private int health = 30;

    @JsonIgnore
    private int frozen;

    public HeroCard(int mana, String description, ArrayList<String> colors, String name, int playerId) {
        super(mana, description, colors, name, playerId);
        this.frozen = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public void useHeroAbility(int affectedRow) {

    }

    //    @Override
//    public String toString() {
//        return "HeroCard{" +
//                "health= \n" + health +
//                '}';
//    }
}

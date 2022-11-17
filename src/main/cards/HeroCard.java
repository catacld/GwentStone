package main.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class HeroCard extends Card{

    // each hero starts with 30 points of health
    private int health = 30;

    // checker whether the hero is able to attack or not
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

}

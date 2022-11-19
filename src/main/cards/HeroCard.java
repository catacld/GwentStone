package main.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class HeroCard extends Card {

    // each hero starts with 30 points of health
    private final int defaultHealth = 30;
    private int health;

    // checker whether the hero is able to attack or not
    @JsonIgnore
    private int frozen;

    public HeroCard(final int mana, final String description,
                    final ArrayList<String> colors, final String name,
                    final int playerId) {
        super(mana, description, colors, name, playerId);
        this.frozen = 0;
        this.health = defaultHealth;
    }

    public final int getHealth() {
        return health;
    }


    public final void setHealth(final int health) {
        this.health = health;
    }

    public final int getFrozen() {
        return frozen;
    }

    public final void setFrozen(final int frozen) {
        this.frozen = frozen;
    }

    /**
     * uses the hero's ability
     */
    public void useHeroAbility(final int affectedRow) {

    }

}

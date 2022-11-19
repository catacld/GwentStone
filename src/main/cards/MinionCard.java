package main.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class MinionCard extends Card {

    private  int health;
    private  int attackDamage;

    // the rows where the card can be placed
    // depending on the number of the player who owns the card
    @JsonIgnore
    private int boardPosition;


    //check whether the card is able to attack or not
    @JsonIgnore
    private int frozen;

    public MinionCard(final int mana, final String description, final ArrayList<String> colors,
                      final String name, final int health, final int attackDamage,
                      final int playerId, final int boardPosition) {
        super(mana, description, colors, name, playerId);
        this.health = health;
        this.attackDamage = attackDamage;
        this.frozen = 0;
        this.boardPosition = boardPosition;
    }

    public final int getHealth() {
        return health;
    }

    public final void setHealth(final int health) {
        this.health = health;
    }

    public final int getAttackDamage() {
        return attackDamage;
    }

    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public final int getFrozen() {
        return frozen;
    }

    public final void setFrozen(final int frozen) {
        this.frozen = frozen;
    }

    public final int getBoardPosition() {
        return boardPosition;
    }

}

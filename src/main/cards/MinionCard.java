package main.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({ "mana", "attackDamage", "health", "description", "colors", "name"})
public class MinionCard extends Card{

    private int health;
    private int attackDamage;

    @JsonIgnore
    private int boardPosition;


    //check if a card is frozen
    @JsonIgnore
    private boolean frozen;

    public MinionCard(int mana, String description, ArrayList<String> colors, String name, int health, int attackDamage, int playerId, int boardPosition) {
        super(mana, description, colors, name, playerId);
        this.health = health;
        this.attackDamage = attackDamage;
        this.frozen = false;
        this.boardPosition = boardPosition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int boardPosition) {
        this.boardPosition = boardPosition;
    }
}

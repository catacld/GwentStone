package main.cards;

import java.util.ArrayList;

public class MinionCard extends Card{

    private int health, attackDamage;
    //check if a card is frozen
    private boolean frozen;

    public MinionCard(int mana, String description, ArrayList<String> colors, String name, int health, int attackDamage, int playerId) {
        super(mana, description, colors, name, playerId);
        this.health = health;
        this.attackDamage = attackDamage;
        this.frozen = false;
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

    @Override
    public String toString() {
        return "MinionCard{" +
                "health=" + health +
                ", attackDamage=" + attackDamage +
                ", frozen=" + frozen + ", name=" + super.getName() +
                '}';
    }
}

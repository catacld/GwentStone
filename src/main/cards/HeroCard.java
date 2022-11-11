package main.cards;

import java.util.ArrayList;

public class HeroCard extends Card{

    private int health = 30;

    public HeroCard(int mana, String description, ArrayList<String> colors, String name, int playerId) {
        super(mana, description, colors, name, playerId);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

//    @Override
//    public String toString() {
//        return "HeroCard{" +
//                "health= \n" + health +
//                '}';
//    }
}

package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Warden extends MinionCard {

    //private final int boardPosition;

    public Warden(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "Warden", health, attackDamage, playerId, (playerId % 2) + 1);
        // = 2 * (playerId % 2) + 1;
    }


}

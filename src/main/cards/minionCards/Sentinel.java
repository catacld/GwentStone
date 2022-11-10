package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Sentinel extends MinionCard {

    // the position where the card can be placed
    private int boardPosition;

    public Sentinel(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"Sentinel", health, attackDamage, playerId);
        boardPosition = 2 * (playerId % 2) + playerId % 2;
    }


}

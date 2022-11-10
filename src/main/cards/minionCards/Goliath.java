package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Goliath extends MinionCard {

    private final int boardPosition;

    public Goliath(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "Goliath", health, attackDamage, playerId);
        this.boardPosition = 2 * (playerId % 2) + 1;
    }
}

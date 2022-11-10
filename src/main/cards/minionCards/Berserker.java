package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Berserker extends MinionCard {

    private int boardPosition;

    public Berserker(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"Berserker", health, attackDamage, playerId);
        boardPosition = 2 * (playerId % 2) + playerId % 2;
    }
}
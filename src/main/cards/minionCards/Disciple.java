package main.cards.minionCards;

import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Disciple extends MinionCard {
    private int boardPosition;

    private GameBoard board;

    public Disciple(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"Disciple", health, attackDamage, playerId);
        boardPosition = 2 * (playerId % 2) + playerId % 2;
    }

    public void cardUsesAbility(int x, int y) {
        MinionCard targetCard = board.getCard(x,y);
        targetCard.setHealth(targetCard.getHealth() + 2);
    }
}

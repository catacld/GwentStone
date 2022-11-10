package main.cards.minionCards;

import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Miraj extends MinionCard {

    private final int boardPosition;
    private GameBoard board;

    public Miraj(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "Miraj", health, attackDamage, playerId);
        boardPosition = 2 * (playerId % 2) + 1;
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {
        MinionCard targetCard = board.getCard(x,y);
        int aux = targetCard.getHealth();
        targetCard.setHealth(this.getHealth());
        this.setHealth(aux);
    }
}

package main.cards.minionCards;

import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class TheCursedOne extends MinionCard {

    private int boardPosition;

    private GameBoard board;

    public TheCursedOne(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"The Cursed One", health, attackDamage, playerId);
        boardPosition = 2 * (playerId % 2) + playerId % 2;
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {
        MinionCard targetCard = board.getCard(x,y);
        int aux = targetCard.getHealth();
        targetCard.setHealth(targetCard.getAttackDamage());
        targetCard.setAttackDamage(aux);
    }
}
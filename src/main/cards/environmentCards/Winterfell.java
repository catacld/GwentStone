package main.cards.environmentCards;

import main.GameBoard;
import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Winterfell extends Card{

    public GameBoard board;

    public Winterfell(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Winterfell", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useEnvironmentCard(int affectedRow) {
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        for (MinionCard curr : cardsOnRow) {
            curr.setFrozen(true);
        }
    }
}

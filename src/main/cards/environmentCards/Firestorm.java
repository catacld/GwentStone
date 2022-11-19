package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Firestorm extends Card {

    @JsonIgnore
    private GameBoard board;

    public Firestorm(final int mana, final String description, final ArrayList<String> colors,
                     final int playerId) {
        super(mana, description, colors, "Firestorm", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * decrease the health of each card on the row of
     * index "affectedRow" by 1
     */
    public final void useAbility(final int affectedRow) {
        // get the cards placed on the affected row
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        // decrease each card's health
        for (MinionCard curr : cardsOnRow) {
            curr.setHealth(curr.getHealth() - 1);
        }

        // delete the cards that have no health left
        board.cleanRow(affectedRow);
    }

    public final GameBoard getBoard() {
        return board;
    }

    public final void setBoard(final GameBoard board) {
        this.board = board;
    }
}

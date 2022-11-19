package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.Card;

import java.util.ArrayList;

public class Winterfell extends Card {

    @JsonIgnore
    private GameBoard board;

    public Winterfell(final int mana, final String description,
                      final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "Winterfell", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * freeze all the cards placed on the row of index
     * "affectedRow"
     */
    public void useAbility(final int affectedRow) {
        // freeze the entire row
        board.freezeRow(affectedRow);
    }

    public final GameBoard getBoard() {
        return board;
    }
}

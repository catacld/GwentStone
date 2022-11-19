package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.cards.Card;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class HeartHound extends Card {

    @JsonIgnore
    private GameBoard board;

    public HeartHound(final int mana, final String description,
                      final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "Heart Hound", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * steal the card with maximum health from the row of
     * index "rowIndex" and place it on a friendly row
     * of index "ownRow"
     */
    public final void useAbility(final int rowIndex, final int ownRow) {

        // get the index of the card with maximum health
        int index = board.getMaxHealthCard(rowIndex);

        // get the card with maximum health from the enemy's row
        MinionCard card = board.getRow(rowIndex).remove(index);

        // place the stolen card on a friendly row
        board.placeCard(ownRow, card);
    }

    public final GameBoard getBoard() {
        return board;
    }


}

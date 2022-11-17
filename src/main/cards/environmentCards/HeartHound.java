package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.cards.Card;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class HeartHound extends Card {

    @JsonIgnore
    public GameBoard board;

    public HeartHound(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Heart Hound", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useAbility(int rowIndex, int ownRow) {

        // get the index of the card with maximum health
        int index = board.getMaxHealthCard(rowIndex);

        // get the card with maximum health from the enemy's row
        MinionCard card = board.getRow(rowIndex).remove(index);

        // place the stolen card on a friendly row
        board.placeCard(ownRow, card);
    }
}

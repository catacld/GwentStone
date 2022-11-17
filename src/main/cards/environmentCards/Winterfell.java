package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.Card;

import java.util.ArrayList;

public class Winterfell extends Card{

    @JsonIgnore
    public GameBoard board;

    public Winterfell(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Winterfell", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useAbility(int affectedRow) {
        // freeze the entire row
        board.freezeRow(affectedRow);
    }
}

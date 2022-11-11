package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.cards.Card;
import main.GameBoard;

import java.util.ArrayList;

public class HeartHound extends Card {

    @JsonIgnore
    public GameBoard board;

    public HeartHound(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Heart Hound", playerId);
        this.board = GameBoard.getInstance();
    }
}

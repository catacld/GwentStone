package main.cards.environmentCards;

import main.cards.Card;
import main.GameBoard;

import java.util.ArrayList;

public class HeartHound extends Card {

    public GameBoard board;

    public HeartHound(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "HeartHound", playerId);
        this.board = GameBoard.getInstance();
    }
}

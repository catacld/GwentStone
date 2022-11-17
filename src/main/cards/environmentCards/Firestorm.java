package main.cards.environmentCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Firestorm extends Card {

    @JsonIgnore
    private GameBoard board;

    public Firestorm(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Firestorm", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useAbility(int affectedRow) {
        // get the row of cards
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        // decrease each card's health
        for (MinionCard curr : cardsOnRow) {
            curr.setHealth(curr.getHealth() - 1);
        }

        // delete the cards that have no health left
        board.cleanRow(affectedRow);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}

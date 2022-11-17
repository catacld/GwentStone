package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Disciple extends MinionCard {

    @JsonIgnore
    private GameBoard board;

    public Disciple(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"Disciple", health, attackDamage, playerId, 2 * (playerId % 2) + playerId % 2);
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x,y);

        // use the ability on the card
        targetCard.setHealth(targetCard.getHealth() + 2);
    }



    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}

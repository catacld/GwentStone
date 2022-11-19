package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Disciple extends MinionCard {

    @JsonIgnore
    private GameBoard board;

    public Disciple(final int mana, final String description, final ArrayList<String> colors,
                    final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors,
                "Disciple", health, attackDamage, playerId, 2 * (playerId % 2) + playerId % 2);
        this.board = GameBoard.getInstance();
    }

    /**
     * increases the health of the target card by 2 points
     */
    public void cardUsesAbility(final int x, final int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x, y);

        // use the ability on the card
        targetCard.setHealth(targetCard.getHealth() + 2);
    }



    public final GameBoard getBoard() {
        return board;
    }

    public final void setBoard(final GameBoard board) {
        this.board = board;
    }
}

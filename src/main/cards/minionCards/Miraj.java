package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Miraj extends MinionCard {

    @JsonIgnore
    private GameBoard board;

    public Miraj(final int mana, final String description, final ArrayList<String> colors,
                 final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors,
                "Miraj", health, attackDamage, playerId, (playerId % 2) + 1);
        this.board = GameBoard.getInstance();
    }

    /**
     * swap the health of the card placed at (x,y)
     * with the current card's own health
     */
    public final void cardUsesAbility(final int x, final int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x, y);

        // swap the health of the enemy card
        // with the current card's own health
        int aux = targetCard.getHealth();
        targetCard.setHealth(this.getHealth());
        this.setHealth(aux);
    }

}

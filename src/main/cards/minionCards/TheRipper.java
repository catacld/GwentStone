package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class TheRipper extends MinionCard {


    @JsonIgnore
    private GameBoard board;

    public TheRipper(final int mana, final String description, final ArrayList<String> colors,
                     final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors, "The Ripper",
                health, attackDamage, playerId, (playerId % 2) + 1);
        this.board = GameBoard.getInstance();
    }

    /**
     * decrease the attack damage of the card placed at (x,y)
     * by 2
     */
    public void cardUsesAbility(final int x, final int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x, y);

        // decrease the enemy card's attack damage by 2 points
        // it can not be less than 0
        targetCard.setAttackDamage(Math.max(targetCard.getAttackDamage() - 2, 0));
    }


}

package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class Miraj extends MinionCard {

    @JsonIgnore
    private GameBoard board;

    public Miraj(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "Miraj", health, attackDamage, playerId, (playerId % 2) + 1);
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x,y);

        // swap the health of the enemy card
        // with its own health
        int aux = targetCard.getHealth();
        targetCard.setHealth(this.getHealth());
        this.setHealth(aux);
    }

}

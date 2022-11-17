package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class TheCursedOne extends MinionCard {



    @JsonIgnore
    private GameBoard board;

    public TheCursedOne(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors,"The Cursed One", health, attackDamage, playerId, 2 * (playerId % 2) + playerId % 2);
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {

        // get the card placed at (x,y)
        MinionCard targetCard = board.getCard(x,y);

        // swap the enemy card's health with
        // its attack damage
        int aux = targetCard.getHealth();
        targetCard.setHealth(targetCard.getAttackDamage());
        targetCard.setAttackDamage(aux);
    }


}

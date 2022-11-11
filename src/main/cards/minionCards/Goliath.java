package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Goliath extends MinionCard {

    //private  int boardPosition;

    public Goliath(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "Goliath", health, attackDamage, playerId,(playerId % 2) + 1 );
        //this.boardPosition = 2 * (playerId % 2) + 1;
    }


}

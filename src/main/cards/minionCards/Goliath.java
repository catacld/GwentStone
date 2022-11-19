package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Goliath extends MinionCard {


    public Goliath(final int mana, final String description, final ArrayList<String> colors,
                   final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors,
                "Goliath", health, attackDamage, playerId, (playerId % 2) + 1);
    }


}

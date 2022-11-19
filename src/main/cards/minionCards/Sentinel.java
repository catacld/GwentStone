package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Sentinel extends MinionCard {


    public Sentinel(final int mana, final String description, final ArrayList<String> colors,
                    final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors,
                "Sentinel", health, attackDamage, playerId, 2 * (playerId % 2) + playerId % 2);
    }


}

package main.cards.minionCards;

import main.cards.MinionCard;

import java.util.ArrayList;

public class Warden extends MinionCard {


    public Warden(final int mana, final String description, final ArrayList<String> colors,
                  final int health, final int attackDamage, final int playerId) {
        super(mana, description, colors, "Warden", health,
                attackDamage, playerId, (playerId % 2) + 1);
    }


}

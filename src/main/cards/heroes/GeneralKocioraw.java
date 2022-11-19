package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class GeneralKocioraw extends HeroCard {

    private GameBoard board;

    public GeneralKocioraw(final int mana, final String description,
                           final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "General Kocioraw", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * increase the attack damage of every card placed on
     * the row of index "affectedRow" by 1
     */
    public void useHeroAbility(final int affectedRow) {
        // get the cards placed on the affected row
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        // use the ability on every card on the row
        for (MinionCard curr : cardsOnRow) {
            curr.setAttackDamage(curr.getAttackDamage() + 1);
        }
    }
}

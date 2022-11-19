package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class KingMudface extends HeroCard {

    private GameBoard board;

    public KingMudface(final int mana, final String description,
                       final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "King Mudface", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * increase the health of every card on the row
     * of index "affectedRow" by 1
     */
    public void useHeroAbility(final int affectedRow) {
        // get the cards placed on the affected row
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        // use the ability on every cards on the row
        for (MinionCard curr : cardsOnRow) {
            curr.setHealth(curr.getHealth() + 1);
        }
    }


}

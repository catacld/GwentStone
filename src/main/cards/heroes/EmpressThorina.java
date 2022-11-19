package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;


import java.util.ArrayList;

public class EmpressThorina extends HeroCard {

    private GameBoard board;

    public EmpressThorina(final int mana, final String description,
                          final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "Empress Thorina", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * remove the card with maximum health from the row
     * of index "affectedRow"
     */
    public void useHeroAbility(final int affectedRow) {

        // get the index of the card with maximum health
        int index = board.getMaxHealthCard(affectedRow);

        // remove the card with maximum health placed on the affected row
        board.getGameBoard().get(affectedRow).remove(index);
    }
}

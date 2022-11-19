package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class LordRoyce extends HeroCard {

    private GameBoard board;

    public LordRoyce(final int mana, final String description,
                     final ArrayList<String> colors, final int playerId) {
        super(mana, description, colors, "Lord Royce", playerId);
        this.board = GameBoard.getInstance();
    }

    /**
     * freeze the card with the maximum health placed
     * on the row of index "affectedRow"
     */
    public void useHeroAbility(final int affectedRow) {
        // get the card with the maximum attack damage
        // placed on the affected row
        MinionCard maxAttackCard = board.getMaxAttackCard(affectedRow);

        // freeze the card
        maxAttackCard.setFrozen(1);
    }
}

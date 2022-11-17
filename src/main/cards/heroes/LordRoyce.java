package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class LordRoyce extends HeroCard {

    private GameBoard board;

    public LordRoyce(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Lord Royce", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useHeroAbility(int affectedRow) {
        // get the card with the maximum attack damage
        // placed on the affected row
        MinionCard maxAttackCard = board.getMaxAttackCard(affectedRow);

        // freeze the card
        maxAttackCard.setFrozen(1);
    }
}

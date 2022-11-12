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
        MinionCard maxAttackCard = board.getMaxAttackCard(affectedRow);
        maxAttackCard.setFrozen(1);
    }
}

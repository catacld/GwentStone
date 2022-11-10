package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class KingMudface extends HeroCard {

    private GameBoard board;

    public KingMudface(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "King Mudface", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useHeroAbility(int affectedRow) {
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        for (MinionCard curr : cardsOnRow) {
            curr.setHealth(curr.getHealth() + 1);
        }
    }


}

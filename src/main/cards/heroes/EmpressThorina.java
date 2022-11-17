package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class EmpressThorina extends HeroCard {

    private GameBoard board;

    public EmpressThorina(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "Empress Thorina", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useHeroAbility(int affectedRow) {

        // get the index of the card with maximum health
        int index = board.getMaxHealthCard(affectedRow);

        // remove the card with maximum health placed on the affected row
        board.getGameBoard().get(affectedRow).remove(index);
    }
}

package main.cards.heroes;

import main.GameBoard;
import main.cards.HeroCard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class GeneralKocioraw extends HeroCard {

    private GameBoard board;

    public GeneralKocioraw(int mana, String description, ArrayList<String> colors, int playerId) {
        super(mana, description, colors, "General Kocioraw", playerId);
        this.board = GameBoard.getInstance();
    }

    public void useHeroAbility(int affectedRow) {
        // get the cards on the affected row
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        // use the ability on every card on the row
        for (MinionCard curr : cardsOnRow) {
            curr.setAttackDamage(curr.getAttackDamage()+1);
        }
    }
}

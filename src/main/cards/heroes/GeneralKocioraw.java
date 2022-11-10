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
        ArrayList<MinionCard> cardsOnRow = board.getRow(affectedRow);

        for (MinionCard curr : cardsOnRow) {
            curr.setAttackDamage(curr.getAttackDamage()+1);
        }
    }
}

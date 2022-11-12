package main.cards.minionCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.GameBoard;
import main.cards.MinionCard;

import java.util.ArrayList;

public class TheRipper extends MinionCard {


    @JsonIgnore
    private GameBoard board;

    public TheRipper(int mana, String description, ArrayList<String> colors, int health, int attackDamage, int playerId) {
        super(mana, description, colors, "The Ripper", health, attackDamage, playerId, (playerId % 2) + 1);
//        boardPosition = 2 * (playerId % 2) + 1;
        this.board = GameBoard.getInstance();
    }

    public void cardUsesAbility(int x, int y) {
        MinionCard targetCard = board.getCard(x,y);
        targetCard.setAttackDamage(Math.max(targetCard.getAttackDamage() - 2,0));
    }


}

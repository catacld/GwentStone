package main;

import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class GameBoard {

    private static GameBoard instance = null;

    private ArrayList<ArrayList<MinionCard>> gameBoard;


    private GameBoard() {

        gameBoard = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            gameBoard.add(new ArrayList<>());
        }

    }

    // we make the class singleton since we always
    // refer to the same gameboard
    public static GameBoard getInstance() {

        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    public static void setInstance(GameBoard instance) {
        GameBoard.instance = instance;
    }

    public MinionCard getCard(int x, int y) {

        if (y == gameBoard.get(x).size()) {
            return null;
        } else {
            return gameBoard.get(x).get(y);
        }
    }


    public int placeCard(int rowIndex, MinionCard card) {


        if (gameBoard.get(rowIndex).size() == 5) {
            return -1;
        } else {
            gameBoard.get(rowIndex).add(card);
            return 1;
        }

    }

    public void deleteCard(int x, int y) {
        gameBoard.get(x).remove(y);
    }


    // get cards from a row
    public ArrayList<MinionCard> getRow(int rowIndex) {


        return gameBoard.get(rowIndex);

    }

    public MinionCard getMaxAttackCard(int index) {
        int maximum = Integer.MIN_VALUE;
        MinionCard maxCard = null;

        for (int i = 0; i < gameBoard.get(index).size() ; i++) {
            if (gameBoard.get(index).get(i).getAttackDamage() > maximum) {
                maximum = gameBoard.get(index).get(i).getAttackDamage();
                maxCard = gameBoard.get(index).get(i);
            }
        }

        return maxCard;
    }

    public int getMaxHealthCard(int index) {
        int maximum = Integer.MIN_VALUE;
        int maxHealthIndex = -1;

        for (int i = 0; i < gameBoard.get(index).size() ; i++) {
            if (gameBoard.get(index).get(i).getHealth() > maximum) {
                maximum = gameBoard.get(index).get(i).getHealth();
                maxHealthIndex = i;
            }
        }

        return maxHealthIndex;
    }

    public ArrayList<ArrayList<MinionCard>> getGameBoard() {
        return gameBoard;
    }


    public void unfreezeAll() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                if (gameBoard.get(i).get(j) instanceof MinionCard) {
                    gameBoard.get(i).get(j).setFrozen(false);
                }
            }
        }
    }
}

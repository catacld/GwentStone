package main;

import main.cards.Card;
import main.cards.MinionCard;

import java.util.ArrayList;

public class GameBoard {

    private static GameBoard instance = null;
    private MinionCard[][] gameBoard;


    private GameBoard() {
        gameBoard = new MinionCard[4][5];
    }

    // we make the class singleton since we always
    // refer to the same gameboard
    public static GameBoard getInstance() {

        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    public MinionCard getCard(int x, int y) {
        return gameBoard[x][y];
    }


    public void placeCard(int x, int y, MinionCard card) {
        gameBoard[x][y] = card;
    }

    public void deleteCard(int x, int y) {
        gameBoard[x][y] = null;
        for (int i = x; i < 4; i++) {
            gameBoard[i][y] = gameBoard[i+1][y];
        }
    }


    // get cards from a row
    public ArrayList<MinionCard> getRow(int rowIndex) {

        ArrayList<MinionCard> cards = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (gameBoard[rowIndex][i] != null) {
                cards.add(gameBoard[rowIndex][i]);
            }
        }

        return cards;

    }

    public MinionCard getMaxAttackCard(int index) {
        int maximum = Integer.MIN_VALUE;
        MinionCard maxCard = null;

        for (int i = 0; i < 5 ; i++) {
            if (gameBoard[index][i] != null && gameBoard[index][i].getAttackDamage() > maximum) {
                maximum = gameBoard[index][i].getAttackDamage();
                maxCard = gameBoard[index][i];
            }
        }

        return maxCard;
    }

    public int getMaxHealthCard(int index) {
        int maximum = Integer.MIN_VALUE;
        int maxHealthIndex = -1;

        for (int i = 0; i < 5 ; i++) {
            if (gameBoard[index][i] != null && gameBoard[index][i].getHealth() > maximum) {
                maximum = gameBoard[index][i].getHealth();
                maxHealthIndex = i;
            }
        }

        return maxHealthIndex;
    }

    public MinionCard[][] getGameBoard() {
        return gameBoard;
    }
}

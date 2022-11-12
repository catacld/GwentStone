package main;

import main.cards.Card;
import main.cards.MinionCard;

import java.lang.reflect.Array;
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

        if (y >= gameBoard.get(x).size()) {
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

            for(int i =0; i < 4; i++) {
                for (int w =0; w < gameBoard.get(i).size(); w++) {
                    System.out.println("---ROW " + i + "---COLUMN " + w + "---CARD: " + gameBoard.get(i).get(w) + "HEALTH: " + gameBoard.get(i).get(w).getHealth()
                            + "ATTACK---" + gameBoard.get(i).get(w).getAttackDamage());
                }
                //System.out.println();
            }

            System.out.println();
            System.out.println();
            System.out.println();
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


    public void freezeRow(int affectedRow) {
        for (int i = 0; i < gameBoard.get(affectedRow).size(); i++) {
            gameBoard.get(affectedRow).get(i).setFrozen(1);
        }
    }


    public ArrayList<MinionCard> getFrozenCards() {

        ArrayList<MinionCard> frozenCards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            int size = gameBoard.get(i).size();

            for (int j = 0; j < size; j++) {
                if (gameBoard.get(i).get(j).getFrozen() == 1) {
                    frozenCards.add(gameBoard.get(i).get(j));
                }
            }
        }

        return frozenCards;
    }

    public boolean containsTank(int playerIdx) {
        int startRow;
        int endRow;

        if (playerIdx == 1) {
            startRow = 0;
            endRow = 1;
        } else {
            startRow = 2;
            endRow = 3;
        }
        for (int i = startRow; i <= endRow; i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                if (gameBoard.get(i).get(j).getName().equals("Goliath") ||
                    gameBoard.get(i).get(j).getName().equals("Warden")) {
                    return true;
                }
            }
        }

        return false;
    }


    public void cleanRow(int affectedRow) {
        int size = gameBoard.get(affectedRow).size();

        for (int i = 0; i < size; i++) {
            if (gameBoard.get(affectedRow).get(i).getHealth() <= 0) {
                gameBoard.get(affectedRow).remove(i);
                size--;
                i--;
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("------AFTER CLEANING-----");

        for (int i = 0; i < 4; i++) {
            for (int w = 0; w < gameBoard.get(i).size(); w++) {
                System.out.println("---ROW " + i + "---COLUMN " + w + "---CARD: " + gameBoard.get(i).get(w) + "HEALTH: " + gameBoard.get(i).get(w).getHealth()
                        + "ATTACK---" + gameBoard.get(i).get(w).getAttackDamage());
            }
        }


        System.out.println("------AFTER CLEANING DONE-----");
        System.out.println();
        System.out.println();
        System.out.println();
    }


    public void unfreeze(int playerIdx) {
        int startRow;
        int endRow;

        if (playerIdx == 2) {
            startRow = 0;
            endRow = 1;
        } else {
            startRow = 2;
            endRow = 3;
        }
        for (int i = startRow; i <= endRow; i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                    gameBoard.get(i).get(j).setFrozen(0);
            }
        }
    }
}

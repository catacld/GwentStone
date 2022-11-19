package main;

import main.cards.MinionCard;

import java.util.ArrayList;

public final class GameBoard {

    private final int maxNumberOfCards = 5;
    private final int numberOfRows = 4;

    private static GameBoard instance = null;

    private ArrayList<ArrayList<MinionCard>> gameBoard;


    private GameBoard() {

        gameBoard = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i++) {
            gameBoard.add(new ArrayList<>());
        }

    }

    /**
     * make the class singleton since the same gameboard
     * is always referred to
     */
    public static GameBoard getInstance() {

        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    public static void setInstance(final GameBoard instance) {
        GameBoard.instance = instance;
    }

    /**
     * get the card placed at (x,y) on the game board
     */
    public MinionCard getCard(final int x, final int y) {

        // if there is no card placed return null
        if (y >= gameBoard.get(x).size()) {
            return null;
        } else { // else return the card
            return gameBoard.get(x).get(y);
        }
    }

    /**
     * place a card on the game board
     */
    public int placeCard(final int rowIndex, final MinionCard card) {
        // check if the row is full
        if (gameBoard.get(rowIndex).size() == maxNumberOfCards) {
            return -1;
        } else { // if not then place the card
            gameBoard.get(rowIndex).add(card);
            return 1;
        }
    }

    /**
     * get the cards placed on a row of the game board
     */
    public ArrayList<MinionCard> getRow(final int rowIndex) {

        return gameBoard.get(rowIndex);

    }

    /**
     * get the card with the maximum attack damage
     * from the row of index "index"
     */
    public MinionCard getMaxAttackCard(final int index) {
        int maximum = Integer.MIN_VALUE;
        MinionCard maxCard = null;

        // traverse the entire row of the game board
        for (int i = 0; i < gameBoard.get(index).size(); i++) {
            // check for maximum
            if (gameBoard.get(index).get(i).getAttackDamage() > maximum) {
                maximum = gameBoard.get(index).get(i).getAttackDamage();
                maxCard = gameBoard.get(index).get(i);
            }
        }

        // return the card with maximum attack damage
        return maxCard;
    }

    /**
     * similar to "getMaxAttackCard" method
     */
    public int getMaxHealthCard(final int index) {
        int maximum = Integer.MIN_VALUE;
        int maxHealthIndex = -1;

        // traverse the entire row of the game board
        for (int i = 0; i < gameBoard.get(index).size(); i++) {
            if (gameBoard.get(index).get(i).getHealth() > maximum) {
                // check for maximum
                maximum = gameBoard.get(index).get(i).getHealth();
                maxHealthIndex = i;
            }
        }

        return maxHealthIndex;
    }

    public ArrayList<ArrayList<MinionCard>> getGameBoard() {
        return gameBoard;
    }


    /**
     * freeze an entire row of the game board
     */
    public void freezeRow(final int affectedRow) {
        for (int i = 0; i < gameBoard.get(affectedRow).size(); i++) {
            gameBoard.get(affectedRow).get(i).setFrozen(1);
        }
    }


    /**
     * get all the frozen cards placed on the game board
     */
    public ArrayList<MinionCard> getFrozenCards() {

        ArrayList<MinionCard> frozenCards = new ArrayList<>();

        // traverse the entire game board and search for frozen cards
        for (int i = 0; i < numberOfRows; i++) {

            int size = gameBoard.get(i).size();

            // traverse each row of the game board
            for (int j = 0; j < size; j++) {
                // check if the card is frozen
                if (gameBoard.get(i).get(j).getFrozen() == 1) {
                    frozenCards.add(gameBoard.get(i).get(j));
                }
            }
        }

        return frozenCards;
    }

    /**
     * check if a player has any tank card placed
     * on the game board
     */
    public boolean containsTank(final int playerIdx) {
        // get the enemy player's part of the game board
        int startRow = playerIdx - playerIdx % 2;
        int endRow = startRow + 1;


        // check the entire part of the game board belonging to the player
        // for tank cards
        for (int i = startRow; i <= endRow; i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                if (gameBoard.get(i).get(j).isTank()) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * delete the cards that no longer have any health points
     * from the row of index "affectedRow"
     */
    public void cleanRow(final int affectedRow) {

        // get the number of cards that are on the row
        int numberOfCards = gameBoard.get(affectedRow).size();

        // delete the cards that no longer have health points
        for (int i = 0; i < numberOfCards; i++) {
            if (gameBoard.get(affectedRow).get(i).getHealth() <= 0) {
                gameBoard.get(affectedRow).remove(i);
                numberOfCards--;
                i--;
            }
        }

    }


    /**
     * unfreeze all the cards placed on the side of
     * the player of id "playerIdx"
     */
    public void unfreeze(final int playerIdx) {

        // get the player's part of the game board
        int startRow = 2 * (playerIdx % 2);
        int endRow = startRow + 1;

        // unfreeze all the player's cards
        for (int i = startRow; i <= endRow; i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                gameBoard.get(i).get(j).setFrozen(0);
            }
        }
    }
}

package main;

import checker.Checker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ArrayList;

import main.cards.Card;
import main.cards.MinionCard;
import main.cards.environmentCards.Firestorm;
import main.cards.environmentCards.HeartHound;
import main.cards.environmentCards.Winterfell;
import main.cards.minionCards.Disciple;
import main.cards.minionCards.Miraj;
import main.cards.minionCards.TheCursedOne;
import main.cards.minionCards.TheRipper;
import main.outputClasses.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */




public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */

    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation




        ArrayList<Player> players = new ArrayList<Player>();

        DecksInput playerOneDecksInput = inputData.getPlayerOneDecks();
        DecksInput playerTwoDecksInput = inputData.getPlayerTwoDecks();
        ArrayList<GameInput> games = inputData.getGames();

        //Player player1 = new Player(0,0, playerOneDecksInput.getNrDecks(), playerOneDecksInput, 1);
        //Player player2 = new Player(0,0, playerTwoDecksInput.getNrDecks(), playerTwoDecksInput, 2);

        //players.add(player1);
        //players.add(player2);

        GameBoard gameBoard;

        int gamesPlayed = 0;
        int player1Wins = 0;
        int player2Wins = 0;


        // starting each game

        for (int i = 0; i < games.size(); i++) {

            gamesPlayed++;

            Player player1 = new Player(gamesPlayed,player1Wins, playerOneDecksInput.getNrDecks(), playerOneDecksInput, 1);
            Player player2 = new Player(gamesPlayed,player2Wins, playerTwoDecksInput.getNrDecks(), playerTwoDecksInput, 2);

            gameBoard = GameBoard.getInstance();

            StartGameInput startGame = games.get(i).getStartGame();
            int seed = startGame.getShuffleSeed();
            int manaToReceive = 1;

            // used later to apply commands on a specific player
            int id;

            // aux variables to check which players ended their turns
            boolean player1Ended = false;
            boolean player2Ended = false;
            boolean gameInProgress = true;

            // preparation at the beginning of each game
            //players.clear();
            player1.setDeck(startGame.getPlayerOneDeckIdx());
            player2.setDeck(startGame.getPlayerTwoDeckIdx());
            player1.getDeck().shuffle(seed);
            player2.getDeck().shuffle(seed);
            player1.setHero(startGame.getPlayerOneHero());
            player2.setHero(startGame.getPlayerTwoHero());
            player1.setMana(player1.getMana() + Math.min(manaToReceive,10));
            player2.setMana(player2.getMana() + Math.min(manaToReceive,10));
            if (player1.getDeck().getSize() != 0) {
                player1.addToHand(player1.getDeck().drawCard());
                player2.addToHand(player2.getDeck().drawCard());
            }


            // decide the order in which the game will be played
            int playerTurn = startGame.getStartingPlayer();

//            if (playerTurn == 2) {
//                players.add(player2);
//                players.add(player1);
//            } else {
//                players.add(player1);
//                players.add(player2);
//            }

            ArrayList<ActionsInput> actions = games.get(i).getActions();







            for (int j = 0; j < actions.size() && gameInProgress == true; j++) {
                //player1.setMana(player1.getMana() + Math.max(manaToReceive,10));
                //player2.setMana(player2.getMana() + Math.max(manaToReceive,10));




                String command = actions.get(j).getCommand();


                switch (command) {
                    case "getPlayerDeck":

                        id = actions.get(j).getPlayerIdx();
                        if (id == 1) {
                            commandGetDeck commandToPrint = new commandGetDeck(command, player1.getDeck().getDeck(), 1);
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
//                            for (int y = 0; y < player1.getDeck().getSize(); y++)
//                            {
//                                output.add(objectMapper.convertValue(player1.getDeck().getCard(y), JsonNode.class));
//                            }
                        } else {
//                            for (int y = 0; y < player2.getDeck().getSize(); y++)
//                            {
//                                output.add(objectMapper.convertValue(player2.getDeck().getCard(y), JsonNode.class));
//                            }

                            commandGetDeck commandToPrint = new commandGetDeck(command, player2.getDeck().getDeck(), 2);
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        }
                        break;
                    case "getPlayerHero":

                        id = actions.get(j).getPlayerIdx();
                        if (id == 1) {
                            commandOutputHero commandToPrint = new commandOutputHero(command, player1.getHero(), 1);
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        } else {
                            commandOutputHero commandToPrint = new commandOutputHero(command, player2.getHero(), 2);
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        }
                        break;
                    case "getCardsInHand":
                        id = actions.get(j).getPlayerIdx();

                        if (id == 1) {
                            commandgetCardsInHand commandToPrint = new commandgetCardsInHand(command, id, player1.getCardsInHand());
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        } else {
                            commandgetCardsInHand commandToPrint = new commandgetCardsInHand(command, id, player2.getCardsInHand());
                            output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        }
                        break;
                    case "getPlayerTurn":
                        commandPlayerTurn commandToPrint = new commandPlayerTurn(command, playerTurn);
                        output.add(objectMapper.convertValue(commandToPrint, JsonNode.class));
                        break;
                    case "endPlayerTurn":
                        if (playerTurn == 1) {
                            player1Ended = true;
                            gameBoard.unfreeze(1);
                            player1.getHero().setFrozen(0);
                            playerTurn = 2;
                        } else {
                            player2Ended = true;
                            gameBoard.unfreeze(2);
                            player2.getHero().setFrozen(0);
                            playerTurn = 1;
                        }
                        // preparing a new round
                        if (player1Ended == true && player2Ended == true) {
                            player1Ended = false;
                            player2Ended = false;
                            if (player1.getDeck().getSize() != 0) {
                                player1.addToHand(player1.getDeck().drawCard());
                                player2.addToHand(player2.getDeck().drawCard());
                            }
                            manaToReceive++;
                            player1.setMana(player1.getMana() + Math.min(manaToReceive,10));
                            player2.setMana(player2.getMana() + Math.min(manaToReceive,10));
                        }
                        break;
                    case "placeCard" :
                        int handIdx =  actions.get(j).getHandIdx();
                        Player player;

                        if (playerTurn == 1) {
                            player = player1;
                        } else {
                            player = player2;
                        }


                        //checking errors

                        if(player.getCardsInHand().get(handIdx).getName().equals("Firestorm")||
                                player.getCardsInHand().get(handIdx).getName().equals("Winterfell") ||
                                player.getCardsInHand().get(handIdx).getName().equals("Heart Hound")) {
                            commandPlaceCard commandPlace = new commandPlaceCard(command, handIdx, "Cannot place environment card on table.");
                            output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                        } else if (player.getCardsInHand().get(handIdx).getMana() >
                                    player.getMana()) {
                            commandPlaceCard commandPlace = new commandPlaceCard(command, handIdx, "Not enough mana to place card on table.");
                            output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                        } else {
                            int success = gameBoard.placeCard(((MinionCard) player.getCardsInHand().get(handIdx)).getBoardPosition(),
                                                 (MinionCard)(player.getCardsInHand().get(handIdx)));
                            if (success != 1) {
                                commandPlaceCard commandPlace = new commandPlaceCard(command, handIdx, "Cannot place card on table since row is full.");
                                output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                            } else {
                                player.setMana(player.getMana() - player.getCardsInHand().get(handIdx).getMana());
                                player.getCardsInHand().remove(handIdx);

                            }
                        }
                        break;
                    case "getPlayerMana":
                        int playerIdx =  actions.get(j).getPlayerIdx();
                        commandGetPlayerMana commandPlace;
                        if (playerIdx == 1) {
                            commandPlace = new commandGetPlayerMana(command, playerIdx, player1.getMana());
                        } else {
                            commandPlace = new commandGetPlayerMana(command, playerIdx, player2.getMana());
                        }
                        output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                        break;
                    case "getCardsOnTable":
                        commandgetCardsOnTable commandGet = new commandgetCardsOnTable(command, gameBoard.getGameBoard());
                        output.add(objectMapper.convertValue(commandGet, JsonNode.class));
                        break;
                    case "getCardAtPosition":
                        int x = actions.get(j).getX();
                        int y = actions.get(j).getY();

                        Card card = gameBoard.getCard(x,y);
                        commandGetCardAtPos commandGetCard;
                        commandGetCardAtPosError commandGetCardAtError;

                        if (card == null) {
                            commandGetCardAtError = new commandGetCardAtPosError(command, "No card available at that position.", x, y);
                            output.add(objectMapper.convertValue(commandGetCardAtError, JsonNode.class));
                        } else {
                            commandGetCard = new commandGetCardAtPos(command,card,x,y);
                            output.add(objectMapper.convertValue(commandGetCard, JsonNode.class));
                        }



                        break;
                    case "getEnvironmentCardsInHand":
                        playerIdx =  actions.get(j).getPlayerIdx();
                        commandGetEnvCards getEnvCards;
                        if (playerIdx == 1) {
                            getEnvCards = new commandGetEnvCards(command,playerIdx,player1.getEnvironmentCards());
                        } else {
                            getEnvCards = new commandGetEnvCards(command,playerIdx,player2.getEnvironmentCards());
                        }
                        output.add(objectMapper.convertValue(getEnvCards, JsonNode.class));
                        break;
                    case "useEnvironmentCard":
                        handIdx = actions.get(j).getHandIdx();
                        int affectedRow = actions.get(j).getAffectedRow();

                        if (playerTurn == 1) {
                            player = player1;
                        } else {
                            player = player2;
                        }

                        if (!player.getCardsInHand().get(handIdx).getName().equals("Firestorm") &&
                                !player.getCardsInHand().get(handIdx).getName().equals("Winterfell") &&
                                !player.getCardsInHand().get(handIdx).getName().equals("Heart Hound")) {
                            commandUseEnvCard useEnvCard = new commandUseEnvCard(command, handIdx, affectedRow, "Chosen card is not of type environment.");
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if (player.getCardsInHand().get(handIdx).getMana() >
                                player.getMana()) {
                            commandUseEnvCard useEnvCard = new commandUseEnvCard(command, handIdx, affectedRow, "Not enough mana to use environment card.");
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if ((playerTurn == 1 && affectedRow >= 2) || (playerTurn == 2 && affectedRow < 2)) {
                            commandUseEnvCard useEnvCard = new commandUseEnvCard(command, handIdx, affectedRow, "Chosen row does not belong to the enemy.");
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if (player.getCardsInHand().get(handIdx).getName().equals("Heart Hound") &&
                                    gameBoard.getRow(2 * (playerTurn % 2)+(1-(affectedRow%2))).size()==5) {
                            commandUseEnvCard useEnvCard = new commandUseEnvCard(command, handIdx, affectedRow, "Cannot steal enemy card since the player's row is full.");
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else {

                            String cardName = player.getCardsInHand().get(handIdx).getName();

                            switch (cardName) {
                                case "Firestorm":
                                    ((Firestorm) player.getCardsInHand().get(handIdx)).useAbility(affectedRow);
                                    break;
                                case "Winterfell":
                                    ((Winterfell) player.getCardsInHand().get(handIdx)).useAbility(affectedRow);
                                    break;
                                case "Heart Hound":
                                    ((HeartHound) player.getCardsInHand().get(handIdx)).useAbility(affectedRow, 2 * (playerTurn % 2)+1-(affectedRow%2));
                                    break;
                        }
                            player.setMana(player.getMana()-player.getCardsInHand().get(handIdx).getMana());
                            player.getCardsInHand().remove(handIdx);
                        }

                        break;
                    case "getFrozenCardsOnTable":
                        commandGetFrozenTable frozenTable = new commandGetFrozenTable(command, gameBoard.getFrozenCards());
                        output.add(objectMapper.convertValue(frozenTable, JsonNode.class));
                        break;
                    case "cardUsesAttack":



                        int cardAttackerX = actions.get(j).getCardAttacker().getX();
                        int cardAttackerY = actions.get(j).getCardAttacker().getY();
                        int cardAttackedX = actions.get(j).getCardAttacked().getX();
                        int cardAttackedY = actions.get(j).getCardAttacked().getY();
                        commandCardUsesAttack cardUsesAttack;




                        if (gameBoard.getCard(cardAttackerX,cardAttackerY) != null &&
                                gameBoard.getCard(cardAttackedX,cardAttackedY) != null) {
                                    gameBoard.getCard(cardAttackerX,cardAttackerY).getAttackDamage();
                            if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 1) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacker card is frozen.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 2) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacker card has already attacked this turn.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else if ((playerTurn == 1 && cardAttackedX > 1) || (playerTurn == 2 && cardAttackedX <= 1)) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacked card does not belong to the enemy.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else {
                                boolean rowHasTank = gameBoard.containsTank(playerTurn);

                                if (rowHasTank == true && !gameBoard.getCard(cardAttackedX, cardAttackedY).getName().equals("Goliath")
                                        && !gameBoard.getCard(cardAttackedX, cardAttackedY).getName().equals("Warden")) {
                                    cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                            cardAttackedY, "Attacked card is not of type 'Tank'.");
                                    output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                                } else {
                                    gameBoard.getCard(cardAttackedX, cardAttackedY).setHealth(gameBoard.getCard(cardAttackedX, cardAttackedY).getHealth() - gameBoard.getCard(cardAttackerX, cardAttackerY).getAttackDamage());
                                    gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                    gameBoard.cleanRow(cardAttackedX);


                                }
                            }
                        }


                        break;
                    case "cardUsesAbility":
                        cardAttackerX = actions.get(j).getCardAttacker().getX();
                        cardAttackerY = actions.get(j).getCardAttacker().getY();
                        cardAttackedX = actions.get(j).getCardAttacked().getX();
                        cardAttackedY = actions.get(j).getCardAttacked().getY();


                        if (gameBoard.getCard(cardAttackerX,cardAttackerY) != null &&
                                gameBoard.getCard(cardAttackedX,cardAttackedY) != null) {
                                    gameBoard.getCard(cardAttackerX,cardAttackerY).getAttackDamage();
                            if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 1) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacker card is frozen.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 2) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacker card has already attacked this turn.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else if ((playerTurn == 1 && cardAttackedX > 1) || (playerTurn == 2 && cardAttackedX <= 1)
                                        && !gameBoard.getCard(cardAttackerX, cardAttackerY).getName().equals("Disciple")) {
                                cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacked card does not belong to the enemy.");
                                output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                            } else {
                                if (gameBoard.getCard(cardAttackerX, cardAttackerY).getName().equals("Disciple")) {
                                    if ((playerTurn == 1 && cardAttackedX <= 1) || (playerTurn == 2 && cardAttackedX > 1)) {
                                        cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                                cardAttackedY, "Attacked card does not belong to the current player.");
                                        output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                                    } else {
                                    ((Disciple) gameBoard.getCard(cardAttackerX, cardAttackerY)).cardUsesAbility(cardAttackedX, cardAttackedY);
                                    gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                    }
                                } else {
                                    boolean rowHasTank = gameBoard.containsTank(playerTurn);

                                    if (rowHasTank == true && !gameBoard.getCard(cardAttackedX, cardAttackedY).getName().equals("Goliath")
                                            && !gameBoard.getCard(cardAttackedX, cardAttackedY).getName().equals("Warden")) {
                                        cardUsesAttack = new commandCardUsesAttack(command, cardAttackerX, cardAttackerY, cardAttackedX,
                                                cardAttackedY, "Attacked card is not of type 'Tank'.");
                                        output.add(objectMapper.convertValue(cardUsesAttack, JsonNode.class));
                                    } else {
                                        String name =  gameBoard.getCard(cardAttackerX, cardAttackerY).getName();

                                        switch (name) {
                                            case "The Ripper":
                                                ((TheRipper) gameBoard.getCard(cardAttackerX, cardAttackerY)).cardUsesAbility(cardAttackedX,cardAttackedY);
                                                gameBoard.cleanRow(cardAttackedX);
                                                break;
                                            case "Miraj":
                                                ((Miraj) gameBoard.getCard(cardAttackerX, cardAttackerY)).cardUsesAbility(cardAttackedX, cardAttackedY);
                                                break;
                                            case "The Cursed One":
                                                ((TheCursedOne) gameBoard.getCard(cardAttackerX, cardAttackerY)).cardUsesAbility(cardAttackedX,cardAttackedY);
                                                gameBoard.cleanRow(cardAttackedX);
                                                break;
                                        }
                                        gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                        gameBoard.cleanRow(cardAttackedX);
                                    }
                                }

                            }
                        }

                        break;
                    case "useAttackHero":
                        cardAttackerX = actions.get(j).getCardAttacker().getX();
                        cardAttackerY = actions.get(j).getCardAttacker().getY();
                        commandUseAttackHero useAttackHero;
                        if (gameBoard.getCard(cardAttackerX,cardAttackerY) != null) {
                            if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 1) {
                                useAttackHero = new commandUseAttackHero(command, cardAttackerX, cardAttackerY, "Attacker card is frozen.", null);
                                output.add(objectMapper.convertValue(useAttackHero, JsonNode.class));
                            } else if (gameBoard.getCard(cardAttackerX, cardAttackerY).getFrozen() == 2) {
                                useAttackHero = new commandUseAttackHero(command, cardAttackerX, cardAttackerY, "Attacker card has already attacked this turn.", null);
                                output.add(objectMapper.convertValue(useAttackHero, JsonNode.class));
                            } else {
                                boolean rowHasTank = gameBoard.containsTank(playerTurn);
                                if (rowHasTank == true) {
                                    useAttackHero = new commandUseAttackHero(command, cardAttackerX, cardAttackerY, "Attacked card is not of type 'Tank'.", null);
                                    output.add(objectMapper.convertValue(useAttackHero, JsonNode.class));
                                } else {
                                        if (playerTurn == 1) {
                                            player2.getHero().setHealth(player2.getHero().getHealth() -
                                                                        gameBoard.getCard(cardAttackerX, cardAttackerY).getAttackDamage());
                                            if (player2.getHero().getHealth() <= 0) {
                                                commandGameEnded gameEnded = new commandGameEnded("Player one killed the enemy hero.");
                                                output.add(objectMapper.convertValue(gameEnded, JsonNode.class));
                                                player1Wins++;
                                                player1.setGamesWon(player1Wins);
//                                                player1.setGamesPlayed(player1.getGamesPlayed() + 1);
//                                                player2.setGamesPlayed(player2.getGamesPlayed() + 1);
//                                                gameInProgress = false;
                                            }
                                        } else {
                                            player1.getHero().setHealth(player1.getHero().getHealth() -
                                                    gameBoard.getCard(cardAttackerX, cardAttackerY).getAttackDamage());
                                            if (player1.getHero().getHealth() <= 0) {
                                                commandGameEnded gameEnded = new commandGameEnded("Player two killed the enemy hero.");
                                                output.add(objectMapper.convertValue(gameEnded, JsonNode.class));
                                                player2Wins++;
                                                player2.setGamesWon(player2Wins);
//                                                player1.setGamesPlayed(player1.getGamesPlayed() + 1);
//                                                player2.setGamesPlayed(player2.getGamesPlayed() + 1);
//                                                gameInProgress = false;
                                            }
                                        }

                                    gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                }
                            }

                        }
                        break;
                    case "useHeroAbility":

                        commandUseHeroAbility useHeroAbility;
                        affectedRow = actions.get(j).getAffectedRow();

                        if (playerTurn == 1) {
                            player  = player1;
                        } else {
                            player = player2;
                        }



                        if (player.getMana() < player.getHero().getMana()) {
                            useHeroAbility = new commandUseHeroAbility(command, affectedRow,  "Not enough mana to use hero's ability.");
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if (player.getHero().getFrozen() == 1) {
                            useHeroAbility = new commandUseHeroAbility(command, affectedRow,  "Hero has already attacked this turn.");
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if ((player.getHero().getName().equals("Lord Royce") || player.getHero().getName().equals("Empress Thorina")) &&
                                ((playerTurn == 1 && affectedRow >= 2) || (playerTurn == 2 && affectedRow < 2))) {
                            useHeroAbility = new commandUseHeroAbility(command, affectedRow,  "Selected row does not belong to the enemy.");
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if ((player.getHero().getName().equals("General Kocioraw") || player.getHero().getName().equals("King Mudface")) &&
                                ((playerTurn == 1 && affectedRow < 2) || (playerTurn == 2 && affectedRow >= 2))) {
                            useHeroAbility = new commandUseHeroAbility(command, affectedRow,  "Selected row does not belong to the current player.");
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else {
                            player.getHero().useHeroAbility(affectedRow);
                            player.setMana(player.getMana() - player.getHero().getMana());
                            player.getHero().setFrozen(1);
                        }



                        break;
                    case "getTotalGamesPlayed":
                        commandGetGames getGames = new commandGetGames(command, player1.getGamesPlayed());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                    case "getPlayerOneWins":
                        getGames = new commandGetGames(command, player1.getGamesWon());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                    case "getPlayerTwoWins":
                        getGames = new commandGetGames(command, player2.getGamesWon());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                    case "someActionIdk":
                        break;
                }





            }




            //JsonNode node = objectMapper.convertValue(games.get(0).getActions().get(0), JsonNode.class);
            //output.add(node);



            // emptying the gameboard for the next game
            gameBoard.setInstance(null);


            player1.setMana(0);
            player2.setMana(0);
        }


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }



}

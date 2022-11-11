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

        Player player1 = new Player(0,0, playerOneDecksInput.getNrDecks(), playerOneDecksInput, 1);
        Player player2 = new Player(0,0, playerTwoDecksInput.getNrDecks(), playerTwoDecksInput, 2);

        players.add(player1);
        players.add(player2);

        GameBoard gameBoard;


        // starting each game

        for (int i = 0; i < games.size(); i++) {

            gameBoard = GameBoard.getInstance();

            StartGameInput startGame = games.get(i).getStartGame();
            int seed = startGame.getShuffleSeed();
            int manaToReceive = 1;

            // used later to apply commands on a specific player
            int id;

            // aux variables to check which players ended their turns
            boolean player1Ended = false;
            boolean player2Ended = false;

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
                player1.addInHand(player1.getDeck().drawCard());
                player2.addInHand(player2.getDeck().drawCard());
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






            for (int j = 0; j < actions.size(); j++) {
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
                            playerTurn = 2;
                        } else {
                            player2Ended = true;
                            playerTurn = 1;
                        }
                        // preparing a new round
                        if (player1Ended == true && player2Ended == true) {
                            player1Ended = false;
                            player2Ended = false;
                            if (player1.getDeck().getSize() != 0) {
                                player1.addInHand(player1.getDeck().drawCard());
                                player2.addInHand(player2.getDeck().drawCard());
                            }
                            gameBoard.unfreezeAll();
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

                        if(player.getCardsInHand().get(handIdx).getName() == "Firestorm" ||
                                player.getCardsInHand().get(handIdx).getName() == "Winterfell" ||
                                player.getCardsInHand().get(handIdx).getName() == "Heart Hound") {
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

                        if (card == null) {
                            commandGetCard = new commandGetCardAtPos(command,null, "No card at that position.");
                        } else {
                            commandGetCard = new commandGetCardAtPos(command,card, null);
                        }

                        output.add(objectMapper.convertValue(commandGetCard, JsonNode.class));



                        break;
                    case "someActionIdk":
                        break;
                }





            }




            //JsonNode node = objectMapper.convertValue(games.get(0).getActions().get(0), JsonNode.class);
            //output.add(node);



            // emptying the gameboard for the next game
            gameBoard.setInstance(null);
        }



        //player1.copyDeck(0);

        //player1.printDeck();













        //ArrayList<ArrayList<CardInput>> cardInput = decksInput.getDecks();
        //CardInput firstCard = cardInput.get(0).get(0);

        //MinionCard card = new MinionCard(firstCard.getMana(), firstCard.getDescription(), firstCard.getColors(), firstCard.getName(),
                                        //firstCard.getHealth(), firstCard.getAttackDamage(), 1);



        //JsonNode node = objectMapper.convertValue(player1, JsonNode.class);

        //output.add(node);

        //System.out.println("---PRINTING CARD----");
        //System.out.println(card);



        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }



}

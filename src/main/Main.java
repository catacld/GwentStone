package main;

import checker.Checker;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import com.sun.security.jgss.GSSUtil;
import fileio.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ArrayList;
import fileio.DecksInput;
import fileio.CardInput;
import main.cards.Card;
import main.cards.MinionCard;
import main.cards.minionCards.Disciple;

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

        Player player1 = new Player(0,0, playerOneDecksInput.getNrDecks());
        Player player2 = new Player(0,0, playerTwoDecksInput.getNrDecks());

        for (int i = 0; i < playerOneDecksInput.getNrDecks(); i++) {
            player1.addDeck(playerOneDecksInput.getDecks().get(i), 1);
            player2.addDeck(playerTwoDecksInput.getDecks().get(i), 2);
        }








        //ArrayList<ArrayList<CardInput>> cardInput = decksInput.getDecks();
        //CardInput firstCard = cardInput.get(0).get(0);

        //MinionCard card = new MinionCard(firstCard.getMana(), firstCard.getDescription(), firstCard.getColors(), firstCard.getName(),
                                        //firstCard.getHealth(), firstCard.getAttackDamage(), 1);



        //JsonNode node = objectMapper.convertValue(card, JsonNode.class);

        //output.add(node);

        //System.out.println("---PRINTING CARD----");
        //System.out.println(card);



        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}

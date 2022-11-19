package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;
import main.cards.Card;
import main.cards.HeroCard;
import main.cards.MinionCard;
import main.cards.environmentCards.Firestorm;
import main.cards.environmentCards.HeartHound;
import main.cards.environmentCards.Winterfell;
import main.cards.minionCards.Disciple;
import main.cards.minionCards.Miraj;
import main.cards.minionCards.TheCursedOne;
import main.cards.minionCards.TheRipper;
import main.outputClasses.*;

import java.util.ArrayList;

public class GameEngine {

    private final ObjectMapper objectMapper;

    private final Input inputData;

    private final ArrayNode output;

    public GameEngine(final ObjectMapper objectMapper, final Input inputData,
                      final ArrayNode output) {
        this.objectMapper = objectMapper;
        this.inputData = inputData;
        this.output = output;
    }

    /**
     * start the games
     */
    public void start() {
        ArrayList<Player> players = new ArrayList<Player>();

        DecksInput playerOneDecksInput = inputData.getPlayerOneDecks();
        DecksInput playerTwoDecksInput = inputData.getPlayerTwoDecks();
        ArrayList<GameInput> games = inputData.getGames();

        GameBoard gameBoard;

        int gamesPlayed = 0;
        int player1Wins = 0;
        int player2Wins = 0;

        // start each game
        for (int i = 0; i < games.size(); i++) {

            gamesPlayed++;
            StartGameInput startGame = games.get(i).getStartGame();
            int seed = startGame.getShuffleSeed();

            Player player1 = new Player(gamesPlayed, player1Wins, playerOneDecksInput.getNrDecks(),
                                        playerOneDecksInput, 1);
            Player player2 = new Player(gamesPlayed, player2Wins, playerTwoDecksInput.getNrDecks(),
                                        playerTwoDecksInput, 2);

            // preparation at the beginning of each game
            player1.startNewGame(startGame.getPlayerOneDeckIdx(),
                                 seed, startGame.getPlayerOneHero());
            player2.startNewGame(startGame.getPlayerTwoDeckIdx(),
                                 seed, startGame.getPlayerTwoHero());

            // clear the list of players
            players.clear();
            // add the new players
            players.add(player1);
            players.add(player2);

            gameBoard = GameBoard.getInstance();

            // used to print errors
            String error = null;


            // counter to keep track of the amount of mana
            // that players receive at the beginning of
            // each round
            int manaToReceive = 1;

            // used later to apply commands on a specific player
            int playerId;

            // check how many players have played their turn
            int turnsPlayed = 0;

            // decide the order in which the game will be played
            int playerTurn = startGame.getStartingPlayer();

            ArrayList<ActionsInput> actions = games.get(i).getActions();

            for (int j = 0; j < actions.size(); j++) {

                String command = actions.get(j).getCommand();

                switch (command) {
                    default:
                        break;
                    case "getPlayerDeck":
                        // id of the player whose deck will be printed
                        playerId = actions.get(j).getPlayerIdx();
                        // the deck of the player
                        ArrayList<Card> deck = players.get(playerId - 1).getDeck().getDeck();
                        // print the deck
                        CommandGetDeck printDeck = new CommandGetDeck(command, deck, playerId);
                        output.add(objectMapper.convertValue(printDeck, JsonNode.class));
                        break;
                    case "getPlayerHero":
                        // id of the player whose hero will be printed
                        playerId = actions.get(j).getPlayerIdx();
                        // the hero of the player
                        HeroCard hero = players.get(playerId - 1).getHero();
                        // print the hero
                        CommandOutputHero printHero = new CommandOutputHero(command,
                                                                            hero, playerId);
                        output.add(objectMapper.convertValue(printHero, JsonNode.class));
                        break;
                    case "getCardsInHand":
                        // id of the player whose hand will be printed
                        playerId = actions.get(j).getPlayerIdx();
                        // get the cards in the player's hand
                        ArrayList<Card> cards = players.get(playerId - 1).getCardsInHand();
                        // print the cards in the player's hand
                        CommandgetCardsInHand printHand = new CommandgetCardsInHand(command,
                                                                                    playerId,
                                                                                    cards);
                        output.add(objectMapper.convertValue(printHand, JsonNode.class));
                        break;
                    case "getPlayerTurn":
                        // print the active player
                        CommandPlayerTurn printTurn = new CommandPlayerTurn(command, playerTurn);
                        output.add(objectMapper.convertValue(printTurn, JsonNode.class));
                        break;
                    case "endPlayerTurn":
                        // unfreeze the cards of the player whose turn ended
                        gameBoard.unfreeze(playerTurn);
                        // increase counter of turns that ended
                        turnsPlayed++;
                        // change the active player
                        playerTurn = playerTurn % 2 + 1;
                        // if both players have played their turn
                        // a new round starts
                        if (turnsPlayed == 2) {
                            turnsPlayed = 0;
                            manaToReceive++;
                            player1.startNewRound(manaToReceive);
                            player2.startNewRound(manaToReceive);
                        }
                        break;
                    case "placeCard" :
                        // the index of the card in the player's hand
                        // to be placed
                        int cardIdx =  actions.get(j).getHandIdx();
                        // the player that places the card
                        Player activePlayer = players.get(playerTurn - 1);
                        // the card to be placed
                        Card cardToPlace = activePlayer.getCardsInHand().get(cardIdx);
                        // check if the card is an environment card
                        if (cardToPlace.isEnvironmentCard()) {
                            error = "Cannot place environment card on table.";
                            CommandPlaceCard commandPlace = new CommandPlaceCard(command,
                                                                                 cardIdx, error);
                            output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                        } else if (!activePlayer.hasMana(cardToPlace)) {
                            // check if the player does not have enough mana to place the card
                            error = "Not enough mana to place card on table.";
                            CommandPlaceCard commandPlace = new CommandPlaceCard(command,
                                                                                 cardIdx, error);
                            output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                        } else {
                            // try to place the card
                            int success = gameBoard.placeCard(
                                          ((MinionCard) cardToPlace).getBoardPosition(),
                                          (MinionCard) cardToPlace);
                            // the card could not be placed since the row was full
                            if (success != 1) {
                                error = "Cannot place card on table since row is full.";
                                CommandPlaceCard commandPlace = new CommandPlaceCard(command,
                                                                    cardIdx, error);
                                output.add(objectMapper.convertValue(commandPlace, JsonNode.class));
                            } else {
                                // the card was successfully placed
                                activePlayer.setMana(activePlayer.getMana()
                                                     - cardToPlace.getMana());
                                activePlayer.getCardsInHand().remove(cardIdx);
                            }
                        }
                        break;
                    case "getPlayerMana":
                        // the id of the player whose mana will be printed
                        playerId =  actions.get(j).getPlayerIdx();
                        // the player whose mana will be printed
                        Player player = players.get(playerId - 1);
                        // print the mana
                        CommandGetPlayerMana printMana;
                        printMana = new CommandGetPlayerMana(command, playerId, player.getMana());
                        output.add(objectMapper.convertValue(printMana, JsonNode.class));
                        break;
                    case "getCardsOnTable":
                        // print the cards on the game board
                        CommandgetCardsOnTable commandGet = new CommandgetCardsOnTable(command,
                                                                gameBoard.getGameBoard());
                        output.add(objectMapper.convertValue(commandGet, JsonNode.class));
                        break;
                    case "getCardAtPosition":
                        // the coordinates of the card
                        int x = actions.get(j).getX();
                        int y = actions.get(j).getY();
                        // the card placed at (x,y)
                        Card card = gameBoard.getCard(x, y);
                        // if there is no card placed at (x,y)
                        // print an error message
                        if (card == null) {
                            CommandGetCardAtPosError printCardError =
                                    new CommandGetCardAtPosError(command,
                                    "No card available at that position.", x, y);
                            output.add(objectMapper.convertValue(printCardError, JsonNode.class));
                        } else { // else print the card
                            CommandGetCardAtPos printCard = new CommandGetCardAtPos(command, card,
                                                                x, y);
                            output.add(objectMapper.convertValue(printCard, JsonNode.class));
                        }
                        break;
                    case "getEnvironmentCardsInHand":
                        // the id of the player whose environment cards will be printed
                        playerId =  actions.get(j).getPlayerIdx();
                        // the player whose environment cards will be printed
                        activePlayer = players.get(playerId - 1);
                        // the environment cards that will be printed
                        ArrayList<Card> environmentCards = activePlayer.getEnvironmentCards();
                        // print the environment cards in the player's hand
                        CommandGetEnvCards getEnvCards = new CommandGetEnvCards(command,
                                                             playerId, environmentCards);
                        output.add(objectMapper.convertValue(getEnvCards, JsonNode.class));
                        break;
                    case "useEnvironmentCard":
                        // the index of the environment card from the
                        // player's hand that will be used
                        cardIdx = actions.get(j).getHandIdx();
                        // the index of the row that will be affected by
                        // the card's ability
                        int affectedRow = actions.get(j).getAffectedRow();
                        // the player that uses the card
                        activePlayer = players.get(playerTurn - 1);
                        // the card to be used
                        Card cardToUse = activePlayer.getCardsInHand().get(cardIdx);
                        // check if the card is not an environment card
                        if (!cardToUse.isEnvironmentCard()) {
                            error = "Chosen card is not of type environment.";
                            CommandUseEnvCard useEnvCard = new CommandUseEnvCard(command, cardIdx,
                                                                affectedRow, error);
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if (!activePlayer.hasMana(cardToUse)) {
                            // check if the player does not have enough mana to use the ability
                            error = "Not enough mana to use environment card.";
                            CommandUseEnvCard useEnvCard = new CommandUseEnvCard(command, cardIdx,
                                                            affectedRow, error);
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if ((playerTurn == 1 && affectedRow >= 2)
                                    || (playerTurn == 2 && affectedRow < 2)) {
                            // check if the row is not an enemy row
                            error = "Chosen row does not belong to the enemy.";
                            CommandUseEnvCard useEnvCard = new CommandUseEnvCard(command, cardIdx,
                                                                affectedRow, error);
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else if (cardToUse.getName().equals("Heart Hound")
                                && gameBoard.getRow(2 * (playerTurn % 2)
                                                    + (1 - (affectedRow % 2))).size() == 5) {
                            // check if the player's row has enough space to steal the card
                            error = "Cannot steal enemy card since the player's row is full.";
                            CommandUseEnvCard useEnvCard = new CommandUseEnvCard(command, cardIdx,
                                                                                 affectedRow,
                                                                                 error);
                            output.add(objectMapper.convertValue(useEnvCard, JsonNode.class));
                        } else {
                            // use the card's ability
                            String cardName = cardToUse.getName();

                            switch (cardName) {
                                default:
                                    break;
                                case "Firestorm":
                                    ((Firestorm) cardToUse).useAbility(affectedRow);
                                    break;
                                case "Winterfell":
                                    ((Winterfell) cardToUse).useAbility(affectedRow);
                                    break;
                                case "Heart Hound":
                                    ((HeartHound) cardToUse).useAbility(affectedRow,
                                                                2 * (playerTurn % 2) + 1
                                                                        - (affectedRow % 2));
                                    break;
                            }
                            // decrease the player's mana after using the ability
                            activePlayer.setMana(activePlayer.getMana() - cardToUse.getMana());
                            // delete the card from the player's hand
                            activePlayer.getCardsInHand().remove(cardIdx);
                        }
                        break;
                    case "getFrozenCardsOnTable":
                        // get the frozen cards on the table
                        CommandGetFrozenTable frozenTable = new CommandGetFrozenTable(command,
                                                                gameBoard.getFrozenCards());
                        output.add(objectMapper.convertValue(frozenTable, JsonNode.class));
                        break;
                    case "cardUsesAttack":
                        // get the coordinates of the attacker card
                        int cardAttackerX = actions.get(j).getCardAttacker().getX();
                        int cardAttackerY = actions.get(j).getCardAttacker().getY();
                        // get the coordinates of the attacked card
                        int cardAttackedX = actions.get(j).getCardAttacked().getX();
                        int cardAttackedY = actions.get(j).getCardAttacked().getY();
                        // the attacker card
                        MinionCard attackerCard = gameBoard.getCard(cardAttackerX, cardAttackerY);
                        // the attacked card
                        MinionCard attackedCard = gameBoard.getCard(cardAttackedX, cardAttackedY);
                        CommandCardUsesAttack cardUsesAttack;

                        // check that cards exist at both pair of coordinates
                        if (gameBoard.getCard(cardAttackerX, cardAttackerY) != null
                                && gameBoard.getCard(cardAttackedX, cardAttackedY) != null) {
                            // check if the attacker card is frozen
                            if (attackerCard.getFrozen() == 1) {
                                error = "Attacker card is frozen.";
                                cardUsesAttack = new CommandCardUsesAttack(command,
                                                     cardAttackerX, cardAttackerY,
                                                     cardAttackedX, cardAttackedY, error);
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                     JsonNode.class));
                            } else if (attackerCard.getFrozen() == 2) {
                                // check if the attacker card has already attacked
                                error = "Attacker card has already attacked this turn.";
                                cardUsesAttack = new CommandCardUsesAttack(command, cardAttackerX,
                                                     cardAttackerY, cardAttackedX,
                                                     cardAttackedY, error);
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                    JsonNode.class));
                            } else if ((playerTurn == 1 && cardAttackedX > 1)
                                       || (playerTurn == 2 && cardAttackedX <= 1)) {
                                // check that the attacked card is an enemy
                                error = "Attacked card does not belong to the enemy.";
                                cardUsesAttack = new CommandCardUsesAttack(command, cardAttackerX,
                                                     cardAttackerY, cardAttackedX,
                                                     cardAttackedY,
                                                     error);
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                     JsonNode.class));
                            } else {
                                boolean playerPlacedTank = gameBoard.containsTank(playerTurn);

                                // check if the enemy player has a tank card placed
                                // on the game board but the target is not a tank card
                                if (playerPlacedTank && !attackedCard.isTank()) {
                                    error = "Attacked card is not of type 'Tank'.";
                                    cardUsesAttack = new CommandCardUsesAttack(command,
                                                         cardAttackerX, cardAttackerY,
                                                         cardAttackedX, cardAttackedY,
                                                         error);
                                    output.add(objectMapper.convertValue(cardUsesAttack,
                                                                         JsonNode.class));
                                } else {
                                    // attack the target
                                    attackedCard.setHealth(attackedCard.getHealth()
                                                           - attackerCard.getAttackDamage());
                                    // the attacker card can no longer attack during this turn
                                    gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                    // delete the card if it has no health points left
                                    gameBoard.cleanRow(cardAttackedX);
                                }
                            }
                        }
                        break;
                    case "cardUsesAbility":
                        // get the coordinates of the attacker card
                        cardAttackerX = actions.get(j).getCardAttacker().getX();
                        cardAttackerY = actions.get(j).getCardAttacker().getY();
                        // get the coordinates of the attacked card
                        cardAttackedX = actions.get(j).getCardAttacked().getX();
                        cardAttackedY = actions.get(j).getCardAttacked().getY();
                        // the attacker card
                        attackerCard = gameBoard.getCard(cardAttackerX, cardAttackerY);
                        // the attacked card
                        attackedCard = gameBoard.getCard(cardAttackedX, cardAttackedY);

                        // check that cards exist at both pair of coordinates
                        if (gameBoard.getCard(cardAttackerX, cardAttackerY) != null
                                && gameBoard.getCard(cardAttackedX, cardAttackedY) != null) {
                            // check if the attacker card is frozen
                            if (attackerCard.getFrozen() == 1) {
                                cardUsesAttack = new CommandCardUsesAttack(command, cardAttackerX,
                                                      cardAttackerY, cardAttackedX,
                                        cardAttackedY, "Attacker card is frozen.");
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                     JsonNode.class));
                            } else if (attackerCard.getFrozen() == 2) {
                                // check if the attacker card has already attacked/used
                                // its ability during this turn
                                error = "Attacker card has already attacked this turn.";
                                cardUsesAttack = new CommandCardUsesAttack(command, cardAttackerX,
                                        cardAttackerY, cardAttackedX,
                                        cardAttackedY, error);
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                     JsonNode.class));
                            } else if ((playerTurn == 1 && cardAttackedX > 1)
                                    || (playerTurn == 2 && cardAttackedX <= 1)
                                    && !attackerCard.getName().equals("Disciple")) {
                                // check that the attacked card is an enemy
                                error = "Attacked card does not belong to the enemy.";
                                cardUsesAttack = new CommandCardUsesAttack(command, cardAttackerX,
                                                     cardAttackerY, cardAttackedX,
                                                     cardAttackedY, error);
                                output.add(objectMapper.convertValue(cardUsesAttack,
                                                                     JsonNode.class));
                            } else {
                                // check that the card is friendly
                                if (attackerCard.getName().equals("Disciple")) {
                                    if ((playerTurn == 1 && cardAttackedX <= 1)
                                            || (playerTurn == 2
                                                && cardAttackedX > 1)) {
                                        error = "Attacked card does not belong"
                                                + " to the current player.";
                                        cardUsesAttack = new CommandCardUsesAttack(command,
                                                             cardAttackerX, cardAttackerY,
                                                             cardAttackedX, cardAttackedY,
                                                             error);
                                        output.add(objectMapper.convertValue(cardUsesAttack,
                                                                             JsonNode.class));
                                    } else {
                                        // use the card's ability
                                        ((Disciple) attackerCard).cardUsesAbility(cardAttackedX,
                                                                  cardAttackedY);
                                        attackerCard.setFrozen(2);
                                    }
                                } else {
                                    boolean playerPlacedTank = gameBoard.containsTank(playerTurn);

                                    // check if the enemy player has a tank card placed
                                    // on the game board but the target is not
                                    // a tank card
                                    if (playerPlacedTank && !attackedCard.isTank()) {
                                        error = "Attacked card is not of type 'Tank'.";
                                        cardUsesAttack = new CommandCardUsesAttack(command,
                                                             cardAttackerX, cardAttackerY,
                                                             cardAttackedX, cardAttackedY,
                                                             error);
                                        output.add(objectMapper.convertValue(cardUsesAttack,
                                                                             JsonNode.class));
                                    } else {
                                        // use the card's ability
                                        String name =  attackerCard.getName();

                                        switch (name) {
                                            default:
                                                break;
                                            case "The Ripper":
                                                ((TheRipper) attackerCard).cardUsesAbility(
                                                                           cardAttackedX,
                                                                           cardAttackedY);
                                                gameBoard.cleanRow(cardAttackedX);
                                                break;
                                            case "Miraj":
                                                ((Miraj) attackerCard).cardUsesAbility(
                                                                       cardAttackedX,
                                                                       cardAttackedY);
                                                break;
                                            case "The Cursed One":
                                                ((TheCursedOne) attackerCard).cardUsesAbility(
                                                                              cardAttackedX,
                                                                              cardAttackedY);
                                                gameBoard.cleanRow(cardAttackedX);
                                                break;
                                        }
                                        // the card can no longer attack/use its ability
                                        // during this turn
                                        attackerCard.setFrozen(2);
                                        // delete the attacked card if it no longer
                                        // has any health points
                                        gameBoard.cleanRow(cardAttackedX);
                                    }
                                }
                            }
                        }
                        break;
                    case "useAttackHero":
                        // get the coordinates of the attacker card
                        cardAttackerX = actions.get(j).getCardAttacker().getX();
                        cardAttackerY = actions.get(j).getCardAttacker().getY();
                        // get the attacker card
                        attackerCard = gameBoard.getCard(cardAttackerX, cardAttackerY);
                        CommandUseAttackHero useAttackHero;
                        // check that there is a card placed at (x,y)
                        if (attackerCard != null) {
                            // check if the attacker card is frozen
                            if (attackerCard.getFrozen() == 1) {
                                error = "Attacker card is frozen.";
                                useAttackHero = new CommandUseAttackHero(command, cardAttackerX,
                                                                         cardAttackerY, error,
                                                                         null);
                                output.add(objectMapper.convertValue(useAttackHero,
                                                                     JsonNode.class));
                            } else if (attackerCard.getFrozen() == 2) {
                                // check if the attacker has already attacked/used its
                                // ability during this turn
                                error = "Attacker card has already attacked this turn.";
                                useAttackHero = new CommandUseAttackHero(command, cardAttackerX,
                                                                         cardAttackerY, error,
                                                                         null);
                                output.add(objectMapper.convertValue(useAttackHero,
                                                                     JsonNode.class));
                            } else {
                                boolean playerPlacedTank = gameBoard.containsTank(playerTurn);
                                // check if the enemy has any tank cards placed on the game board
                                if (playerPlacedTank) {
                                    error = "Attacked card is not of type 'Tank'.";
                                    useAttackHero = new CommandUseAttackHero(command, cardAttackerX,
                                                                             cardAttackerY, error,
                                                                    null);
                                    output.add(objectMapper.convertValue(useAttackHero,
                                                                         JsonNode.class));
                                } else {
                                    if (playerTurn == 1) {
                                        player2.getHero().setHealth(player2.getHero().getHealth()
                                                - attackerCard.getAttackDamage());
                                        if (player2.getHero().getHealth() <= 0) {
                                            CommandGameEnded gameEnded = new CommandGameEnded(
                                                    "Player one killed the enemy hero.");
                                            output.add(objectMapper.convertValue(gameEnded,
                                                                                 JsonNode.class));
                                            player1Wins++;
                                            player1.setGamesWon(player1Wins);
                                        }
                                    } else {
                                        player1.getHero().setHealth(player1.getHero().getHealth()
                                                - attackerCard.getAttackDamage());
                                        if (player1.getHero().getHealth() <= 0) {
                                            CommandGameEnded gameEnded = new CommandGameEnded(
                                                    "Player two killed the enemy hero.");
                                            output.add(objectMapper.convertValue(gameEnded,
                                                                                 JsonNode.class));
                                            player2Wins++;
                                            player2.setGamesWon(
                                                    player2Wins);
                                        }
                                    }
                                    // the card can no longer attack/use
                                    // its ability during this turn
                                    gameBoard.getCard(cardAttackerX, cardAttackerY).setFrozen(2);
                                }
                            }
                        }
                        break;
                    case "useHeroAbility":

                        CommandUseHeroAbility useHeroAbility;
                        // the index of the row where the ability will be used
                        affectedRow = actions.get(j).getAffectedRow();

                        // the active player that uses its hero ability
                        activePlayer  = players.get(playerTurn - 1);

                        // the active player's hero
                        hero = activePlayer.getHero();

                        // check if the player has enough mana to use the ability
                        if (!activePlayer.hasMana(activePlayer.getHero())) {
                            error = "Not enough mana to use hero's ability.";
                            useHeroAbility = new CommandUseHeroAbility(command, affectedRow,
                                                                       error);
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if (activePlayer.getHero().getFrozen() == 1) {
                            // check if the hero has already attacked
                            error = "Hero has already attacked this turn.";
                            useHeroAbility = new CommandUseHeroAbility(command, affectedRow,
                                                                       error);
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if ((hero.getName().equals("Lord Royce")
                                    || activePlayer.getHero().getName().equals("Empress Thorina"))
                                    && ((playerTurn == 1 && affectedRow >= 2)
                                    || (playerTurn == 2 && affectedRow < 2))) {
                            error = "Selected row does not belong to the enemy.";
                            useHeroAbility = new CommandUseHeroAbility(command, affectedRow,
                                                                       error);
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else if ((hero.getName().equals("General Kocioraw")
                                    || activePlayer.getHero().getName().equals("King Mudface"))
                                    && ((playerTurn == 1 && affectedRow < 2)
                                    || (playerTurn == 2 && affectedRow >= 2))) {
                            error = "Selected row does not belong to the current player.";
                            useHeroAbility = new CommandUseHeroAbility(command, affectedRow,
                                                                       error);
                            output.add(objectMapper.convertValue(useHeroAbility, JsonNode.class));
                        } else {
                            // use the hero's ability
                            activePlayer.getHero().useHeroAbility(affectedRow);
                            // decrease the player's mana
                            activePlayer.setMana(activePlayer.getMana() - hero.getMana());
                            // the hero can no longer attack/use its ability during this turn
                            activePlayer.getHero().setFrozen(1);
                        }
                        break;
                    case "getTotalGamesPlayed":
                        // print the number of games played by the players
                        CommandGetGames getGames = new CommandGetGames(command,
                                                                       player1.getGamesPlayed());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                    case "getPlayerOneWins":
                        // print player one wins
                        getGames = new CommandGetGames(command, player1.getGamesWon());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                    case "getPlayerTwoWins":
                        // print player two wins
                        getGames = new CommandGetGames(command, player2.getGamesWon());
                        output.add(objectMapper.convertValue(getGames, JsonNode.class));
                        break;
                }

            }

            // emptying the game board for the next game
            gameBoard.setInstance(null);

        }

    }
}

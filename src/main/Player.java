package main;

import fileio.DecksInput;
import main.cards.Card;

import java.util.ArrayList;
import fileio.CardInput;
import main.cards.HeroCard;
import main.cards.heroes.EmpressThorina;
import main.cards.heroes.GeneralKocioraw;
import main.cards.heroes.KingMudface;
import main.cards.heroes.LordRoyce;

public class Player {

    // player id to check whether it is player 1 or player 2
    private final int id;

    // player's statistics
    private final int gamesPlayed;
    private int gamesWon;
    private int mana;
    private int numberOfDecks;


    // player's hero
    private HeroCard hero;

    // cards in player's hand
    private ArrayList<Card> cardsInHand;

    // player's available decks
    private ArrayList<Deck> decks;

    // deck selected for the current game
    private Deck deck;

    private DecksInput decksData;

    public Player(final int gamesPlayed, final int gamesWon, final int numberOfDecks,
                  final DecksInput decksData, final int id) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.mana = 1;
        this.numberOfDecks = numberOfDecks;
        this.cardsInHand = new ArrayList<>();
        this.decks = new ArrayList<Deck>();
        this.hero = null;
        this.decksData = decksData;
        this.id = id;
        this.deck = null;

        for (int i = 0; i < numberOfDecks; i++) {
            this.addDeck(decksData.getDecks().get(i), this.id);
        }
    }


    /**
     * called at the beginning of every game
     * to prepare the player
     */
    public final void startNewGame(final int deckIdx, final int shuffleSeed,
                                   final CardInput inputHero) {
        // assign the player the chosen deck
        this.setDeck(deckIdx);
        // shuffle the deck
        this.deck.shuffle(shuffleSeed);
        // assign the player the chosen hero
        this.setHero(inputHero);
        // draw a card at the beginning of the first round
        cardsInHand.add(deck.drawCard());

    }

    /**
     * method called at the beginning
     * of every round
     */
    public void startNewRound(final int manaToReceive) {
        // if there are cards left in the deck,
        // draw one at the beginning of each round
        if (this.deck.getSize() != 0) {
            cardsInHand.add(deck.drawCard());
        }
        // player gets mana at the beginning of each round,
        // but no more than 10
        this.mana += Math.min(manaToReceive, 10);
        // unfreeze the hero if it was frozen
        this.hero.setFrozen(0);
    }

    /**
     * add a new deck to the player's decks
     */
    public void addDeck(final ArrayList<CardInput> deckToAdd,
                        final int idOfDeck) {
        decks.add(new Deck(deckToAdd, idOfDeck));
    }

    public final int getMana() {
        return mana;
    }

    public final void setMana(final int mana) {
        this.mana = mana;
    }

    public final int getGamesPlayed() {
        return gamesPlayed;
    }

    public final int getGamesWon() {
        return gamesWon;
    }

    public final void setGamesWon(final int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public final ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }


    public final ArrayList<Deck> getDecks() {
        return decks;
    }

    public final void setDecks(final ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public final HeroCard getHero() {
        return hero;
    }

    /**
     * set the hero of the player
     */
    public final void setHero(final CardInput heroInput) {

        String name = heroInput.getName();
        HeroCard newHero = null;

        switch (name) {
            default:
                break;
            case "Lord Royce":
                newHero = new LordRoyce(heroInput.getMana(), heroInput.getDescription(),
                                        heroInput.getColors(), id);
                break;
            case "Empress Thorina":
                newHero = new EmpressThorina(heroInput.getMana(), heroInput.getDescription(),
                                             heroInput.getColors(), id);
                break;
            case "General Kocioraw":
                newHero = new GeneralKocioraw(heroInput.getMana(), heroInput.getDescription(),
                                              heroInput.getColors(), id);
                break;
            case "King Mudface":
                newHero = new KingMudface(heroInput.getMana(), heroInput.getDescription(),
                                          heroInput.getColors(), id);
                break;
        }

        this.hero = newHero;

    }

    public final Deck getDeck() {
        return deck;
    }


    /**
     * deep copy the deck of index "index"
     */
    public final void setDeck(final int index) {
        this.deck = new Deck(decks.get(index), this.id);
    }


    /**
     * get the environment cards that the player has in their hand
     */
    public final ArrayList<Card> getEnvironmentCards() {

        ArrayList<Card> environmentCards = new ArrayList<>();

        for (Card card : cardsInHand) {
            if (card.isEnvironmentCard()) {
                environmentCards.add(card);
            }
        }

        return environmentCards;
    }


    /**
     * check if the player has enough
     * mana to place a card
     */
    public final boolean hasMana(final Card card) {
        return this.mana >= card.getMana();
    }
}

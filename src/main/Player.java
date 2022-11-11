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

    // player id to check whether it is the player 1 or player 2
    private int id;

    // statistics
    private int gamesPlayed;
    private int gamesWon;
    private int mana;
    private int numberOfDecks;

    private HeroCard hero;

    // cards in player's hand
    private ArrayList<Card> cardsInHand;

    // player's available decks
    private ArrayList<Deck> decks;

    // deck selected for the game
    private Deck deck;

    private DecksInput decksData;

    public Player(int gamesPlayed, int gamesWon, int numberOfDecks, DecksInput decksData, int id) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        // each player will get mana at the beginning of every round
        this.mana = 0;
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

    public void addDeck(ArrayList<CardInput> deck, int id) {
        decks.add(new Deck(deck, id));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public HeroCard getHero() {
        return hero;
    }

    public void setHero(CardInput heroInput) {

        String name = heroInput.getName();
        HeroCard newHero = null;

        switch (name) {
            case "Lord Royce":
                newHero = new LordRoyce(heroInput.getMana(), heroInput.getDescription(), heroInput.getColors(), id);
                break;
            case "Empress Thorina":
                newHero = new EmpressThorina(heroInput.getMana(), heroInput.getDescription(), heroInput.getColors(), id);
                break;
            case "General Kocioraw":
                newHero = new GeneralKocioraw(heroInput.getMana(), heroInput.getDescription(), heroInput.getColors(), id);
                break;
            case "King Mudface":
                newHero = new KingMudface(heroInput.getMana(), heroInput.getDescription(), heroInput.getColors(), id);
                break;
        }

        this.hero = newHero;

    }

    public Deck getDeck() {
        return deck;
    }

    // deep copy the deck chosen
    public void setDeck(int index) {
        this.deck = new Deck(decks.get(index), this.id);
    }

    public void addInHand(Card card) {
        cardsInHand.add(card);
    }



    // debug purpose remove after hw is done
    public void printDeck() {
        deck.printCards();
    }

    public void printHero() {
        System.out.println(hero);
    }
}

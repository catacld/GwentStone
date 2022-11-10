package main;

import main.cards.Card;

import java.util.ArrayList;
import fileio.CardInput;
import main.cards.HeroCard;

public class Player {

    // player id to check whether it is the player 1 or player 2
    //private int final id;

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

    public Player(int gamesPlayed, int gamesWon, int numberOfDecks) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        // each player will get mana at the beginning of every round
        this.mana = 0;
        this.numberOfDecks = numberOfDecks;
        this.cardsInHand = new ArrayList<>();
        this.decks = new ArrayList<Deck>();
        this.hero = null;
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

    public void setHero(HeroCard hero) {
        this.hero = hero;
    }

    // debug purpose remove after hw is done
//    public void printDeck() {
//        decks.get(0).printCards();
//    }
}

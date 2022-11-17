package main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import main.cards.Card;

import java.util.*;

import fileio.CardInput;
import main.cards.environmentCards.Firestorm;
import main.cards.environmentCards.HeartHound;
import main.cards.environmentCards.Winterfell;
import main.cards.minionCards.*;

public class Deck {
    @JsonProperty("output")
    private ArrayList<Card> deck;

    public Deck(ArrayList<CardInput> inputDeck, int id) {
        this.deck = new ArrayList<>();

        for  (int i = 0; i < inputDeck.size(); i++) {

            // parse the data from the json input file
            CardInput readCard = inputDeck.get(i);
            String nameOfCard = readCard.getName();

            Card card = null;

            // instantiate a new card based on its name
            switch (nameOfCard) {
                case "Disciple":
                    card = new Disciple(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                                             readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Miraj":
                    card = new Miraj(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "The Ripper":
                    card = new TheRipper(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "The Cursed One":
                    card = new TheCursedOne(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Sentinel":
                    card = new Sentinel(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Berserker":
                    card = new Berserker(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Goliath":
                    card = new Goliath(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Warden":
                    card = new Warden(readCard.getMana(), readCard.getDescription(), readCard.getColors(),
                            readCard.getHealth(), readCard.getAttackDamage(), id );
                    break;
                case "Firestorm":
                    card = new Firestorm(readCard.getMana(), readCard.getDescription(), readCard.getColors(),id );
                    break;
                case "Winterfell":
                    card = new Winterfell(readCard.getMana(), readCard.getDescription(), readCard.getColors(),id );
                    break;
                case "Heart Hound":
                    card = new HeartHound(readCard.getMana(), readCard.getDescription(), readCard.getColors(),id );
                    break;
            }

            // add the newly instantiated card to the deck
            this.deck.add(card);


        }
    }

    // constructor used to deep copy another deck
    public Deck(Deck copyDeck, int id) {
        this.deck = new ArrayList<>();

        for (int i = 0; i < copyDeck.getSize(); i++) {

            Card card = copyDeck.getCard(i);
            String nameOfCard = card.getName();
            Card copyCard = null;

            switch (nameOfCard) {
                case "Disciple":
                    copyCard = new Disciple(card.getMana(), card.getDescription(), card.getColors(),
                            ((Disciple) card).getHealth(), ((Disciple) card).getAttackDamage(), id );
                    break;
                case "Miraj":
                    copyCard = new Miraj(card.getMana(), card.getDescription(), card.getColors(),
                            ((Miraj) card).getHealth(), ((Miraj) card).getAttackDamage(), id );
                    break;
                case "The Ripper":
                    copyCard = new TheRipper(card.getMana(), card.getDescription(), card.getColors(),
                            ((TheRipper) card).getHealth(), ((TheRipper) card).getAttackDamage(), id );
                    break;
                case "The Cursed One":
                    copyCard = new TheCursedOne(card.getMana(), card.getDescription(), card.getColors(),
                            ((TheCursedOne) card).getHealth(), ((TheCursedOne) card).getAttackDamage(), id );
                    break;
                case "Sentinel":
                    copyCard = new Sentinel(card.getMana(), card.getDescription(), card.getColors(),
                            ((Sentinel) card).getHealth(), ((Sentinel) card).getAttackDamage(), id );
                    break;
                case "Berserker":
                    copyCard = new Berserker(card.getMana(), card.getDescription(), card.getColors(),
                            ((Berserker) card).getHealth(), ((Berserker) card).getAttackDamage(), id );
                    break;
                case "Goliath":
                    copyCard = new Goliath(card.getMana(), card.getDescription(), card.getColors(),
                            ((Goliath) card).getHealth(), ((Goliath) card).getAttackDamage(), id );
                    break;
                case "Warden":
                    copyCard = new Warden(card.getMana(), card.getDescription(), card.getColors(),
                            ((Warden) card).getHealth(), ((Warden) card).getAttackDamage(), id );
                    break;
                case "Firestorm":
                    copyCard = new Firestorm(card.getMana(), card.getDescription(), card.getColors(),id );
                    break;
                case "Winterfell":
                    copyCard = new Winterfell(card.getMana(), card.getDescription(), card.getColors(),id );
                    break;
                case "Heart Hound":
                    copyCard = new HeartHound(card.getMana(), card.getDescription(), card.getColors(),id );
                    break;
            }

            this.deck.add(copyCard);

        }

    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    @JsonIgnore
    public int getSize() {
        return deck.size();
    }

    // get the card at index "index" from the deck
    public Card getCard(int index) {
        return deck.get(index);
    }

    // method used to shuffle the deck
    public void shuffle(int seed) {
        Random random = new Random(seed);
        Collections.shuffle(this.deck, random);
    }

    // draw a card from the deck
    public Card drawCard() {
            return deck.remove(0);
    }
}

package main;

import main.cards.Card;

import java.util.ArrayList;
import java.util.Stack;
import fileio.CardInput;
import main.cards.MinionCard;
import main.cards.environmentCards.Firestorm;
import main.cards.environmentCards.HeartHound;
import main.cards.environmentCards.Winterfell;
import main.cards.minionCards.*;

public class Deck {

    private Stack<Card> deck;

    public Deck(ArrayList<CardInput> inputDeck, int id) {
        this.deck = new Stack<>();

        for  (int i = 0; i < inputDeck.size(); i++) {

            CardInput readCard = inputDeck.get(i);
            String nameOfCard = readCard.getName();
            Card card = null;

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

            this.deck.add(card);


        }
    }

        //debug purpose remove after hw is done
//    public void printCards() {
//        while (deck.size() != 0) {
//            System.out.println(deck.pop());
//        }
//    }
}

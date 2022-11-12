package main.cards;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Array;
import java.util.ArrayList;

// base class that will be extended by each card
public class Card {

    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    // check which player has the card
    @JsonIgnore
    private int playerId;

    public Card() {
    }

    public Card(int mana, String description, ArrayList<String> colors, String name, int playerId) {
        this.mana = mana;
        this.description = description;
        this.colors = colors;
        this.name = name;
        this.playerId = playerId;
    }



    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    // workaround for environment cards



    @Override
    public String toString() {
        return "Card{" +
                "mana=" + mana +
                ", description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}

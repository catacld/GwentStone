package main.cards;


import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;

// base class that will be extended by each card
public class Card {

    private final int mana;
    private final String description;
    private final ArrayList<String> colors;
    private final String name;

    // check which player has the card
    @JsonIgnore
    private int playerId;


    public Card(final int mana, final String description, final ArrayList<String> colors,
                final String name, final int playerId) {
        this.mana = mana;
        this.description = description;
        this.colors = colors;
        this.name = name;
        this.playerId = playerId;
    }


    public int getMana() {
        return mana;
    }

    public String getDescription() {
        return description;
    }


    public ArrayList<String> getColors() {
        return colors;
    }


    public String getName() {
        return name;
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

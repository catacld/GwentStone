package main.cards;


import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;

// base class that will be extended by each card
public class Card {

    private final int mana;
    private final String description;
    private final ArrayList<String> colors;
    private final String name;

    // check which player the card belongs to
    @JsonIgnore
    private final int playerId;


    public Card(final int mana, final String description, final ArrayList<String> colors,
                final String name, final int playerId) {
        this.mana = mana;
        this.description = description;
        this.colors = colors;
        this.name = name;
        this.playerId = playerId;
    }

    public final int getMana() {
        return mana;
    }


    public final String getDescription() {
        return description;
    }

    public final ArrayList<String> getColors() {
        return colors;
    }

    public final String getName() {
        return name;
    }

    public final int getPlayerId() {
        return playerId;
    }

    // check if the card is an evironment card
    @JsonIgnore
    public final boolean isEnvironmentCard() {
        return name.equals("Firestorm") || name.equals("Winterfell") || name.equals("Heart Hound");
    }

    // check if the card is a tank card
    @JsonIgnore
    public final boolean isTank() {
        return name.equals("Goliath") || name.equals("Warden");
    }




}

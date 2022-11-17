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


    /**
     * @return mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @return colors
     */
    public ArrayList<String> getColors() {
        return colors;
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return playerId
     */

    public int getPlayerId() {
        return playerId;
    }




}

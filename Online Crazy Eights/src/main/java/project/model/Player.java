/**
 * Model package
 */
package project.model;

import java.util.ArrayList;

/**
 * A class representing player inside the game, player's cards, their name, their points
 */
public class Player {
    //
    // Model
    //

    /** List of cards on player's hand */
    private final ArrayList<Card> handCards;

    private int points = 0;

    /** The name of the player */
    private final String name;

    /**
     * Constructs a new player with given name and empty hand of cards
     * @param name name of the player
     */
    public Player(String name){
        this.name = name;
        handCards = new ArrayList<>();
    }

    /**
     * Gets the points the player has
     * @return the player's total points
     */
    public int getPoints(){
        return points;
    }

    /**
     * Adds the player's points by some amount
     * @param amount the given amount to add
     */
    public void addPoints(int amount){
        points += amount;
    }

    /**
     * Gets the player's name
     * @return the player's name
     */
    public String getName(){
        return name;
    }

    /**
     * Adds card to player's hand cards
     * @param card card to be added
     */
    public void addCard(Card card){
        handCards.add(card);
    }

    /**
     * Removes card from player's hand cards
     * @param card card to be removed
     */
    public void removeCard(Card card){
        handCards.remove(card);
    }

    /**
     * Returns the player's current hand of cards
     * @return a list of cards the player currently has
     */
    public ArrayList<Card> getHandCards(){
        return handCards;
    }

    /**
     * Clears the player's hand.
     */
    public void clearHand() {
        handCards.clear();
    }
}

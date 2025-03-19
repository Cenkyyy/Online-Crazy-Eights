/**
 * Model package
 */
package project.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class representing the game's deck, where players can draw cards from.
 * Maintains list of cards, option to shuffle them, drawing of the cards
 */
public class Deck {
    //
    // Model
    //

    /** List of deck cards */
    private final ArrayList<Card> deckCards;

    /**
     * Constructs initial deck and shuffles it
     */
    public Deck(){
        deckCards = new ArrayList<>();
        for (var suit : Card.Suits.values()){
            for (var rank : Card.Ranks.values()){
                deckCards.add(new Card(suit, rank));
            }
        }
        shuffleCards(deckCards);
    }

    /**
     * Shuffles the cards
     * @param cards cards to be shuffled
     */
    public void shuffleCards(ArrayList<Card> cards){
        Collections.shuffle(cards);
    }

    /**
     * Determines if deck is empty
     * @return true if it is, otherwise false
     */
    public boolean isEmpty() {
        return deckCards.isEmpty();
    }

    /**
     * Adds list of shuffled cards to the deck.
     * This is useful when recycling of the stockpile happens.
     * @param cards cards to be added
     */
    public void addCards(ArrayList<Card> cards){
        shuffleCards(cards);
        deckCards.addAll(cards);
    }

    /**
     * Draws first card from the deck
     * @return the first card from the deck
     */
    public Card drawCard(){
        return deckCards.removeFirst();
    }
}

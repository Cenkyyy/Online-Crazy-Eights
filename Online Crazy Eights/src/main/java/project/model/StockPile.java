/**
 * Model package
 */
package project.model;

import java.util.ArrayList;

/**
 * A class representing the game's stockpile, where cards are stocked.
 * Maintains list of cards, current top card, can recycle cards when deck is used up
 */
public class StockPile {
    //
    // Model
    //

    /** List of stockpile cards */
    private final ArrayList<Card> stockPileCards;

    private Card topCard;
    private Card.Suits topCardSuit;

    /**
     * Constructs an empty stockpile
     */
    public StockPile(){
        stockPileCards = new ArrayList<>();
    }

    /**
     * Adds a card to the stockpile and updates the top card and its suit
     * @param card the card to be added
     */
    public void addCard(Card card){
        stockPileCards.add(card);
        topCard = card;
        if (card.getRank() != Card.Ranks.EIGHT){
            topCardSuit = card.getSuit();
        }
    }

    /**
     * Gets the top card from the stockpile
     * @return the top card
     */
    public Card getTopCard(){
        return topCard;
    }

    /**
     * Returns the top card suit from the stockpile
     * @return the top card suit
     */
    public Card.Suits getTopCardSuit() { return topCardSuit; }

    /**
     * Sets the suit of the top card. This is here in case an EIGHT is placed down
     * to forcefully change the suit
     * @param suit the new suit of the top card
     */
    public void setTopCardSuit(Card.Suits suit){
        topCardSuit = suit;
    }

    /**
     * Recycles all cards under the top card.
     * This is done when the deck is empty and player's cannot draw another card
     * @return the list of cards, that were recycled
     */
    public ArrayList<Card> recycle(){
        ArrayList<Card> cardsToRecycle = new ArrayList<>();

        // get all cards
        for (int i = 0; i < stockPileCards.size() - 1; i++) {
            cardsToRecycle.add(stockPileCards.get(i));
        }

        // ensure the top card isn't in the recycled list
        cardsToRecycle.remove(topCard);
        stockPileCards.clear();
        stockPileCards.add(topCard);

        return cardsToRecycle;
    }
}

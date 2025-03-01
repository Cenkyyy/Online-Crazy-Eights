package java.project.model;

import java.util.ArrayList;

public class StockPile {
    //
    // Model
    //

    private ArrayList<Card> stockPileCards;
    private Card topCard;
    private Card.Suits topCardSuit;

    public StockPile(){
        stockPileCards = new ArrayList<>();
    }

    public void addCard(Card card){
        stockPileCards.add(card);
        topCard = card;
        topCardSuit = card.getSuit();
    }

    public Card getTopCard(){
        return topCard;
    }

    public void setTopCardSuit(Card.Suits suit){
        topCardSuit = suit;
    }

    public ArrayList<Card> recycle(){
        ArrayList<Card> cardsToRecycle = new ArrayList<>();
        for (int i = 0; i < stockPileCards.size() - 1; i++) {
            cardsToRecycle.add(stockPileCards.get(i));
        }
        cardsToRecycle.remove(topCard);
        stockPileCards.clear();
        stockPileCards.add(topCard);
        return cardsToRecycle;
    }
}

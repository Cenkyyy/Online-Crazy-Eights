package cz.cuni.mff.java.project;

import java.util.ArrayList;

public class StockPile {
    //
    // Model
    //

    private ArrayList<Card> stockPileCards;
    private Card topCard;

    public StockPile(){
        stockPileCards = new ArrayList<>();
    }

    public void AddCard(Card card){
        stockPileCards.add(card);
        topCard = card;
    }

    public Card GetTopCard(){
        return topCard;
    }

    public ArrayList<Card> Recycle(){
        ArrayList<Card> cardsToRecycle = new ArrayList<>();
        for (int i = 0; i < stockPileCards.size() - 1; i++) {
            cardsToRecycle.add(stockPileCards.get(i));
        }
        stockPileCards.clear();
        stockPileCards.add(topCard);
        return cardsToRecycle;
    }
}

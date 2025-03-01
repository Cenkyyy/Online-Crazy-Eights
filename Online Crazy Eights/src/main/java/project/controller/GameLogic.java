package project.controller;

// TO-DO:

// Use Model-View-Controller pattern
// Model: Card, Deck, StockPile
// Controller: GameLogic
// View: GameDisplayer, Chat

// Firstly, create the game logic without networking
// Secondly, GameDisplaying and Chat for all possibilities
// Lastly, integrate networking into this

import project.model.Player;
import project.model.Card;
import project.model.Deck;
import project.model.StockPile;
import java.util.ArrayList;

public class GameLogic {
    //
    // Controller
    //

    private final int twoPlayerCardsCount = 7;
    private final int nonTwoPlayerCardCount = 5;

    private int currPlayerIndex;
    private ArrayList<Player> players;
    private Deck deck;
    private StockPile stockPile;

    public GameLogic(){
        players = new ArrayList<>();
        deck = new Deck();
        stockPile = new StockPile();

        dealInitialCards();
        Card firstCard = deck.drawCard();
        stockPile.addCard(firstCard);
    }

    public Player getCurrentPlayer(){
        return players.get(currPlayerIndex);
    }

    public void nextTurn(){
        currPlayerIndex = (currPlayerIndex + 1) % players.size();
    }

    public void addPlayer(Player player){
        if (players.size() < 8){
            players.add(player);
        }
        else System.out.println("[GAME] This lobby is already full.");
    }

    public void dealInitialCards(){
        int cardsCount = players.size() == 2 ? twoPlayerCardsCount : nonTwoPlayerCardCount;

        for (int i = 0; i < cardsCount; i++) {
            for (var player : players){
                player.addCard(deck.drawCard()); // add drawn card from the deck
            }
        }
    }

    public void drawCard(Player player){
        if (deck.isEmpty()){
            deck.addCards(stockPile.recycle());
        }
        Card drawnCard = deck.drawCard();
        if (drawnCard != null){
            player.addCard(drawnCard);
        }
    }

    public boolean playCard(Player player, Card card, Card.Suits suit){
        Card topCard = stockPile.getTopCard();

        if (card.getSuit() == topCard.getSuit() ||
            card.getRank() == Card.Ranks.EIGHT ||
            card.getRank() == topCard.getRank()){

            if (card.getRank() == Card.Ranks.EIGHT && suit != null){
                stockPile.setTopCardSuit(suit);
            }

            player.removeCard(card);
            stockPile.addCard(card);
            nextTurn();
            return true;
        }
        return false;
    }
}

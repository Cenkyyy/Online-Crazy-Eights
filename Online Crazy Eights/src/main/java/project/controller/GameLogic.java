/**
 * Controller package
 */
package project.controller;

import project.model.Player;
import project.model.Card;
import project.model.Deck;
import project.model.StockPile;

import java.util.ArrayList;

/**
 * A class that manages the game logic, checks if someone has won, player turns, card drawing, playing cards
 */
public class GameLogic {
    //
    // Controller
    //

    // constants for initial cards being given
    private final int twoPlayerCardsCount = 7;
    private final int nonTwoPlayerCardCount = 5;

    // player limit
    public final int playerUpperBound = 7;
    public final int playerLowerBound = 2;

    // when this amount of points is reached, player wins
    private int winningAmount = 50;

    private int currPlayerIndex;
    private ArrayList<Player> players;
    private Deck deck;
    private StockPile stockPile;

    /**
     * Constructs a new instance with empty player list, shuffled deck, and a stockpile of cards
     */
    public GameLogic(){
        players = new ArrayList<>();
        deck = new Deck();
        stockPile = new StockPile();
        winningAmount = 50 * players.size();
    }

    /**
     * Deals initial cards to each player, and starts it
     */
    public void startGame(){
        dealInitialCards();
        Card firstCard = deck.drawCard();
        stockPile.addCard(firstCard);
    }

    /**
     * Resets the game for a new round.
     */
    public void resetGame() {
        for (Player player : players) {
            player.clearHand();
        }
        deck = new Deck();
        stockPile = new StockPile();
    }

    /**
     * Returns the player, whose turn it is
     * @return the current player
     */
    public Player getCurrentPlayer(){
        return players.get(currPlayerIndex);
    }

    /**
     * Moves the turn to the next player
     */
    public void nextTurn(){
        currPlayerIndex = (currPlayerIndex + 1) % players.size();
    }

    /**
     * Adds a player to the game if the limit of 7 players isn't full
     * @param player player to add
     */
    public void addPlayer(Player player){
        if (players.size() < playerUpperBound){
            players.add(player);
        }
        else System.out.println("[GAME] This lobby is already full.");
    }

    /**
     * Removes player from the game
     * @param player player to remove
     */
    public void removePlayer(Player player){
        players.remove(player);
    }

    /**
     * Returns list of current players playing the game
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the current stockpile of cards
     * @return the stockpile instance
     */
    public StockPile getStockPile(){
        return stockPile;
    }

    /**
     * Deals initial cards to each player based on amount of players in the game
     */
    public void dealInitialCards(){
        int cardsCount = players.size() == playerLowerBound ? twoPlayerCardsCount : nonTwoPlayerCardCount;

        for (int i = 0; i < cardsCount; i++) {
            for (var player : players){
                player.addCard(deck.drawCard()); // add drawn card from the deck
            }
        }
    }

    /**
     * Draws a card from the deck and gives it to the player if it is possible, if not, stockpile will get recycled
     * @param player the player to give the card to
     */
    public void drawCard(Player player){
        if (deck.isEmpty()){
            deck.addCards(stockPile.recycle());
        }
        Card drawnCard = deck.drawCard();
        if (drawnCard != null){
            player.addCard(drawnCard);
        }
    }

    /**
     * Handles playing a card from a player's hand
     * @param player the player trying to play a card
     * @param card the card being played
     * @param chosenSuit the chosen suit if playing an 8, otherwise null
     * @return true if the play was successful, false otherwise
     */
    public boolean playCard(Player player, Card card, Card.Suits chosenSuit){
        Card topCard = stockPile.getTopCard();

        if (card.getSuit() == topCard.getSuit() ||
            card.getRank() == Card.Ranks.EIGHT ||
            card.getRank() == topCard.getRank()){

            if (card.getRank() == Card.Ranks.EIGHT) {
                if (chosenSuit == null) {
                    return false;
                }
                stockPile.setTopCardSuit(chosenSuit);
            }

            player.removeCard(card);
            stockPile.addCard(card);
            nextTurn();
            return true;
        }
        return false;
    }

    /**
     * Checks if any player has their hand is empty - meaning they've won
     * @return true if at least one player has no cards left, false otherwise
     */
    public boolean hasRoundWinner() {
        for (Player player : players) {
            if (player.getHandCards().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the player who has won the round
     * Assumes that only one player can have an empty hand at the time of winning
     * @return the winning player, or null if no winner is found
     */
    public Player getRoundWinner() {
        for (Player player : players) {
            if (player.getHandCards().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Checks if any player has enough points to win the game
     * @return true if at least one player has enough points to win, false otherwise
     */
    public boolean hasUltimateWinner(){
        for (Player player : players){
            if (player.getPoints() >= winningAmount){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the player who has won the game
     * @return the winning player, or null if no winner is found
     */
    public Player getUltimateWinner(){
        for (Player player : players){
            if (player.getPoints() >= winningAmount){
                return player;
            }
        }
        return null;
    }

    /**
     * Calculates the score for the winning player based on the opponents' remaining cards.
     * @param winner the player who won the game
     * @return how many points the player has won
     */
    public int calculateScores(Player winner) {
        int totalPoints = 0;
        for (Player player : players) {
            if (!player.equals(winner)) {
                for (Card card : player.getHandCards()) {
                    totalPoints += card.getPointValue();
                }
            }
        }
        winner.addPoints(totalPoints);
        return totalPoints;
    }
}

package java.project.view;

import java.project.model.Card;
import java.project.model.Player;
import java.util.ArrayList;

public class GameDisplayer {
    //
    // View
    //

    public void DisplayRules(){
        System.out.println("======================================");
        System.out.println("      Welcome to Crazy Eights!        ");
        System.out.println("======================================");
        System.out.println("Aim:");
        System.out.println("The first player to get rid of all their cards wins the round. " +
                           "After player reaches certain amount of points he wins the game.");
        System.out.println("Rules:");
        System.out.println("1. Each player is dealt 5 cards (or 7 in a two-player game).");
        System.out.println("2. The top card from the deck is placed face-up to start the stock pile.");
        System.out.println("3. Players take turns matching the top card by rank or suit.");
        System.out.println("4. If a player cannot play, they must draw a card.");
        System.out.println("5. Players can play 8 at any time, which allows them to choose the next suit of the card to be played");
        System.out.println("6. Points are earned by winning rounds, after round ends, cards of all other players are checked " +
                               "and score is added as follows: each 8 is worth 50 points, each ACE is worth 1 point, each 10,J,Q,K is worth 10 points.");
        System.out.println("======================================");
    }

    public void DisplayGameState(Player player){
        System.out.println("======================================");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|            Top card:             |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("=|                                  |=");
        System.out.println("======================================");
        System.out.println("Current turn: " + player.getName());
        DisplayPlayersCards(player);
    }

    public void DisplayPlayersCards(Player player){
        ArrayList<Card> playersCards = player.getHandCards();

        System.out.print(player.getName() + " cards are: |");
        for (var card : playersCards){
            System.out.print(card + "|");
        }
        System.out.println("|");
    }
}

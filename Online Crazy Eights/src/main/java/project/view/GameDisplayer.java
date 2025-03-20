/**
 * View package
 */
package project.view;

import project.model.Card;
import project.model.Player;
import java.util.ArrayList;

/**
 * A class containing all sorts of displaying the game makes
 */
public class GameDisplayer {
    //
    // View
    //

    /**
     * Display rules of the game at the start
     */
    public String displayRules(){
        return """
               \n======================================
                      Welcome to Crazy Eights!       
               ======================================
               Aim:
               The first player to get rid of all their cards wins the round. 
               After a player reaches a certain amount of points, they win the game.
               
               Rules:
               1. Each player is dealt 5 cards (or 7 in a two-player game).
               2. The top card from the deck is placed face-up to start the stockpile.
               3. Players take turns matching the top card by rank or suit.
               4. If a player cannot play, they must draw a card.
               5. Players can play an 8 at any time, which allows them to choose the next suit.
               6. Points are earned by winning rounds. After a round ends, other players' cards are checked 
                  and scores are added as follows:
                  - Each 8 is worth 50 points.
                  - Each ACE is worth 1 point.
                  - Each 10, J, Q, K is worth 10 points.
               7. The first player to reach the winning score is declared the ultimate winner.   
                 
               How to play:
               You may choose one of the 3 possible commands:
                1. PLAY <one-based index of card> <suit> - Plays a card. If playing an Eight, specify the new suit. Possible options: HEARTS, DIAMONDS, CLUBS, SPADES (lowercase also accepted)
                2. DRAW - Draws a card from the deck
                3. CHAT <message> - Sends a chat message to all players.
                4. START - Lets any player start the game
               """;
    }

    /**
     * Displays the overall game state.
     * @param players The list of all players.
     * @param currentPlayer The player whose turn it is and the only one who will see their hand.
     * @param topCard The card currently on top of the stockpile.
     * @return The game state as a formatted string.
     */
    public String displayGameState(ArrayList<Player> players, Player currentPlayer, Card topCard, Card.Suits topCardSuit){
        StringBuilder sb = new StringBuilder();

        sb.append("======================================\n");
        sb.append("           Crazy Eights Board         \n");
        sb.append("======================================\n");
        if (topCard.getRank() == Card.Ranks.EIGHT){
            sb.append("Top Card: ").append(topCard.getRank().getValue()).append(topCardSuit.getSymbol()).append("\n");
        }else{
            sb.append("Top Card: ").append(topCard).append("\n");
        }
        sb.append("--------------------------------------\n");

        // display public view: each player's name and their card count.
        for (Player player : players) {
            sb.append(player.getName()).append(" has ").append(player.getHandCards().size()).append(" cards.\n");
        }
        sb.append("======================================\n");
        sb.append("Current turn: ").append(currentPlayer.getName());
        sb.append("\n======================================");
        return sb.toString();
    }

    /**
     * Displays the player's hand.
     * @param player The player whose cards will be displayed.
     * @return The player's hand as a formatted string.
     */
    public String displayPlayersCards(Player player){
        StringBuilder sb = new StringBuilder();
        sb.append("Your (").append(player.getName()).append(") hand: |");

        for (Card card : player.getHandCards()) {
            sb.append(card.getRank().getValue()).append(card.getSuit().getSymbol()).append("|");
        }
        sb.append(" (choose between indices 1-").append(player.getHandCards().size()).append(")");
        return sb.toString();
    }

    /**
     * Displays the player's score
     * @param player The player whose score will be displayed
     * @return The player's score as a string
     */
    public String displayPlayersScore(Player player){
        return "Your (" + player.getName() + ") game score is: " + player.getPoints() + "\n";
    }

    /** Displays message that the player hasn't joined the game yet */
    public String displayNotJoinedTheGameMessage(){
        return "[SERVER] You haven't joined the game yet.";
    }

    /** Displays message to inform the player that it's not his turn yet */
    public String displayNotYourTurnMessage(){
        return "[SERVER] It's not your turn.";
    }

    /** Displays message that player didn't write the PLAY command correctly */
    public String displayInvalidPlayCommandMessage(){
        return "[SERVER] Invalid PLAY command. Usage: PLAY <cardIndex> [<suit>]";
    }

    /** Displays message the command is unknown */
    public String displayUnknownCommandMessage(){
        return "[SERVER] Unknown command.";
    }

    /** Displays message that the chosen card index is out of range */
    public String displayOutOfRangeIndexMessage(){
        return "[SERVER] Card index out of range.";
    }

    /** Displays message that the card index is in invalid form */
    public String displayInvalidCardIndexMessage(){
        return "[SERVER] Invalid card index.";
    }

    /** Displays message to inform the player to submit a chosen SUIT as well when playing an EIGHT */
    public String displayNotSuitChosenWhenPlayingEightMessage(){
        return "[SERVER] When playing an 8, you must choose a suit. Usage: PLAY <cardIndex> <suit>";
    }

    /** Displays message that the chosen suit of the EIGHT is not valid */
    public String displayInvalidSuitChosenMessage(){
        return "[SERVER] Invalid suit. Valid suits: HEARTS, DIAMONDS, CLUBS, SPADES.";
    }

    /** Displays message that the move was invalid */
    public String displayInvalidMoveMessage(){
        return "[SERVER] Invalid move. Please try again.";
    }

    /** Displays message that the CHAT command is in incorrect form */
    public String displayInvalidChatCommandMessage(){
        return "[SERVER] Invalid CHAT command. Usage: CHAT <message>";
    }
}

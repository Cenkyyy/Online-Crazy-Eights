/**
 * View package
 */
package project.view;

import project.model.Player;

/**
 * Handles the display of chat messages in the game
 */
public class Chat {
    //
    // View
    //

    /**
     * Returns a chat message from a player.
     * @param player The player sending the message.
     * @param message The message content.
     * @return A formatted string representing the chat message.
     */
    public String displayMessage(Player player, String message){
        return "[CHAT] " + player.getName() + ": " + message;
    }
}

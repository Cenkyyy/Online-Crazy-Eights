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
     * @param splittedMessage The message content.
     * @return A formatted string representing the chat message.
     */
    public String displayMessage(Player player, String[] splittedMessage){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < splittedMessage.length; i++){
            sb.append(splittedMessage[i]).append(" ");
        }
        sb.append("\n");
        return "[CHAT] " + player.getName() + ": " + sb.toString();
    }
}

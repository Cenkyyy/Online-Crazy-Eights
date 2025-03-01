package project.view;

import project.model.Player;

public class Chat {
    //
    // View
    //

    public void DisplayMessage(Player player, String message){
        System.out.println("[CHAT] " + player.getName() + ": " + message);
    }
}

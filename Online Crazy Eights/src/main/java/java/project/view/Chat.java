package java.project.view;

import java.project.model.Player;

public class Chat {
    //
    // View
    //

    public void DisplayMessage(Player player, String message){
        System.out.println("[CHAT] " + player.getName() + ": " + message);
    }
}

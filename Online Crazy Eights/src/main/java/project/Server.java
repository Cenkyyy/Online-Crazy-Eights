/**
 * Network package
 */
package project.network;

import project.controller.GameLogic;
import project.model.Player;
import project.model.Card;
import project.view.GameDisplayer;
import project.view.Chat;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A class that opens up the server and lets users join, handles every command and the game itself
 */
public class Server {
    //
    // Network
    //

    /** Game server port */
    private static final int PORT = 6666;

    /** List of active client handlers for each connected client. */
    private final List<ClientHandler> clientHandlers = Collections.synchronizedList(new ArrayList<>());

    // controller
    private final GameLogic gameLogic = new GameLogic();

    // views
    private final GameDisplayer displayer = new GameDisplayer();
    private final Chat chatView = new Chat();

    // bool to ensure the game starts only once
    private boolean gameStarted = false;

    // bool to ensure the game is currently running
    private volatile boolean running = true;

    /**
     * The main that starts server and waits for clients to join
     * @param args command-line arguments (not needed)
     */
    public static void main(String[] args) {
        new Server().start();
    }

    /**
     * Starts the server and waits for client connections.
     * Each joined client is running on separate thread
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server successfully started on port " + PORT);

            // main loop - accept new clients and create a thread for them
            while (running) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                clientHandlers.add(handler);
                new Thread(handler).start();
                System.out.println("A new player connected. Total players: " + clientHandlers.size());
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred when creating server");
        }
        finally {
            shutdown();
        }
    }

    /**
     * Sends a message to all connected clients
     * @param message the message to broadcast
     */
    public void publicBroadcast(String message) {
        synchronized(clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                handler.sendMessage(message);
            }
        }
    }

    /**
     * Sends a message only to the target player
     * @param targetPlayer player that the message should be sent to
     * @param message message to broadcast
     */
    public void privateBroadcast(Player targetPlayer, String message){
        for (ClientHandler handler : clientHandlers) {
            if (handler.getPlayer().equals(targetPlayer)) {
                handler.sendMessage(message); // Send only to the matched player
                break;
            }
        }
    }

    /**
     * Processes a command sent from a client
     * Valid commands:
     * - PLAY <cardIndex> [<suit>] (Play a card; if playing an 8, a suit must be provided)
     * - DRAW (Draw a card)
     * - CHAT <message> (Send a chat message)
     * @param handler the client handler processing the command
     * @param command the command received from the client as string
     */
    public void processCommand(ClientHandler handler, String command) {
        Player player = handler.getPlayer();
        if (player == null) {
            handler.sendMessage(displayer.displayNotJoinedTheGameMessage());
            return;
        }

        String[] splitted = command.split(" ");
        Commands.Command cmd = Commands.Command.fromString(splitted[0]);

        if (!player.equals(gameLogic.getCurrentPlayer()) && !cmd.equals(Commands.Command.CHAT)) {
            handler.sendMessage(displayer.displayNotYourTurnMessage());
            return;
        }

        switch (cmd) {
            case PLAY -> handlePlayCommand(handler, player, splitted);
            case DRAW -> handleDrawCommand(player);
            case CHAT -> handleChatCommand(handler, splitted);
            case UNKNOWN -> handler.sendMessage(displayer.displayUnknownCommandMessage());
        }
    }

    /**
     * Handles the PLAY command when a player attempts to play a card
     * @param handler the client handler executing the command
     * @param player the player who is playing the card
     * @param splitted the split command arguments
     */
    private void handlePlayCommand(ClientHandler handler, Player player, String[] splitted) {
        if (splitted.length < 2) {
            handler.sendMessage(displayer.displayInvalidPlayCommandMessage());
            return;
        }

        int cardIndex;
        try {
            cardIndex = Integer.parseInt(splitted[1]) - 1;
        }
        catch (NumberFormatException e) {
            handler.sendMessage(displayer.displayInvalidCardIndexMessage());
            return;
        }

        if (cardIndex < 0 || cardIndex >= player.getHandCards().size()) {
            handler.sendMessage(displayer.displayOutOfRangeIndexMessage());
            return;
        }

        Card cardToPlay = player.getHandCards().get(cardIndex);
        Card.Suits chosenSuit = null;

        // in case EIGHT is player
        if (cardToPlay.getRank() == Card.Ranks.EIGHT) {
            if (splitted.length < 3) {
                handler.sendMessage(displayer.displayNotSuitChosenWhenPlayingEightMessage());
                return;
            }

            // get chosen suit
            switch(splitted[2].toUpperCase()){
                case "HEARTS" -> chosenSuit = Card.Suits.HEARTS;
                case "DIAMONDS" -> chosenSuit = Card.Suits.DIAMONDS;
                case "CLUBS" -> chosenSuit = Card.Suits.CLUBS;
                case "SPADES" -> chosenSuit = Card.Suits.SPADES;
                default -> handler.sendMessage(displayer.displayInvalidSuitChosenMessage());
            }
        }

        boolean success = gameLogic.playCard(player, cardToPlay, chosenSuit);
        if (success) {
            if (cardToPlay.getRank() == Card.Ranks.EIGHT && chosenSuit != null){
                displayOneGameRound("[GAME] " + player.getName() + " played " + cardToPlay + " and switched the suit to: " + chosenSuit.getSymbol());
            }
            else{
                displayOneGameRound("[GAME] " + player.getName() + " played " + cardToPlay);
            }
        }
        else {
            handler.sendMessage(displayer.displayInvalidMoveMessage());
        }
    }

    /**
     * Handles the DRAW command
     * @param player the player who is playing the card
     */
    private void handleDrawCommand(Player player) {
        gameLogic.drawCard(player);
        gameLogic.nextTurn();
        displayOneGameRound("[GAME] " + player.getName() + " drew a card.");
    }

    /**
     * Handles the CHAT command.
     * @param handler the client handler executing the command
     * @param splitted the split command arguments
     */
    private void handleChatCommand(ClientHandler handler, String[] splitted) {
        if (splitted.length < 2) {
            handler.sendMessage(displayer.displayInvalidChatCommandMessage());
            return;
        }
        String msg = splitted[1];
        publicBroadcast(chatView.displayMessage(handler.getPlayer(), msg));
    }

    /**
     * Displays the winner and their score if there is one, otherwise,
     * displays
     * @param message the message to display
     */
    private void displayOneGameRound(String message){
        if (gameLogic.hasRoundWinner()) {
            if (gameLogic.hasUltimateWinner()){
                Player ultimateWinner = gameLogic.getUltimateWinner();
                publicBroadcast("[GAME] " + ultimateWinner.getName() + " wins the game, thank you everyone for playing! bye");
                shutdown();
                return;
            }

            Player winner = gameLogic.getRoundWinner();
            int totalPoints = gameLogic.calculateScores(winner);
            publicBroadcast("[GAME] Accumulated score from other players cards: " + totalPoints + " points!");
            publicBroadcast("[GAME] " + winner.getName() + " wins and currently has: " + winner.getPoints());
            resetGame();
            return;
        }

        // display the game state publicly for everyone
        publicBroadcast(message);
        publicBroadcast(displayer.displayGameState(gameLogic.getPlayers(), gameLogic.getCurrentPlayer(), gameLogic.getStockPile().getTopCard(), gameLogic.getStockPile().getTopCardSuit()));

        // display each player's card to him privately
        for (var p : gameLogic.getPlayers()){
            privateBroadcast(p, displayer.displayPlayersCards(p));
            privateBroadcast(p, displayer.displayPlayersScore(p));
        }
    }

    /**
     * Resets the game
     */
    private void resetGame(){
        gameLogic.resetGame();
        gameLogic.startGame();
        publicBroadcast("[GAME] New game is starting!");
    }

    /**
     * Closes the server socket and notifies all connected clients.
     */
    private void shutdown() {
        running = false;
        publicBroadcast("Server is shutting down.");
    }

    /**
     * Handles communication with a single client.
     */
    private class ClientHandler implements Runnable {
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private Player player;

        /**
         * Constructs a ClientHandler for the given socket
         * @param socket the socket representing the client connection
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Returns the player associated with this client handler
         * @return the player instance
         */
        public Player getPlayer() {
            return player;
        }

        /**
         * Sends a message to the client
         * @param message the message to be sent
         */
        public void sendMessage(String message) {
            out.println(message);
        }

        /**
         * Handles the client communication loop.
         */
        @Override
        public void run() {
            try {
                in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // ask the client for its name
                out.println("Enter your name:");
                String name = in.readLine();
                player = new Player(name);

                // add the new player to the game
                gameLogic.addPlayer(player);
                publicBroadcast("[GAME] " + name + " has joined the game.");

                // If at least 2 players have joined and the game hasn’t started, then start the game.
                if (gameLogic.getPlayers().size() >= 2 && !gameStarted) {
                    gameStarted = true;
                    gameLogic.startGame();
                    publicBroadcast("[GAME] Game is starting!");
                    displayOneGameRound(displayer.displayRules());
                }

                // main loop - process each line received from the client.
                String input;
                while ((input = in.readLine()) != null) {
                    processCommand(this, input);
                }
            }
            catch (IOException e) {
                System.out.println("Connection error with client " + (player != null ? player.getName() : ""));
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    // won't happen
                }
                clientHandlers.remove(this);
                publicBroadcast("[GAME] " + (player != null ? player.getName() : "A player") + " has left.");
            }
        }
    }
}

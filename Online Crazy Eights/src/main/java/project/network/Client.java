/**
 * Network package
 */
package project.network;

import java.io.*;
import java.net.*;

/**
 * A client that connects to the game server, sends user input and receives messages
 */
public class Client {
    //
    // Network
    //

    /** Game server address */
    private static final String SERVER_ADDRESS = "localhost";

    /** Game server port */
    private static final int SERVER_PORT = 6666;

    /**
     * The main that starts the connections between the client and server
     * @param args command-line arguments (not needed)
     */
    public static void main(String[] args) {
        new Client().start();
    }

    /**
     *  Starts the client, connects client to the server and manages communication
     */
    public void start() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // create connection to the server
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // to receive messages from server
             PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true); // to send messages to the server
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))) { // to read user commands from the console

            // start a thread to receive messages from the server
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = serverIn.readLine()) != null) {
                        System.out.println(msg);
                    }
                }
                catch (IOException e) {
                    System.out.println("Connection closed by server.");
                }
            }).start();

            // main loop - read user input and send it to the server
            String input;
            while ((input = userIn.readLine()) != null) {
                serverOut.println(input);
            }
        }
        catch (IOException e) {
            System.err.println("Unable to connect to server: " + e.getMessage());
        }
    }
}

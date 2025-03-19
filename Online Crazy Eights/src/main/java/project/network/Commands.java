/**
 * Network package
 */
package project.network;

/**
 * A class containing possible game commands used for communicating during the game
 */
public class Commands {
    //
    // Network
    //

    /**
     * Enum representing different types of commands players can send
     */
    public enum Command {
        PLAY,
        DRAW,
        CHAT,
        UNKNOWN; // For invalid commands

        /**
         * Converts a string representation of a command into its corresponding enum
         * @param str the command string to be converted
         * @return the corresponding command
         */
        public static Command fromString(String str) {
            Command cmd;
            switch (str.toUpperCase()) {
                case "PLAY" -> cmd = Command.PLAY;
                case "DRAW" -> cmd = Command.DRAW;
                case "CHAT" -> cmd = Command.CHAT;
                default -> cmd = Command.UNKNOWN;
            }
            return cmd;
        }
    }
}

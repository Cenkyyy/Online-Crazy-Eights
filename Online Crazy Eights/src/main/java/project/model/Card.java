/**
 * Model package
 */
package project.model;

/**
 * A class representing a card with a suit and rank
 */
public class Card {
    //
    // Model
    //

    /**
     * Represents the suits of a card
     */
    public enum Suits {
        DIAMONDS("♦"), HEARTS("♥"), SPADES("♠"), CLUBS("♣");

        /** The symbol representing the suit */
        private final String symbol;

        /**
         * Constructs a suit with given symbol
         * @param symbol is the symbol for the suit
         */
        Suits(String symbol) {
            this.symbol = symbol;
        }

        /**
         * Gets the symbol of the suit
         * @return the suit symbol
         */
        public String getSymbol() {
            return symbol;
        }
    }

    /**
     * Represents the ranks of a card
     */
    public enum Ranks {
        ACE("A", 1), TWO("2", 0), THREE("3", 0), FOUR("4", 0), FIVE("5", 0),
        SIX("6", 0), SEVEN("7", 0), EIGHT("8", 50), NINE("9", 0),
        TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

        /** The value representing the value (or rank) */
        private final String value;

        /** The point value assigned to the rank */
        private final int pointValue;

        /**
         * Constructs a rank with a string value and point value
         * @param value the string representation of the rank
         * @param pointValue the point value of the rank
         */
        Ranks(String value, int pointValue) {
            this.value = value;
            this.pointValue = pointValue;
        }

        /**
         * Gets the string value of the rank
         * @return the rank's value
         */
        public String getValue() {
            return value;
        }

        /**
         * Gets the point value of the rank
         * @return the rank's point value
         */
        public int getPointValue(){
            return pointValue;
        }
    }

    /** The suit of the card */
    private final Suits Suit;

    /** The rank of the card */
    private final Ranks Rank;

    /**
     * Constructs a card with specified suit and rank
     * @param suit is the suit of the card
     * @param rank is the rank of the card
     */
    public Card(Suits suit, Ranks rank) {
        Suit = suit;
        Rank = rank;
    }

    /**
     * Gets the suit of the card
     * @return the suit of the card
     */
    public Suits getSuit(){
        return Suit;
    }

    /**
     * Gets the rank of the card
     * @return the rank of the card
     */
    public Ranks getRank(){
        return Rank;
    }

    /**
     *
     * @return
     */
    public int getPointValue() {
        return Rank.pointValue;
    }

    /**
     * Overrides a way how to print card as a string
     * @return string version of a card
     */
    @Override
    public String toString() {
        return Rank.getValue() + Suit.getSymbol();
    }
}

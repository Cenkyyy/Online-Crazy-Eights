package cz.cuni.mff.java.project;

public class Card {
    //
    // Model
    //

    public enum Suits {
        DIAMONDS("♦"), HEARTS("♥"), SPADES("♠"), CLUBS("♣");

        private final String symbol;

        Suits(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public enum Ranks {
        TWO("2", 0), THREE("3", 0), FOUR("4", 0), FIVE("5", 0),
        SIX("6", 0), SEVEN("7", 0), EIGHT("8", 50), NINE("9", 0),
        TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10), ACE("A", 1);

        private final String value;
        private final int pointValue;

        Ranks(String value, int pointValue) {
            this.value = value;
            this.pointValue = pointValue;
        }

        public String getValue() {
            return value;
        }

        public int getPointValue(){
            return pointValue;
        }
    }

    public final Suits Suit;
    public final Ranks Rank;

    public Card(Suits suit, Ranks rank) {
        Suit = suit;
        Rank = rank;
    }

    public Suits GetSuit(){
        return Suit;
    }

    public Ranks GetRank(){
        return Rank;
    }
}

package project.model;

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
        ACE("A", 1), TWO("2", 0), THREE("3", 0), FOUR("4", 0), FIVE("5", 0),
        SIX("6", 0), SEVEN("7", 0), EIGHT("8", 50), NINE("9", 0),
        TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

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

    private final Suits Suit;
    private final Ranks Rank;

    public Card(Suits suit, Ranks rank) {
        Suit = suit;
        Rank = rank;
    }

    public Suits getSuit(){
        return Suit;
    }

    public Ranks getRank(){
        return Rank;
    }
}

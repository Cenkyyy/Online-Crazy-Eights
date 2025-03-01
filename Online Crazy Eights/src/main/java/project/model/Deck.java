package project.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //
    // Model
    //

    private ArrayList<Card> deckCards;

    public Deck(){
        deckCards = new ArrayList<>();
        for (var suit : Card.Suits.values()){
            for (var rank : Card.Ranks.values()){
                deckCards.add(new Card(suit, rank));
            }
        }
        shuffleCards();
    }

    public void shuffleCards(){
        Collections.shuffle(deckCards);
    }

    public boolean isEmpty() {
        return deckCards.isEmpty();
    }

    public void addCards(ArrayList<Card> cards){
        deckCards.addAll(cards);
        shuffleCards();
    }

    public Card drawCard(){
        return deckCards.removeFirst();
    }
}

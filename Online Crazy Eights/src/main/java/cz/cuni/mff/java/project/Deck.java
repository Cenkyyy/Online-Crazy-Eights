package cz.cuni.mff.java.project;

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
        ShuffleCards();
    }

    public void ShuffleCards(){
        Collections.shuffle(deckCards);
    }

    public void AddCards(ArrayList<Card> cards){
        deckCards.addAll(cards);
        ShuffleCards();
    }

    public Card DrawCard(){
        return deckCards.removeFirst();
    }
}

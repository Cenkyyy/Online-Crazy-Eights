package cz.cuni.mff.java.project;

import java.util.ArrayList;

public class Player {
    //
    // Model
    //

    private ArrayList<Card> handCards;
    private int points = 0;
    private final String name;

    public Player(String name){
        this.name = name;
        handCards = new ArrayList<>();
    }

    public int GetPoints(){
        return points;
    }

    public void AddPoints(int amount){
        points += amount;
    }

    public String GetName(){
        return name;
    }

    public void AddCard(Card card){
        handCards.add(card);
    }

    public void RemoveCard(Card card){
        handCards.remove(card);
    }
}

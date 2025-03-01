package java.project.model;

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

    public int getPoints(){
        return points;
    }

    public void addPoints(int amount){
        points += amount;
    }

    public String getName(){
        return name;
    }

    public void addCard(Card card){
        handCards.add(card);
    }

    public void removeCard(Card card){
        handCards.remove(card);
    }

    public ArrayList<Card> getHandCards(){
        return handCards;
    }
}

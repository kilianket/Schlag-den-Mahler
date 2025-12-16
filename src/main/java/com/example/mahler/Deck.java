package com.example.mahler;

import javafx.scene.image.Image;
import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    private final String[] suits = {"Karo", "Herz", "Kreuz", "Pik"};
    private final String[] ranks = {"2","3","4","5","6","7","8","9","10","Bube","Dame","König","Ass"};

    public Deck() {
        reset();
    }

    public void reset() {
        cards.clear();

        for (String suit : suits) {
            for (String rank : ranks) {
                String path = "/com/example/mahler/cards/" + rank + "_" + suit + ".png";
                Image img = new Image(getClass().getResource(path).toString());
                cards.add(new Card(suit, rank, img));
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) reset();
        return cards.remove(0);
    }
}

package com.example.mahler;

import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        reset();
    }

    public void reset() {
        cards.clear();
        String[] suits = {"Karo", "Herz", "Kreuz", "Pik"};
        for (String suit : suits) {
            String color = (suit.equals("Karo") || suit.equals("Herz")) ? "rot" : "schwarz";
            for (int value = 2; value <= 14; value++) {
                cards.add(new Card(suit, value, color));
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


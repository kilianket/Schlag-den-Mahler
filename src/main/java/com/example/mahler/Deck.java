package com.example.mahler;

import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        reset();
    }

    public void reset() {
        cards.clear();

        for (int i = 0; i < 52; i++) {
            String suit;
            int value;

            // Suit bestimmen
            if (i < 13) suit = "Karo";
            else if (i < 26) suit = "Herz";
            else if (i < 39) suit = "Kreuz";
            else suit = "Pik";

            // Value 2..14
            value = (i % 13) + 2;

            // Farbe
            String color = (suit.equals("Karo") || suit.equals("Herz")) ? "rot" : "schwarz";

            // Image-Pfad
            String imagePath = "/com/example/mahler/cards/card" + i + ".png";

            cards.add(new Card(suit, value, color, imagePath));
        }

        // shuffle() gehört hier innerhalb der Methode reset()
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

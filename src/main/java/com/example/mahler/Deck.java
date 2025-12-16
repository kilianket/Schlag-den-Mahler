package com.example.mahler;

import javafx.scene.image.Image;
import java.util.*;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        reset();
    }

    public void reset() {
        cards.clear();

        String[] suits = {"Karo", "Herz", "Kreuz", "Pik"};

        for (int i = 0; i < 52; i++) {
            String suit = suits[i / 13];
            int value = (i % 13) + 2;

            Image img = new Image(
                    getClass().getResource("/com/example/mahler/cards/card" + i + ".png")
                            .toExternalForm()
            );

            cards.add(new Card(suit, value, img));
        }

        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) reset();
        return cards.remove(0);
    }

}

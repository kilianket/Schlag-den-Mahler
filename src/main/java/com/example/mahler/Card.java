package com.example.mahler;

import javafx.scene.image.Image;

public class Card {

    private final String suit;   // Karo, Herz, Kreuz, Pik
    private final int value;     // 2–14 (Ass = 14)
    private final Image image;

    public Card(String suit, int value, Image image) {
        this.suit = suit;
        this.value = value;
        this.image = image;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public boolean isRed() {
        return suit.equals("Herz") || suit.equals("Karo");
    }
}

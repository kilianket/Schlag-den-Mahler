package com.example.mahler;

public class Card {
    private final String suit;   // Karo, Herz, Kreuz, Pik
    private final int value;      // 2–14 (Ass = 14)
    private final String color;   // rot oder schwarz

    public Card(String suit, int value, String color) {
        this.suit = suit;
        this.value = value;
        this.color = color;
    }

    public String getSuit() { return suit; }
    public int getValue() { return value; }
    public String getColor() { return color; }
}


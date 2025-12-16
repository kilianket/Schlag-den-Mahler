package com.example.mahler;

public class Card {
    private final String suit;
    private final int value;
    private final String color;
    private final String imagePath; // neuer Pfad

    public Card(String suit, int value, String color, String imagePath) {
        this.suit = suit;
        this.value = value;
        this.color = color;
        this.imagePath = imagePath;
    }

    public String getSuit() { return suit; }
    public int getValue() { return value; }
    public String getColor() { return color; }
    public String getImagePath() { return imagePath; }
}

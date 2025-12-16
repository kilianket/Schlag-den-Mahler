package com.example.mahler;

import javafx.scene.image.Image;

public class Card {
    public String suit;  // Kreuz, Pik, Herz, Karo
    public String rank;  // 2..10, Bube, Dame, König, Ass
    public Image image;

    public Card(String suit, String rank, Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }

    public boolean isRed() {
        return suit.equals("Herz") || suit.equals("Karo");
    }

    public int getValue() {
        switch(rank) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "Bube": return 11;
            case "Dame": return 12;
            case "König": return 13;
            case "Ass": return 14;
            default: return 0;
        }
    }
}

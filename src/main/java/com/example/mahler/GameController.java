package com.example.mahler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {

    @FXML
    private ImageView ivDeck;

    @FXML
    private Label infoLabel;

    @FXML
    private HBox boxColor, boxHighLow, boxBetween, boxSuit;

    private List<Card> deck;
    private int currentCardIndex;
    private Card previousCard;

    @FXML
    public void initialize() {
        initializeDeck();
        currentCardIndex = 0;
        previousCard = null;

        infoLabel.setText("Rate die Farbe!");
        showNextCardBack();

        showOnly(boxColor);
    }

    // ------------------ Kartenspiel-Logik ------------------

    private void initializeDeck() {
        deck = new ArrayList<>();
        String[] suits = {"Kreuz", "Pik", "Herz", "Karo"};
        String[] colors = {"schwarz", "rot"};

        for (int i = 2; i <= 14; i++) { // 11=Bube, 12=Dame, 13=König, 14=Ass
            for (int s = 0; s < 4; s++) {
                String color = (s < 2) ? "schwarz" : "rot"; // Kreuz/Pik schwarz, Herz/Karo rot
                deck.add(new Card(i, suits[s], color));
            }
        }
        Collections.shuffle(deck);
    }

    private void showNextCardBack() {
        loadImage("/com/example/mahler/cards/deck.png");
    }

    private void showCard(Card card) {
        loadImage("/com/example/mahler/cards/card" + (card.getIndex()) + ".png");
    }

    private Card drawCard() {
        if (currentCardIndex >= deck.size()) {
            Collections.shuffle(deck);
            currentCardIndex = 0;
        }
        return deck.get(currentCardIndex++);
    }

    private void showOnly(HBox box) {
        boxColor.setVisible(false);
        boxHighLow.setVisible(false);
        boxBetween.setVisible(false);
        boxSuit.setVisible(false);
        box.setVisible(true);
    }

    // ------------------ Runde 1: Farbe ------------------

    @FXML
    private void onColorBlack() { checkColor("schwarz"); }

    @FXML
    private void onColorRed() { checkColor("rot"); }

    private void checkColor(String guess) {
        Card card = drawCard();
        showCard(card);
        if (card.getColor().equals(guess)) {
            infoLabel.setText("Richtig! Rate, ob die nächste Karte höher oder niedriger ist.");
            previousCard = card;
            showOnly(boxHighLow);
        } else {
            infoLabel.setText("Falsch! Die Farbe war " + card.getColor() + ". Neues Spiel.");
            currentCardIndex = 0;
            previousCard = null;
            showOnly(boxColor);
            showNextCardBack();
        }
    }

    // ------------------ Runde 2: Höher/Niedriger ------------------

    @FXML
    private void onHigher() { checkHighLow(true); }

    @FXML
    private void onLower() { checkHighLow(false); }

    private void checkHighLow(boolean guessHigher) {
        Card card = drawCard();
        showCard(card);
        boolean correct = (guessHigher && card.getValue() > previousCard.getValue())
                || (!guessHigher && card.getValue() < previousCard.getValue())
                || (card.getValue() == previousCard.getValue()); // Gleich zählt als richtig

        if (correct) {
            infoLabel.setText("Richtig! Ist die nächste Karte zwischen oder außerhalb?");
            previousCard = card;
            showOnly(boxBetween);
        } else {
            infoLabel.setText("Falsch! Neues Spiel.");
            currentCardIndex = 0;
            previousCard = null;
            showOnly(boxColor);
            showNextCardBack();
        }
    }

    // ------------------ Runde 3: Zwischen/Außerhalb ------------------

    @FXML
    private void onBetween() { checkBetween(true); }

    @FXML
    private void onOutside() { checkBetween(false); }

    private void checkBetween(boolean guessBetween) {
        Card firstCard = deck.get(currentCardIndex - 2);
        Card secondCard = previousCard;
        Card card = drawCard();
        showCard(card);

        int min = Math.min(firstCard.getValue(), secondCard.getValue());
        int max = Math.max(firstCard.getValue(), secondCard.getValue());
        boolean isBetween = card.getValue() >= min && card.getValue() <= max;

        if (isBetween == guessBetween) {
            infoLabel.setText("Richtig! Welche Farbe hat die nächste Karte?");
            previousCard = card;
            showOnly(boxSuit);
        } else {
            infoLabel.setText("Falsch! Neues Spiel.");
            currentCardIndex = 0;
            previousCard = null;
            showOnly(boxColor);
            showNextCardBack();
        }
    }

    // ------------------ Runde 4: Farbe ------------------

    @FXML
    private void onSuitKaro() { checkSuit("Karo"); }

    @FXML
    private void onSuitHerz() { checkSuit("Herz"); }

    @FXML
    private void onSuitKreuz() { checkSuit("Kreuz"); }

    @FXML
    private void onSuitPik() { checkSuit("Pik"); }

    private void checkSuit(String guessSuit) {
        Card card = drawCard();
        showCard(card);
        if (card.getSuit().equals(guessSuit)) {
            infoLabel.setText("🎉 Gewonnen! Neues Spiel beginnt.");
        } else {
            infoLabel.setText("Falsch! Die Karte war " + card.getSuit() + ". Neues Spiel.");
        }
        // Reset für neues Spiel
        currentCardIndex = 0;
        previousCard = null;
        showOnly(boxColor);
        showNextCardBack();
    }

    // ------------------ Helfer ------------------

    private void loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("❌ Bild nicht gefunden: " + path);
            return;
        }
        ivDeck.setImage(new Image(url.toExternalForm()));
    }

    // ------------------ Kartenklasse ------------------

    private static class Card {
        private final int value;
        private final String suit;
        private final String color;

        public Card(int value, String suit, String color) {
            this.value = value;
            this.suit = suit;
            this.color = color;
        }

        public int getValue() { return value; }
        public String getSuit() { return suit; }
        public String getColor() { return color; }

        public int getIndex() {
            // Kartenindex 0..51 für Bildnamen card0.png..card51.png
            return (value - 2) + suitIndex(suit) * 13;
        }

        private int suitIndex(String suit) {
            switch (suit) {
                case "Kreuz": return 0;
                case "Pik": return 1;
                case "Herz": return 2;
                case "Karo": return 3;
            }
            return 0;
        }
    }
}

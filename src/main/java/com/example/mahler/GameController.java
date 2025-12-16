package com.example.mahler;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

public class GameController {

    private Deck deck = new Deck();
    private GameState state = GameState.COLOR;

    private Card lastCard;
    private Card secondLastCard;

    private VBox root = new VBox(15);
    private Label infoLabel = new Label("Rate die Farbe!");
    private HBox buttonBox = new HBox(10);

    public GameController() {
        setupUI();
    }

    private void setupUI() {
        root.setPadding(new Insets(20));
        root.getChildren().addAll(infoLabel, buttonBox);
        showColorButtons();
    }

    private void showColorButtons() {
        buttonBox.getChildren().clear();

        Button rot = new Button("Rot");
        Button schwarz = new Button("Schwarz");

        rot.setOnAction(e -> checkColor("rot"));
        schwarz.setOnAction(e -> checkColor("schwarz"));

        buttonBox.getChildren().addAll(rot, schwarz);
    }

    private void checkColor(String guess) {
        Card card = deck.drawCard();
        lastCard = card;

        if (card.getColor().equals(guess)) {
            state = GameState.HIGH_LOW;
            infoLabel.setText("Höher oder niedriger?");
            showHighLowButtons();
        } else {
            lose();
        }
    }

    private void showHighLowButtons() {
        buttonBox.getChildren().clear();

        Button higher = new Button("Höher");
        Button lower = new Button("Niedriger");

        higher.setOnAction(e -> checkHighLow(true));
        lower.setOnAction(e -> checkHighLow(false));

        buttonBox.getChildren().addAll(higher, lower);
    }

    private void checkHighLow(boolean higher) {
        Card card = deck.drawCard();
        secondLastCard = lastCard;
        lastCard = card;

        if (card.getValue() == secondLastCard.getValue() ||
                (higher && card.getValue() > secondLastCard.getValue()) ||
                (!higher && card.getValue() < secondLastCard.getValue())) {

            state = GameState.BETWEEN;
            infoLabel.setText("Zwischen oder außerhalb?");
            showBetweenButtons();
        } else {
            lose();
        }
    }

    private void showBetweenButtons() {
        buttonBox.getChildren().clear();

        Button between = new Button("Zwischen");
        Button outside = new Button("Außerhalb");

        between.setOnAction(e -> checkBetween(true));
        outside.setOnAction(e -> checkBetween(false));

        buttonBox.getChildren().addAll(between, outside);
    }

    private void checkBetween(boolean guessBetween) {
        Card card = deck.drawCard();
        Card first = secondLastCard;
        Card second = lastCard;

        lastCard = card;

        int min = Math.min(first.getValue(), second.getValue());
        int max = Math.max(first.getValue(), second.getValue());

        boolean isBetween = card.getValue() >= min && card.getValue() <= max;

        if (isBetween == guessBetween) {
            state = GameState.SUIT;
            infoLabel.setText("Welche Farbe hat die Karte?");
            showSuitButtons();
        } else {
            lose();
        }
    }


    private void showSuitButtons() {
        buttonBox.getChildren().clear();

        for (String suit : new String[]{"Karo", "Herz", "Kreuz", "Pik"}) {
            Button b = new Button(suit);
            b.setOnAction(e -> checkSuit(suit));
            buttonBox.getChildren().add(b);
        }
    }

    private void checkSuit(String guessSuit) {
        Card card = deck.drawCard();
        lastCard = card;

        if (card.getSuit().equals(guessSuit)) {
            win();
        } else {
            lose();
        }
    }


    private void win() {
        infoLabel.setText("🎉 Gewonnen!");
        buttonBox.getChildren().clear();
    }

    private void lose() {
        infoLabel.setText("❌ Verloren! Neues Spiel?");
        buttonBox.getChildren().clear();

        Button restart = new Button("Neustart");
        restart.setOnAction(e -> resetGame());

        buttonBox.getChildren().add(restart);
    }

    private void resetGame() {
        deck.reset();
        state = GameState.COLOR;
        lastCard = null;
        secondLastCard = null;
        infoLabel.setText("Rate die Farbe!");
        showColorButtons();
    }

    public Parent getView() {
        return root;
    }
}

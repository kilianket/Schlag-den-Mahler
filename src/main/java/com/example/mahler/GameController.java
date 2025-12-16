package com.example.mahler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;



public class GameController {

    @FXML private HBox boxColor;
    @FXML private HBox boxHighLow;
    @FXML private HBox boxBetween;
    @FXML private HBox boxSuit;


    @FXML
    private ImageView ivDeck;

    @FXML
    private Label infoLabel;

    private Deck deck;
    private Card currentCard;
    private Card previousCard;

    private enum Round {
        COLOR,
        HIGH_LOW,
        BETWEEN,
        SUIT
    }

    private Round round;

    // ===== INITIALIZE (NUR EINMAL!) =====
    @FXML
    public void initialize() {
        deck = new Deck();
        round = Round.COLOR;

        // Prüfen, ob die Datei gefunden wird
        URL url = getClass().getResource("/com/example/mahler/cards/back.png");
        if (url == null) {
            System.err.println("❌ back.png nicht gefunden!");
        } else {
            ivDeck.setImage(new Image(url.toExternalForm()));
        }

        infoLabel.setText("Runde 1: Rot oder Schwarz?");
        updateUI();
    }


    // ===== RUNDE 1 =====
    @FXML
    private void onColorRed() {
        checkColor(true);
    }

    @FXML
    private void onColorBlack() {
        checkColor(false);
    }

    private void checkColor(boolean guessedRed) {
        currentCard = deck.draw();
        ivDeck.setImage(currentCard.getImage());

        if (currentCard.isRed() == guessedRed) {
            round = Round.HIGH_LOW;
            infoLabel.setText("Richtig! Runde 2: Höher oder niedriger?");
        } else {
            loseGame();
        }
    }

    // ===== RUNDE 2 (Platzhalter, Buttons fehlen noch) =====
    private void checkHigher(boolean guessHigher) {
        previousCard = currentCard;
        currentCard = deck.draw();
        ivDeck.setImage(currentCard.getImage());

        if (currentCard.getValue() == previousCard.getValue()) {
            infoLabel.setText("Gleich! Nochmal raten.");
            return;
        }

        boolean higher = currentCard.getValue() > previousCard.getValue();

        if (higher == guessHigher) {
            round = Round.BETWEEN;
            infoLabel.setText("Richtig! Runde 3: Zwischen oder außerhalb?");
        } else {
            loseGame();
        }
    }

    // ===== VERLOREN =====
    private void loseGame() {
        infoLabel.setText("❌ Verloren! Neues Spiel.");
        deck.reset();
        round = Round.COLOR;

        ivDeck.setImage(
                new Image(getClass()
                        .getResource("/com/example/mahler/cards/back.png")
                        .toExternalForm())
        );
    }

    private void updateUI() {
        boxColor.setVisible(round == Round.COLOR);
        boxHighLow.setVisible(round == Round.HIGH_LOW);
        boxBetween.setVisible(round == Round.BETWEEN);
        boxSuit.setVisible(round == Round.SUIT);
    }

    @FXML
    private void onHigher() {
        checkHigher(true);
        updateUI();
    }

    @FXML
    private void onLower() {
        checkHigher(false);
        updateUI();
    }

    @FXML
    private void onBetween() {
        checkBetween(true);
        updateUI();
    }

    @FXML
    private void onOutside() {
        checkBetween(false);
        updateUI();
    }

    @FXML
    private void onSuitKaro() { checkSuit("KARO"); }
    @FXML
    private void onSuitHerz() { checkSuit("HERZ"); }
    @FXML
    private void onSuitKreuz() { checkSuit("KREUZ"); }
    @FXML
    private void onSuitPik()  { checkSuit("PIK"); }

    private void checkBetween(boolean guessedBetween) {
        infoLabel.setText("Runde 3 noch in Arbeit");
    }

    private void checkSuit(String suit) {
        infoLabel.setText("Runde 4 noch in Arbeit");
    }

}

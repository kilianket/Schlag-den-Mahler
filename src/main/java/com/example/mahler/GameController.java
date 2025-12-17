package com.example.mahler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class GameController {

    @FXML
    private ImageView ivDeck;

    @FXML
    private Label infoLabel;

    @FXML
    public void initialize() {
        loadImage("/com/example/mahler/cards/back.png");
        infoLabel.setText("Rate die Farbe!");
    }

    @FXML
    private void onColorBlack() {
        infoLabel.setText("Schwarz gewählt");
        showRandomCard();
    }

    @FXML
    private void onColorRed() {
        infoLabel.setText("Rot gewählt");
        showRandomCard();
    }

    private void showRandomCard() {
        int i = (int) (Math.random() * 52);
        loadImage("/com/example/mahler/cards/card" + i + ".png");
    }

    private void loadImage(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("❌ Bild nicht gefunden: " + path);
            return;
        }

        ivDeck.setImage(new Image(url.toExternalForm()));
    }
}

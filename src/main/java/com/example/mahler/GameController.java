package com.example.mahler;


import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import java.util.Random;

public class GameController {

    @FXML
    private ImageView ivDeck;

    @FXML
    private VBox root;

    @FXML
    private HBox buttonBox;

    @FXML
    private Label infoLabel;

    private Image[] cards = new Image[52];
    private Random rand = new Random();

    @FXML
    public void initialize() {
        // Karten laden
        for (int i = 0; i < 52; i++) {
            String path = "/com/example/mahler/cards/card" + i + ".png";
            cards[i] = new Image(getClass().getResource(path).toString());
        }
    }

    @FXML
    private void onColorRed() { showNewCard(); }

    @FXML
    private void onColorBlack() { showNewCard(); }

    private void showNewCard() {
        int t = rand.nextInt(52);
        ivDeck.setImage(cards[t]);
    }
}

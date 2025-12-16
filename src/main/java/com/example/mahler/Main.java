package com.example.mahler;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.getView(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Schlag den Mahler");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

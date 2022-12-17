package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Stage;
public class Main {

    private static Stage mainStage;

    public Main(Stage stage){
        mainStage = stage;
    }

    public static void lauchMain() throws IOException {

        Game2 game = new Game2(mainStage);
        game.main();

    }
}

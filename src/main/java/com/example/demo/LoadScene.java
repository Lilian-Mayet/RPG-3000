package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScene {

    private static Stage mainStage;

    public LoadScene(Stage stage){

        mainStage = stage;

    }



    public static void setScene(String fxmlfile) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlfile));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);

        mainStage.setTitle("RPG");
        mainStage.setScene(scene);
        mainStage.show();

    }

}

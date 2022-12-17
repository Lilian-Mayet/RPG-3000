package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseModeController implements Initializable {

    @FXML
    private Button consoleBut;

    @FXML
    private Button guiBut;
    @FXML
    private Label label;

    @FXML
    void startConsole(ActionEvent event) {
        guiBut.setVisible(false);
        consoleBut.setVisible(false);
        label.setVisible(true);
        Game game = new Game();
        game.start();

    }

    @FXML
    void startGUI(ActionEvent event) throws IOException {
        LoadScene.setScene("start.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setVisible(false);
    }
}


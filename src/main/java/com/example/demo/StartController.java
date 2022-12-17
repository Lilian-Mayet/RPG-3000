package com.example.demo;



import com.example.demo.Game;
import com.example.demo.Game_Data;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController  implements Initializable{

    @FXML
    private  Label nbHeroLabel;



    private  int nbHero;

    private Hero hero;






    @FXML
    public void start    () throws IOException {
        Game_Data.setNbHero(nbHero);
        Game_Data.setNbHeroTemp(nbHero);
        LoadScene.setScene("setHero.fxml");

    }

    @FXML
    public void set2(){
        this.nbHero = 2;
        updatenbHeroLable();
    }
    @FXML
    public void set3(){
        this.nbHero = 3;
        updatenbHeroLable();
    }
    @FXML
    public void set4(){
        this.nbHero = 4;
        updatenbHeroLable();
    }
    @FXML
    public void set5(){
        this.nbHero = 5;
        updatenbHeroLable();
    }
    @FXML
    public void set6(){
        this.nbHero = 6;
        updatenbHeroLable();

    }

    public void updatenbHeroLable() {
        this.nbHeroLabel.setText("Size of the team : " + Integer.toString(nbHero));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nbHero = 2;
        nbHeroLabel.setText("Size of the team : " + Integer.toString(nbHero));
    }
}

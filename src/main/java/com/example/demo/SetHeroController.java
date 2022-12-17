package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetHeroController implements Initializable {

    @FXML
    public Label labelError;
    @FXML
    public CheckBox mage;
    @FXML
    public CheckBox healer;
    @FXML
    public CheckBox hunter;
    @FXML
    public CheckBox warrior;

    @FXML
    public TextField textField;

    @FXML
    ImageView imageHero;

    static String type;
    static String name;

    static Hero hero;

    static Image imageMage;
    static Image imageWarrior;
    static Image imageHealer;
    static Image imageHunter;
    static Image imageNothing;

    static {
        try {
            imageMage = new Image(new FileInputStream("src/main/resources/image/mage.png"));
            imageWarrior = new Image(new FileInputStream("src/main/resources/image/warrior.png"));
            imageHunter = new Image(new FileInputStream("src/main/resources/image/hunter.png"));
            imageHealer = new Image(new FileInputStream("src/main/resources/image/healer.png"));
            imageNothing = new Image(new FileInputStream("src/main/resources/image/nothing.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public SetHeroController() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    @FXML
    public void tomage(){
        type = "mage";
        hunter.setSelected(false);
        warrior.setSelected(false);
        healer.setSelected(false);
        imageHero.setImage(imageMage);
    }

    @FXML
    public void tohealer(){
        type = "healer";
        hunter.setSelected(false);
        warrior.setSelected(false);
        mage.setSelected(false);
        imageHero.setImage(imageHealer);
    }

    @FXML
    public void towarrior(){
        type = "warrior";
        hunter.setSelected(false);
        mage.setSelected(false);
        healer.setSelected(false);
        imageHero.setImage(imageWarrior);
    }

    @FXML
    public void tohunter(){
        type = "hunter";
        mage.setSelected(false);
        warrior.setSelected(false);
        healer.setSelected(false);
        imageHero.setImage(imageHunter);
    }


    public void setLabelErrorVisible(){
        labelError.setText("Enter a valid name and select a class first");
    }

    @FXML
    public void addToTeam() throws IOException {
        boolean added = false;
        name = textField.getText();



        if (type==null || name.length()<3 ){
            setLabelErrorVisible();
        }
        else {
            switch (type) {
                case "mage" -> {
                    hero = new Mage(name, Game_Data.getEquipe());
                    added = true;
                }
                case "warrior" -> {
                    hero = new Warrior(name, Game_Data.getEquipe());
                    added = true;
                }
                case "healer" -> {
                    hero = new Healer(name, Game_Data.getEquipe());
                    added = true;
                }
                case "hunter" -> {
                    hero = new Hunter(name, Game_Data.getEquipe());
                    added = true;
                }
                default -> setLabelErrorVisible();
            }




        }

        if(added){
            Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
            if(Game_Data.getNbHeroTemp()==0){
                LoadScene.setScene("showHero.fxml");
            }
            else {
                LoadScene.setScene("setHero.fxml");
            }
        }


    }




}

package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowHeroController implements Initializable {

    @FXML
    public TableColumn<Hero,String> classType;
    @FXML
    public TableColumn<Hero,String> name;
    @FXML
    public TableColumn<Hero,String> healthpoint;
    @FXML
    public TableColumn<Hero,String> strenght;
    @FXML
    public TableColumn<Hero,String> defense;
    @FXML
    private TableView<Hero> tableHero;

    @FXML
    private Button fightButton;

    @FXML
    ImageView imageHero1;
    @FXML
    ImageView imageHero2;
    @FXML
    ImageView imageHero3;
    @FXML
    ImageView imageHero4;
    @FXML
    ImageView imageHero5;
    @FXML
    ImageView imageHero6;

    static Image imageMage;
    static Image imageWarrior;
    static Image imageHealer;
    static Image imageHunter;

    static {
        try {
            imageMage = new Image(new FileInputStream("src/main/resources/image/mage.png"));
            imageWarrior = new Image(new FileInputStream("src/main/resources/image/warrior.png"));
            imageHunter = new Image(new FileInputStream("src/main/resources/image/hunter.png"));
            imageHealer = new Image(new FileInputStream("src/main/resources/image/healer.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void gotoFight() throws IOException {
        LoadScene.setScene("fight.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeTable();
        System.out.println(Game_Data.getNbHero());
        if(Game_Data.getNbHero()>=2){
            setImage(imageHero1,Game_Data.getEquipe().getHero(0));
            setImage(imageHero2,Game_Data.getEquipe().getHero(1));
        }
        if(Game_Data.getNbHero()>=3){setImage(imageHero3,Game_Data.getEquipe().getHero(2));}
        if(Game_Data.getNbHero()>=4){setImage(imageHero4,Game_Data.getEquipe().getHero(3));}
        if(Game_Data.getNbHero()>=5){setImage(imageHero5,Game_Data.getEquipe().getHero(4));}
        if(Game_Data.getNbHero()==6){setImage(imageHero6,Game_Data.getEquipe().getHero(5));}
    }
    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = Game_Data.getEquipe();
        ObservableList<Hero> list = FXCollections.observableArrayList();
        for(int i=0;i<equipe.getSize();i++){
            list.add(equipe.getHero(i));
        }

        return list;

    }

    public void setImage(ImageView imageView,Hero hero){
        switch (hero.getClassType()){
            case "Healer":
                imageView.setImage(imageHealer);
                break;
            case "Hunter":
                imageView.setImage(imageHunter);
                break;
            case "Warrior":
                imageView.setImage(imageWarrior);
                break;
            case "Mage":
                imageView.setImage(imageMage);
                break;


        }

    }

    public void makeTable(){
        classType.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        name.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        healthpoint.setCellValueFactory(new PropertyValueFactory<Hero, String>("healthPoints"));
        strenght.setCellValueFactory(new PropertyValueFactory<Hero, String>("strenght"));
        defense.setCellValueFactory(new PropertyValueFactory<Hero, String>("defense"));

        tableHero.setItems(heroListMaker());



    }
}

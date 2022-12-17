package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class UpgradeMenuController implements Initializable {

    @FXML
    private Button buttonArrow;

    @FXML
    private Button buttonDefense;

    @FXML
    private Button buttonHealth;

    @FXML
    private Button buttonMana;

    @FXML
    private Button buttonStrenght;

    @FXML
    private ImageView imageHero;

    @FXML
    TableView<Hero> tableHero;

    @FXML
    private TableColumn<Hero,String> classTypeHero;
    @FXML
    private TableColumn<Hero,String> defenseHero;
    @FXML
    private TableColumn<Hero,String> healthpointHero;
    @FXML
    private TableColumn<Hero,String> nameHero;
    @FXML
    private TableColumn<Hero,String> strenghtHero;
    @FXML
    private TableColumn<Hero,String> manaHero;
    @FXML
    private TableColumn<Hero,String> arrowHero;

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

    Hero hero;

    @FXML
    void addArrow(ActionEvent event) throws IOException {
        ((Hunter) hero).updateArrow(10);
        Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
        if(Game_Data.getNbHeroTemp()==0){
            LoadScene.setScene("fight.fxml");
        }
        LoadScene.setScene("upgradeMenu.fxml");
    }

    @FXML
    void addDefense(ActionEvent event) throws IOException {
        hero.setDefense(hero.getDefense() + 10);
        Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
        if(Game_Data.getNbHeroTemp()==0){
            LoadScene.setScene("fight.fxml");
        }
        LoadScene.setScene("upgradeMenu.fxml");


    }

    @FXML
    void addHealth(ActionEvent event) throws IOException {
        hero.updateHP(200);
        Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
        if(Game_Data.getNbHeroTemp()==0){
            LoadScene.setScene("fight.fxml");
        }
        LoadScene.setScene("upgradeMenu.fxml");
    }

    @FXML
    void addMana(ActionEvent event) throws IOException {
        hero.updateMana(100);
        Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
        if(Game_Data.getNbHeroTemp()==0){
            LoadScene.setScene("fight.fxml");
        }
        LoadScene.setScene("upgradeMenu.fxml");
    }

    @FXML
    void addStrenght(ActionEvent event) throws IOException {
        hero.setStrenght((int) (hero.getStrenght()*1.4));
        Game_Data.setNbHeroTemp(Game_Data.getNbHeroTemp()-1);
        if(Game_Data.getNbHeroTemp()==0){
            LoadScene.setScene("fight.fxml");
        }
        LoadScene.setScene("upgradeMenu.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeTable();
        hero = Game_Data.getEquipe().getHero(Game_Data.getNbHeroTemp()-1);
        if(hero.getDefense()>80){
            buttonDefense.setVisible(false);
        }
        if(hero instanceof Warrior ){
            buttonArrow.setVisible(false);
            buttonMana.setVisible(false);
        }
        else if (hero instanceof Hunter) {
            buttonMana.setVisible(false);
        }
        else if (hero instanceof Mage ||  hero instanceof Healer) {
            buttonArrow.setVisible(false);
        }

        setImage(imageHero,hero);
    }

    public void makeTable() {
        classTypeHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        nameHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        healthpointHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("healthPoints"));
        strenghtHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("strenght"));
        defenseHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("defense"));
        manaHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("mana"));
        arrowHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("arrowLeft"));

        tableHero.setItems(heroListMaker());

    }

    public ObservableList<Hero> heroListMaker() {
        Equipe equipe = Game_Data.getEquipe();
        ObservableList<Hero> list = FXCollections.observableArrayList();

        list.add(equipe.getHero(Game_Data.getNbHeroTemp() - 1));


        return list;

    }


    public void setImage(ImageView imageView,Combatant c){
        switch (c.getClassType()){
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

            default:imageView.setImage(imageNothing);
        }

    }
}

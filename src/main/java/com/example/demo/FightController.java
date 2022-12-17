package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FightController implements Initializable {

    @FXML
    private Button attackButton;

    @FXML
    private Label attackerLabel;

    @FXML
    private Label targetLabel;

    @FXML
    private Label modeLabel;

    @FXML
    private Label infoLabel;
    @FXML
    private Label turnLabel;

    @FXML
    private Button setTargetBut;

    @FXML
    private Button setAttackerBut;

    @FXML
    private TableColumn<Enemy,String> classTypeEnemy;

    @FXML
    private TableColumn<Hero,String> classTypeHero;

    @FXML
    private TableColumn<Enemy,String> defenseEnemy;

    @FXML
    private TableColumn<Hero,String> defenseHero;

    @FXML
    private TableColumn<Enemy,String> healthpointEnemy;

    @FXML
    private TableColumn<Hero,String> healthpointHero;

    @FXML
    private TableColumn<Enemy,String> nameEnemy;

    @FXML
    private TableColumn<Hero,String> nameHero;

    @FXML
    private TableColumn<Enemy,String> strenghtEnemy;

    @FXML
    private TableColumn<Hero,String> strenghtHero;

    @FXML
    private TableColumn<Hero,String> manaHero;

    @FXML
    private TableColumn<Hero,String> arrowHero;

    @FXML
    private TableView<Enemy> tableEnemy;

    @FXML
    private TableView<Hero> tableHero;

    @FXML
    ImageView imageTarget;
    @FXML
    ImageView imageAttacker;


    static Image imageMage;
    static Image imageWarrior;
    static Image imageHealer;
    static Image imageHunter;
    static Image imageSkeleton;
    static Image imageGobelin;
    static Image imageGiant;
    static Image imageWitch;
    static Image imageBoss;
    static Image imageNothing;

    static {
        try {
            imageMage = new Image(new FileInputStream("src/main/resources/image/mage.png"));
            imageWarrior = new Image(new FileInputStream("src/main/resources/image/warrior.png"));
            imageHunter = new Image(new FileInputStream("src/main/resources/image/hunter.png"));
            imageHealer = new Image(new FileInputStream("src/main/resources/image/healer.png"));
            imageSkeleton = new Image(new FileInputStream("src/main/resources/image/skeleton.png"));
            imageGobelin = new Image(new FileInputStream("src/main/resources/image/gobelin.png"));
            imageGiant = new Image(new FileInputStream("src/main/resources/image/giant.png"));
            imageWitch = new Image(new FileInputStream("src/main/resources/image/witch.png"));
            imageBoss = new Image(new FileInputStream("src/main/resources/image/boss.png"));
            imageNothing = new Image(new FileInputStream("src/main/resources/image/nothing.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private Combatant attacker;
    private Combatant target;

    private String setMode;

    int enemyTurn;

    String turn;


    public void attackEnemy(Enemy enemy) throws InterruptedException {
        updateTurnLabel();
        Random r = new Random();
        Combatant heroTarget = Game_Data.getEquipe().getHero(r.nextInt(Game_Data.getEquipe().getSize()-1));
        enemy.action(heroTarget);
        infoLabel.setText(enemy.getName()+" attacked "+ heroTarget.getName()+"!");
        Thread.sleep(1000);
        updateTurn();
    }

    @FXML
    public void attack(ActionEvent event) throws IOException, InterruptedException {

        attacker.action(target);
        infoLabel.setText(attacker.getName()+" attacked "+ target.getName()+"!");
        Thread.sleep(1000);
        if (target.getHealthPoints()<=0){
            if (target instanceof Hero){
                infoLabel.setText(target.getName()+" died");
                Game_Data.getEquipe().removeHero(( (Hero) target));
                if(attacker instanceof Hunter){
                    ((Hunter) attacker).updateArrow(2);
                }
            }
            else {
                Game_Data.getHorde().removeEnemy(( (Enemy) target));
                if(Game_Data.getHorde().getSize()==0){
                    Game_Data.nextLevel();
                    Game_Data.setNbHeroTemp(Game_Data.getEquipe().getSize());

                    LoadScene.setScene("upgradeMenu.fxml");
                }
                else {
                    target = Game_Data.getHorde().getEnemy(0);
                    setTargetLabel();
                }
            }
        }
        makeTable();
        tableEnemy.refresh();
        tableHero.refresh();
        setImage(imageAttacker,attacker);
        setImage(imageTarget,target);
        updateTurn();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enemyTurn = -1;
        turn = "hero";
        try {
            updateTurnLabel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Game2.setHorde(Game_Data.getLevel(),Game_Data.getEquipe().getSize());
        attacker = Game_Data.getEquipe().getHero(0);
        target = Game_Data.getHorde().getEnemy(0);
        attackerLabel.setText("Attacker :" + attacker.getName());
        targetLabel.setText("Target :"+target.getName());
        makeTable();
        setMode = "toAttacker";
        modeLabel.setText("Choose Attacker");
        setImage(imageAttacker,attacker);
        setImage(imageTarget,target);
    }


    public ObservableList<Hero> heroListMaker(){
        Equipe equipe = Game_Data.getEquipe();
        ObservableList<Hero> list = FXCollections.observableArrayList();
        for(int i=0;i<equipe.getSize();i++){
            list.add(equipe.getHero(i));
        }

        return list;

    }

    public void updateTurnLabel() throws InterruptedException {
        if(turn=="hero"){turnLabel.setText("It's your turn");}
        else{
            turnLabel.setText("Turn of the enemies");

        }

    }

    public void updateTurn() throws InterruptedException {

        Random r = new Random();
        if (r.nextBoolean()){
            turn = "hero";
            updateTurnLabel();
        }
        else {turn = "enemy";

            updateTurnLabel();
            if(enemyTurn<Game_Data.getHorde().getSize()-1){
                enemyTurn++;
            }
            else {
                enemyTurn=0;
            }

        attackEnemy(Game_Data.getHorde().getEnemy(enemyTurn));
        }


    }


    public ObservableList<Enemy> enemyListMaker(){
        Horde horde = Game_Data.getHorde();
        ObservableList<Enemy> list = FXCollections.observableArrayList();
        for(int i=0;i<horde.getSize();i++){
            list.add(horde.getEnemy(i));
        }

        return list;

    }




    public void makeTable(){
        classTypeHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("classType"));
        nameHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("name"));
        healthpointHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("healthPoints"));
        strenghtHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("strenght"));
        defenseHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("defense"));
        manaHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("mana"));
        arrowHero.setCellValueFactory(new PropertyValueFactory<Hero, String>("arrowLeft"));

        tableHero.setItems(heroListMaker());

        classTypeEnemy.setCellValueFactory(new PropertyValueFactory<Enemy, String>("classType"));
        nameEnemy.setCellValueFactory(new PropertyValueFactory<Enemy, String>("name"));
        healthpointEnemy.setCellValueFactory(new PropertyValueFactory<Enemy, String>("healthPoints"));
        strenghtEnemy.setCellValueFactory(new PropertyValueFactory<Enemy, String>("strenght"));
        defenseEnemy.setCellValueFactory(new PropertyValueFactory<Enemy, String>("defense"));

        tableEnemy.setItems(enemyListMaker());

    }


    public void toAttacker() {
        setMode = "toAttacker";
        modeLabel.setText("Choose Attacker");
    }

    public void toTarget() {
        setMode = "toTarget";
        modeLabel.setText("Choose Target");
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
            case "Skeleton":
                imageView.setImage(imageSkeleton);
                break;
            case "Gobelin":
                imageView.setImage(imageGobelin);
                break;
            case "Giant":
                imageView.setImage(imageGiant);
                break;
            case "Witch":
                imageView.setImage(imageWitch);
                break;
            case "Boss":
                imageView.setImage(imageBoss);
                break;
            default:imageView.setImage(imageNothing);
        }

    }


    @FXML
    public void setAttackerLabel(){
        attackerLabel.setText("Attacker : " +attacker.getName());
    }
    @FXML
    public void setTargetLabel(){
        targetLabel.setText("Target : " + target.getName());
    }

    public void setAttacker(){
        attacker = tableHero.getSelectionModel().getSelectedItem();
        setAttackerLabel();
    }

    public void setCombatant2(){
        if (setMode =="toTarget"){
            target = tableEnemy.getSelectionModel().getSelectedItem();
        }
        else {
            attacker = tableEnemy.getSelectionModel().getSelectedItem();

        }
        setTargetLabel();
        setAttackerLabel();
        setImage(imageAttacker,attacker);
        setImage(imageTarget,target);
    }


    public void setCombatant1(){
        if (setMode =="toTarget"){
            target = tableHero.getSelectionModel().getSelectedItem();
        }
        else {
            attacker = tableHero.getSelectionModel().getSelectedItem();

        }
        setTargetLabel();
        setAttackerLabel();
        setImage(imageAttacker,attacker);
        setImage(imageTarget,target);
    }

    public void setTarget(){
        target = tableEnemy.getSelectionModel().getSelectedItem();
        setTargetLabel();
    }


}

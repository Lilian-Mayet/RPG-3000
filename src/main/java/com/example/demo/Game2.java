package com.example.demo;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game2 {

    private Stage mainStage;
    private static Game_Data game_Data;


    public Game2(Stage stage){

        mainStage = stage;
        game_Data = new Game_Data();
    }



    public void main() throws IOException {

        LoadScene loadScene = new LoadScene(mainStage);
        loadScene.setScene("chooseMode.fxml");

    }
    static ArrayList<Weapon> getAllWeapon(Combatant c){
        ArrayList<Weapon> allWeapon = new ArrayList<>();

        Stick stick = new Stick();
        Sword sword = new Sword();
        Axe axe = new Axe();
        HealingStick healingStick = new HealingStick();
        Sceptre sceptre = new Sceptre();
        Hammer hammer = new Hammer();
        Giant_Sword giant_sword = new Giant_Sword();

        if(c instanceof Hero){
            allWeapon.add(sword);
            allWeapon.add(axe);
            allWeapon.add(healingStick);
            allWeapon.add(sceptre);
            allWeapon.add(stick);
        }
        else if(c instanceof Gobelin){
            allWeapon.add(sword);
            allWeapon.add(stick);
        }
        else if(c instanceof Witch){
            allWeapon.add(sceptre);
            allWeapon.add(healingStick);
        }
        else if(c instanceof Giant){
            allWeapon.add(hammer);
        }
        else if (c instanceof Boss) {
            allWeapon.add(giant_sword);
        }
        return allWeapon;

    }


    static void setHorde(int level,int nbHero){
        Horde horde = new Horde();
        Random rand = new Random();
        int skelID = 1;
        int giantID = 1;
        int witchID =1;
        int gobID = 1;



        switch (level){

            case 1:
                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Skeleton("Skeleton "+(i+1));
                    horde.addEnemy(enemy);
                }
                break;

            case 2:
                for(int i =0;i<nbHero;i++) {
                    Enemy enemy = new Gobelin("Gobelin " + (gobID));
                    gobID++;
                    horde.addEnemy(enemy);
                }

                for(int i =0;i<nbHero;i++) {
                    Enemy enemy = new Skeleton("Skeleton "+(skelID));
                    skelID++;
                    horde.addEnemy(enemy);
                }

                break;



            case 3:
                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Giant("Giant "+giantID);
                    giantID++;

                    horde.addEnemy(enemy);
                    }

                for(int i =0;i<(int)nbHero/2;i++){
                    Enemy enemy = new Skeleton("Skeleton "+skelID);
                    skelID++;

                    horde.addEnemy(enemy);
                }


                break;

            case 4:
                if( (nbHero/2)%2==0 ){
                    nbHero/=2;
                }
                else nbHero = nbHero/2 +1;

                for(int i =0;i<(int)nbHero/2;i++){
                    Enemy enemy = new Giant("Giant "+(giantID));
                    giantID++;
                    horde.addEnemy(enemy);
                }
                for(int i =0;i<(int)nbHero/2;i++){
                    Enemy enemy = new Witch("Witch "+(witchID));
                    witchID++;
                    horde.addEnemy(enemy);
                }
                for(int i =0;i<(int)nbHero/2;i++) {
                    Enemy enemy = new Gobelin("Gobelin" + (gobID));
                    gobID++;
                    horde.addEnemy(enemy);
                }
                break;

            case 5:
                Enemy enemy = new Boss(500*nbHero,15*nbHero,50*nbHero);

                horde.addEnemy(enemy);
                break;
        }



    Game_Data.setHorde(horde);
    }


}

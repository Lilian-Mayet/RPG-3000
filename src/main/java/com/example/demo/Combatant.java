package com.example.demo;

import java.util.ArrayList;


public abstract class Combatant {

    private String classType;
    private String name;
    private int healthPoints;
    private int strenght;
    private  int mana;
    private  int defense;
    private  boolean isAlive;


    private int[] position;
    private ArrayList<Consummable> inventory;
    private ArrayList<Weapon> armory;



    public Combatant(String name){
        this.name = name;
        this.armory= new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.isAlive = true;

    }



    public void action(Combatant combatant){

    }


    //setter
    public void setClassType(String type){
        this.classType = type;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    //getter

    public String getName(){return this.name;}

    public String getClassType(){return this.classType;}
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getStrenght() {
        return this.strenght;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getMana() {
        return this.mana;
    }

    public boolean getIsAlive(){return this.isAlive;}

    public ArrayList<Weapon> getArmory() {
        return this.armory;
    }

    public ArrayList<Consummable> getInventory() {
        return this.inventory;
    }




    //Update

    public void addArmory(Weapon weapon){
        this.armory.add(weapon);
    }

    public void deleteArmory(Weapon weapon){
        this.armory.remove(weapon);
    }

    public void addInventory(Consummable consummable){
        this.inventory.add(consummable);
    }

    public void deleteInventory(Consummable consummable){this.inventory.remove(consummable);}

    public void updateHP(int delta){
        this.healthPoints += delta;
    }

    public void updateMana(int delta){
        this.mana += delta;
    }

    public void updateAlive(){
        if(this.healthPoints<=0){
            this.isAlive = false;
        }
    }


    //print
    public void printInventory(InputParser inputParser) {
        if (this.getInventory().size() == 0) {
            inputParser.printMessage("You don't have any item.");
        } else {
            inputParser.printMessage("You have : ");
            for (int i = 0; i < this.getInventory().size(); i++) {
                if (i == this.getInventory().size() - 1) {
                    inputParser.printMessage(this.getInventory().get(i).name + " .");
                } else {
                    inputParser.printMessage(this.getInventory().get(i).name + " / ");
                }

            }
            inputParser.printMessage("");
        }
        inputParser.printMessage("In your armory you have : ");
        for (int i = 0; i < this.getArmory().size(); i++) {
                inputParser.printMessage((i+1) + " : " +this.getArmory().get(i).name);
        }


    }






}

package com.example.demo;

import java.util.Random;

public class Witch extends Enemy {

    public Witch(String name){
        super(name);
        this.setHealthPoints(200);
        this.setStrenght(45);
        this.setMana(100);
        this.setDefense(13);
        this.setClassType("Witch");

    }

    @Override
    public void interact(Combatant target, InputParser inputParser){
        Random rand = new Random();
        int invSize = this.getArmory().size();
        int choice = rand.nextInt(invSize);
        this.getArmory().get(choice).interaction(this,target,inputParser);



    }
}

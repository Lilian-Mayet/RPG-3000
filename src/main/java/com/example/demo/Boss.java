package com.example.demo;

public class Boss extends Enemy {

    public Boss(int hp,int strenght,int mana){
        super("Boss");
        this.setHealthPoints(hp);
        this.setStrenght(strenght);
        this.setMana(mana);
        this.setDefense(50);
        this.setClassType("Boss");
    }
}

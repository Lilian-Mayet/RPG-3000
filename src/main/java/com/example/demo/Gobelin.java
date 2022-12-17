package com.example.demo;

public class Gobelin extends Enemy{
    public Gobelin(String name){
        super(name);
        this.setHealthPoints(100);
        this.setStrenght(10);
        this.setMana(0);
        this.setDefense(10);
        this.setClassType("Gobelin");

    }
}

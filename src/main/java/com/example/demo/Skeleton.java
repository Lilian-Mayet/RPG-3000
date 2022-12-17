package com.example.demo;

public class Skeleton extends Enemy{
    public Skeleton(String name){
        super(name);
        this.setHealthPoints(50);
        this.setStrenght(5);
        this.setMana(0);
        this.setDefense(5);
        this.setClassType("Skeleton");

    }

}

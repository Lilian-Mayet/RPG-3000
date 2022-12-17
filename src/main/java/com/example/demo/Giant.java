package com.example.demo;

public class Giant extends Enemy{

    public Giant(String name){
        super(name);
        this.setHealthPoints(300);
        this.setStrenght(20);
        this.setMana(0);
        this.setDefense(50);
        this.setClassType("Giant");
    }


}

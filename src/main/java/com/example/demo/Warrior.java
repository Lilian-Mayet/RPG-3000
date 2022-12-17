package com.example.demo;

public class Warrior extends Hero {
    private double damageBoost;

    public Warrior(String name, Equipe equipe) {
        super(name);
        this.setHealthPoints(200);
        this.setMana(0);
        this.setDefense(50);
        this.setStrenght(30);

        this.damageBoost=2;
        equipe.addHero(this);
        Sword sword = new Sword();
        this.addArmory(sword);
        setClassType("Warrior");
    }

    public double getDamageBoost() {
        return damageBoost;
    }

    public void updateDamageBoost(double boost){
        this.damageBoost *= boost;
    }
}

package com.example.demo;

public class Healer extends SpellCaster {

    private double healBoost;


    public Healer(String name,Equipe equipe) {
        super(name);
        this.healBoost = 1.5;
        this.setHealthPoints(110);
        this.setDefense(20);
        this.setMana(80);
        this.setStrenght(30);
        equipe.addHero(this);
        setClassType("Healer");
    }

    @Override
    public void action(Combatant target){
        if(this.getMana()>0){
            target.updateHP(this.getStrenght());
            this.updateMana(-10);
        }

    }

    public double getHealBoost(){return this.healBoost;}

    public void updateHealBoost(double boost){
        this.healBoost *= boost;
    }

}

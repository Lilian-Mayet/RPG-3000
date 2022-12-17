package com.example.demo;

public class Mage extends SpellCaster {

    private double spellBoost;

    public Mage(String name,Equipe equipe) {
        super(name);
        this.spellBoost = 1.2;
        this.setHealthPoints(125);
        this.setStrenght(20);
        this.setDefense(27);
        this.setMana(100);
        equipe.addHero(this);
        setClassType("Mage");
    }

    @Override
    public void action(Combatant target){
        if(this.getMana()>0){
            int damage = this.getStrenght();
            int damageDealed = (int)Math.round(damage*(100-target.getDefense())/100);
            target.updateHP(-damageDealed);
            this.updateMana(-10);
        }
    }


    public double getSpellBoost() {
        return spellBoost;
    }

    public void updateSpellBoost(double boost){
        this.spellBoost *= boost;
    }
}

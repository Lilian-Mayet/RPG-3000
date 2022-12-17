package com.example.demo;

public class ManaPotion extends Potion {

    public ManaPotion(){
        this.name = "mana potion";
        this.manaBoost = 30;
    }

    @Override
    public void consume(Combatant c1) {
        c1.updateMana(this.manaBoost);
    }


}

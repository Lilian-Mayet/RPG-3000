package com.example.demo;

public class Food extends Consummable{





    @Override
    public void consume(Combatant c1) {
        c1.updateHP(this.heal);
    }
}

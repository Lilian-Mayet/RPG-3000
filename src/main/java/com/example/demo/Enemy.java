package com.example.demo;

import java.util.Random;


public class Enemy extends Combatant{


    public Enemy(String name) {
        super(name);
    }


    @Override
    public void action(Combatant target){
        int damage = this.getStrenght();
        int damageDealed = (int)Math.round(damage*(100-target.getDefense())/100);

        target.updateHP(-damageDealed);
    }

    public void interact(Combatant target, InputParser inputParser){
        Random rand = new Random();
        int invSize = this.getArmory().size();
        int choice = rand.nextInt(invSize);
        this.getArmory().get(choice).interaction(this,target,inputParser);



    }

}

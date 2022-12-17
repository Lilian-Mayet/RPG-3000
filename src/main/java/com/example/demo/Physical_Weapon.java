package com.example.demo;

public class Physical_Weapon extends Weapon {
    public int damage;

    @Override
    public void interaction(Combatant attacker, Combatant target, InputParser inputParser) {

        int damage = this.damage*attacker.getStrenght();

        if(attacker instanceof Warrior){damage*=((Warrior) attacker).getDamageBoost();}



        int damageDealed = (int)Math.round(damage*(100-target.getDefense())/100);

        target.updateHP(-damageDealed);

        //message for terminal game
        inputParser.printMessage(attacker.getName() + " dealed " + damageDealed+" to " + target.getName());
        inputParser.printMessage(" with his " + this.name + ". ");
        inputParser.printMessage(target.getName() + " now have " + target.getHealthPoints() + " hp.");

    }


    public int getDamage(){return this.damage;}

}

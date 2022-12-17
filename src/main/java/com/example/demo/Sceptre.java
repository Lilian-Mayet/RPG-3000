package com.example.demo;

public class Sceptre extends Magical_Weapon{

    public Sceptre(){
        this.name = "screptre";
        this.cost = 20;
        this.damage = 60;
    }

    @Override
    public void interaction(Combatant attacker, Combatant target,InputParser inputParser) {
        if(attacker.getMana()>=this.cost){
            attacker.updateMana(-this.cost);
            int damage = this.damage;
            if(attacker instanceof Mage){damage = (int)Math.round(damage*((Mage) attacker).getSpellBoost());}
            target.updateHP(-damage);
            inputParser.printMessage(attacker.getName() + " spent " + this.cost + " mana to deal " + target.getName() + " " + this.damage + "damage");
            inputParser.printMessage(target.getName() + " now have " + target.getHealthPoints() + "hp");
        }
        else{
            inputParser.printMessage("You don't have enought mana");
        }

    }

}

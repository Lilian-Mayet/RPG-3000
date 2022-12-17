package com.example.demo;

import java.util.Scanner;

public class Hero extends Combatant{


    public Hero(String name) {
        super(name);

    }

    @Override
    public void action(Combatant target){
        int damage = this.getStrenght();
        int damageDealed = (int)Math.round(damage*(100-target.getDefense())/100);
        target.updateHP(-damageDealed);
    }

    //action
    public void interact(Combatant target, InputParser inputParser){
        inputParser.printMessage("You are about to interact with " + target.getName() + " (HP = " + target.getHealthPoints());
        inputParser.printMessage(" , defense = " + target.getDefense() + " )");
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        if (this.getArmory().size()>1){
            String name1 = this.getArmory().get(0).name;
            String name2 = this.getArmory().get(1).name;

            System.out.println("Do you want to use your first weapon (" + name1 + ") or your second (" + name2 + ") ?");
            System.out.println("Type 1 or 2:");

            choice = inputParser.getInt() -1;
        }

        this.getArmory().get(choice).interaction(this,target,inputParser);



    }


    public void consume(InputParser inputParser){
        Scanner scan = new Scanner(System.in);

        if (this.getInventory().size() == 0) {
            inputParser.printMessage("You don't have any item sorry.");
        } else {
            inputParser.printMessage("You have : ");
            for (int i = 0; i < this.getInventory().size(); i++) {
                if (i == this.getInventory().size() - 1) {
                    inputParser.printMessage("(" + (i +1)+ ")" + this.getInventory().get(i).name + " .");

                } else {
                    inputParser.printMessage("(" + (i +1)+ ")" +this.getInventory().get(i).name + " / ");
                }
            }

            inputParser.printMessage("What item do you want to use? Type it's number : ");

            int choice = inputParser.getInt()-1;
            this.getInventory().get(choice).consume(this);
            this.deleteInventory(this.getInventory().get(choice));
            inputParser.printMessage(this.getName() +  " now has " + this.getHealthPoints() + "hp and " + this.getMana() + " mana");


        }


    }

    public int getArrowLeft(){return 0;}


}

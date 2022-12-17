package com.example.demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public  void start() {


    ConsoleParser inputParser = new ConsoleParser();



    Horde horde;













    Equipe equipe = createEquipe(inputParser);

    for (int level = 1;level<5;level++) {

            horde = createHorde(level, equipe.getSize());

            inputParser.printMessage("Your team : ");
            equipe.printHero();

            inputParser.printMessage("");
            inputParser.printMessage("Enemy team:");
            horde.printEnemy();

            boolean victory;
            victory = fight(equipe, horde, inputParser);

            if (victory) {

                inputParser.printMessage("\n" + "\n" + "\n");
                inputParser.printMessage("You defeated the enemy team");
                equipe.upgrade();
                level++;
            }
            else {
                inputParser.printMessage("\n" + "\n" + "\n");
                inputParser.printMessage("You have been defeated");
                break;

            }


    }

    }



    static boolean fight(Equipe equipe,Horde horde,InputParser inputParser){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean done = false;

        ArrayList<Combatant> allCombatant = new ArrayList<>();
        ArrayList<Combatant> deadCombatant = new ArrayList<>();
        while (!done){

            if(allCombatant.size()==0) {
                for (int i = 0; i < equipe.getSize(); i++) {
                    allCombatant.add(equipe.getHero(i));
                }
                for (int i = 0; i < horde.getSize(); i++) {
                    allCombatant.add(horde.getEnemy(i));
                }
            }


            int turn = rand.nextInt(allCombatant.size());



            inputParser.printMessage("\n");
            inputParser.printMessage("It's the turn of " + allCombatant.get(turn).getName()+"("+allCombatant.get(turn).getClass()+")");

            if (allCombatant.get(turn) instanceof Hero){
                System.out.print("He has " + allCombatant.get(turn).getHealthPoints()  + " hp and " );
                System.out.println(allCombatant.get(turn).getMana() + " mana.");

                //action du hero
                boolean choiceDone = false;
                while (choiceDone==false){
                    inputParser.printMessage("Do you want to see the inventory(1), interact(2) or use an item(3)?");
                    inputParser.printMessage("Type 1, 2 or 3 : ");
                    int choice = inputParser.getIntRanged(1,3);
                    if (choice==1){
                        allCombatant.get(turn).printInventory(inputParser);
                        inputParser.printMessage("\n");
                    }

                    else if (choice==2){
                        choiceDone = true;

                        inputParser.printMessage("Do you want to target your team(1) or the enemy team(2)?");
                        inputParser.printMessage("Type 1 or 2 :");
                        int choice2 = inputParser.getIntRanged(1,2);
                        if (choice2==1){
                            inputParser.printMessage("Your team is composed of :");
                            equipe.printHero();
                            inputParser.printMessage("Wich one do you want to target?");
                            inputParser.printMessage("Type is number");
                            int target = scan.nextInt();
                            ((Hero) allCombatant.get(turn)).interact(equipe.getHero(target-1),inputParser);
                        }

                        else if (choice2==2){
                            inputParser.printMessage("The enemy team is composed of :");
                            horde.printEnemy();
                            inputParser.printMessage("Wich one do you want to target?");
                            inputParser.printMessage("Type his number");
                            int target = inputParser.getInt();
                            ((Hero) allCombatant.get(turn)).interact(horde.getEnemy(target-1),inputParser);
                            horde.getEnemy(target-1).updateAlive();
                            if (horde.getEnemy(target-1).getIsAlive()==false){
                                inputParser.printMessage(horde.getEnemy(target-1).getName() + " is dead");
                                deadCombatant.add(horde.getEnemy(target-1));
                                horde.removeEnemy(horde.getEnemy(target-1));
                                }


                        }

                        inputParser.printMessage("\n");

                    }

                    else if (choice==3){
                        choiceDone = true;
                        ((Hero) allCombatant.get(turn)).consume(inputParser);
                    }

                    else{
                        inputParser.printMessage("Instruction unclear");
                        inputParser.printMessage("\n");
                    }
                }
            }

            else if (allCombatant.get(turn) instanceof Enemy){
                boolean healing = false;

                if (allCombatant.get(turn) instanceof Witch){
                    if (allCombatant.get(turn).getArmory().get(0) instanceof HealingStick){
                        healing =true;
                    }
                }

                int target = rand.nextInt(equipe.getSize());

                if(healing){
                    target = rand.nextInt(horde.getSize());
                    ((Enemy) allCombatant.get(turn)).interact(horde.getEnemy(target),inputParser);
                }
                else {
                    ((Enemy) allCombatant.get(turn)).interact(equipe.getHero(target),inputParser);
                    equipe.getHero(target).updateAlive();
                }

                if (equipe.getHero(target).getIsAlive()==false){

                    inputParser.printMessage(equipe.getHero(target).getName() + " is dead");
                    deadCombatant.add(equipe.getHero(target));
                    equipe.removeHero(equipe.getHero(target));
                    }


            }

            allCombatant.remove(allCombatant.get(turn));


            for(int i=0;i<deadCombatant.size();i++){
                allCombatant.remove(deadCombatant.get(i));
                deadCombatant.remove(deadCombatant.get(i));
            }



            if (equipe.getSize()==0 || horde.getSize()==0){
                done=true;
            }


        }


        if (equipe.getSize()==0){
            return false;
        }
        else {return true;}

    }



    static Horde createHorde(int level,int nbHero){
        Horde horde = new Horde();
        Random rand = new Random();




        switch (level){
            case 1:
                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Gobelin("Gobelin_"+(i+1));
                    ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                    int windex =   rand.nextInt(allWeapon.size());
                    enemy.addArmory(allWeapon.get(windex));
                    horde.addEnemy(enemy);
                }
                break;

            case 2:
                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Gobelin("test");
                    int type = rand.nextInt(1);
                    switch (type){

                        case 0:
                            enemy = new Gobelin("Gobelin_"+(i+1));
                            break;
                        case 1:
                            enemy = new Gobelin("Witch_"+(i+1));
                    }

                    ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                    int windex =   rand.nextInt(allWeapon.size());
                    enemy.addArmory(allWeapon.get(windex));
                    horde.addEnemy(enemy);
                }
                break;

            case 3:
                if( (nbHero/2)%2==0 ){
                    nbHero/=2;
                }
                else nbHero = nbHero/2 +1;

                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Giant("Giant_"+(i+1));
                    ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                    int windex =   rand.nextInt(allWeapon.size());
                    enemy.addArmory(allWeapon.get(windex));
                    horde.addEnemy(enemy);
                }
                for(int i =0;i<nbHero;i++){
                    Enemy enemy = new Witch("Witch_"+(i+1));
                    ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                    int windex =   rand.nextInt(allWeapon.size());
                    enemy.addArmory(allWeapon.get(windex));
                    horde.addEnemy(enemy);
                }
                for(int i =0;i<1;i++) {
                    Enemy enemy = new Gobelin("Gobelin_" + (1));
                    ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                    int windex = rand.nextInt(allWeapon.size());
                    enemy.addArmory(allWeapon.get(windex));
                    horde.addEnemy(enemy);
                }
                break;

            case 4:
                Enemy enemy = new Boss(500*nbHero,15*nbHero,50*nbHero);
                ArrayList<Weapon> allWeapon = getAllWeapon(enemy);
                int windex =   rand.nextInt(allWeapon.size());
                enemy.addArmory(allWeapon.get(windex));
                horde.addEnemy(enemy);
                break;
        }

        return horde;

    }



    static Equipe createEquipe(InputParser inputParser){







        Equipe equipe = new Equipe();
        inputParser.printMessage("Combien de hero veux tu?");
        int numberOfHero = inputParser.getInt();




        for (int i=0;i<numberOfHero;i++){
            inputParser.printMessage("Quelle type de hero veut tu?");
            inputParser.printMessage("1 = Warrior, 2 = Hunter, 3 = Healer, 4 = Mage");
            int choice = inputParser.getIntRanged(1,4);
            String name;

            switch (choice){
                case 1:
                    inputParser.printMessage("Quel est le nom de ce Warrior?");
                    name = inputParser.getString();
                    new Warrior(name,equipe);
                    break;
                case 2:
                    inputParser.printMessage("Quel est le nom de ce Hunter?");
                    name = inputParser.getString();
                    new Hunter(name,equipe);
                    break;
                case 3:
                    inputParser.printMessage("Quel est le nom de ce Healer?");
                    name = inputParser.getString();
                    new Healer(name,equipe);
                    break;
                case 4:
                    inputParser.printMessage("Quel est le nom de ce Mage?");
                    name = inputParser.getString();
                    new Mage(name,equipe);
                    break;
            }

            if (choice!=2){
                ArrayList<Weapon> allWeapon = getAllWeapon(equipe.getHero(i));
                inputParser.printMessage("Choose your first weapon:");
                inputParser.displayAllWeapon(allWeapon);
                int choice1 = inputParser.getInt();
                equipe.getHero(i).addArmory(allWeapon.get(choice1-1));
                inputParser.printMessage("Choose your second weapon:");
                inputParser.displayAllWeapon(allWeapon);
                int choice2 = inputParser.getInt();
                equipe.getHero(i).addArmory(allWeapon.get(choice2-1));

            }




        }

        return equipe;
    }



    static ArrayList<Weapon> getAllWeapon(Combatant c){
        ArrayList<Weapon> allWeapon = new ArrayList<>();

        Stick stick = new Stick();
        Sword sword = new Sword();
        Axe axe = new Axe();
        HealingStick healingStick = new HealingStick();
        Sceptre sceptre = new Sceptre();
        Hammer hammer = new Hammer();
        Giant_Sword giant_sword = new Giant_Sword();

        if(c instanceof Hero){
            allWeapon.add(sword);
            allWeapon.add(axe);
            allWeapon.add(healingStick);
            allWeapon.add(sceptre);
            allWeapon.add(stick);
        }
        else if(c instanceof Gobelin){
            allWeapon.add(sword);
            allWeapon.add(stick);
        }
        else if(c instanceof Witch){
            allWeapon.add(sceptre);
            allWeapon.add(healingStick);
        }
        else if(c instanceof Giant){
            allWeapon.add(hammer);
        }
        else if (c instanceof Boss) {
            allWeapon.add(giant_sword);
        }
        return allWeapon;

    }

}

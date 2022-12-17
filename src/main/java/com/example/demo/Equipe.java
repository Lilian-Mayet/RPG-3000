package com.example.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Equipe {

    ArrayList<Hero> equipe = new ArrayList<>();
    boolean dead = false;





    public int getSize(){return this.equipe.size();}

    public void addHero(Hero hero){
        this.equipe.add(hero);
    }

    public Hero getHero(int index){
        return this.equipe.get(index);
    }

    public boolean isDead() {
        return dead;
    }


    public void removeHero(Hero hero){
        this.equipe.remove(hero);
        if(this.equipe.size()==0){
            this.dead = true;
        }
    }

    public void printHero(){
        for (int i = 0 ;i<this.equipe.size();i++){
            System.out.print(i+1 +") " + this.equipe.get(i).getName() + " : " +  this.equipe.get(i).getHealthPoints() + "hp, ");
            System.out.println(this.equipe.get(i).getMana() + " mana");
        }


    }



    public void upgrade(){
        Scanner scan = new Scanner(System.in);

       for (int i=0;i<this.getSize();i++){


           if (this.getHero(i) instanceof Warrior){
               System.out.println(this.getHero(i).getName()+", a "+this.getHero(i).getClass()+"," +
                       " have "+this.getHero(i).getHealthPoints()+"hp" );
               System.out.println("Do you want to heal him(1) or boost his damage(2)?");
               int choice = scan.nextInt();
               switch (choice){
                   case 1:
                        this.getHero(i).updateHP(50);
                   case 2:
                        ((Warrior) this.getHero(i)).updateDamageBoost(1.2);
               }
           }

           else if (this.getHero(i) instanceof Healer){
               System.out.println(this.getHero(i).getName()+", a "+this.getHero(i).getClass()+"," +
                       " have "+this.getHero(i).getHealthPoints()+"hp" );
               System.out.println("Do you want to heal him(1) or boost his healing(2)?");
               int choice = scan.nextInt();
               switch (choice){
                   case 1:
                       this.getHero(i).updateHP(50);
                   case 2:
                       ((Healer) this.getHero(i)).updateHealBoost(1.2);
               }
           }

           else if (this.getHero(i) instanceof Hunter){
               System.out.println(this.getHero(i).getName()+", a "+this.getHero(i).getClass()+"," +
                       " have "+this.getHero(i).getHealthPoints()+"hp" );
               System.out.println("Do you want to heal him(1) or get more arrow(2)?");
               int choice = scan.nextInt();
               switch (choice){
                   case 1:
                       this.getHero(i).updateHP(50);
                   case 2:
                       ((Hunter) this.getHero(i)).updateArrow(5);
               }
           }

           else if (this.getHero(i) instanceof Mage){
               System.out.println(this.getHero(i).getName()+", a "+this.getHero(i).getClass()+"," +
                       " have "+this.getHero(i).getHealthPoints()+"hp" );
               System.out.println("Do you want to heal him(1) or boost his spell(2)?");
               int choice = scan.nextInt();
               switch (choice){
                   case 1:
                       this.getHero(i).updateHP(50);
                   case 2:
                       ((Mage) this.getHero(i)).updateSpellBoost(1.2);
               }
           }

       }




    }


}

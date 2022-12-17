package com.example.demo;

import java.util.ArrayList;

public class Horde {



        ArrayList<Enemy> horde = new ArrayList<>();
        boolean dead = false;





        public int getSize(){return this.horde.size();}

        public void addEnemy(Enemy enemy){
            this.horde.add(enemy);
        }

        public Enemy getEnemy(int index){
            return this.horde.get(index);
        }

        public boolean isDead() {
            return dead;
        }


        public void removeEnemy(Enemy enemy){
            this.horde.remove(enemy);
            if(this.horde.size()==0){
                this.dead = true;
            }
        }

        public void printEnemy(){
            for (int i = 0 ;i<this.horde.size();i++){
                System.out.print(i+1 +") " + this.horde.get(i).getName() + " : " +  this.horde.get(i).getHealthPoints() + "hp, ");
                System.out.println(this.horde.get(i).getMana() + " mana");
            }


        }
    }



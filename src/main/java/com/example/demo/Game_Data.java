package com.example.demo;

public class Game_Data {

    static Equipe equipe;
    static Horde horde;
    static int level;

    private static int nbHero;

    private static int nbHerotemp;

    public  Game_Data(){
        level = 1;
        equipe = new Equipe();
        horde = new Horde();
    }

    public static void setNbHero(int nb){
        nbHero = nb;

    }

    public static int getNbHero(){
        return nbHero;
    }

    public static void setNbHeroTemp(int nb){
        nbHerotemp = nb;

    }

    public static int getNbHeroTemp(){
        return nbHerotemp;
    }


    public static int getLevel(){return level;}

    public static void nextLevel(){level++;}


    public static Equipe getEquipe(){return equipe;}

    public static Horde getHorde(){return horde;}

    public static void setHorde(Horde inputhorde){horde = inputhorde;}


}

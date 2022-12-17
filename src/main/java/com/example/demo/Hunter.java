package com.example.demo;

public class Hunter extends Hero{

    private int arrowLeft;

    public Hunter(String name,Equipe equipe) {
        super(name);
        this.arrowLeft = 10;
        Bow bow = new Bow();
        Fist fist = new Fist();
        this.addArmory(bow);
        this.addArmory(fist);
        this.setHealthPoints(150);
        this.setStrenght(26);
        this.setDefense(20);
        this.setMana(0);
        equipe.addHero(this);
        setClassType("Hunter");


    }
    @Override
    public void action(Combatant target){
        if(this.arrowLeft>0){
            int damage = this.getStrenght();
            int damageDealed = (int)Math.round(damage*(100-target.getDefense())/100);
            target.updateHP(-damageDealed);
            this.updateArrow(-1);
        }
        else {
            target.updateHP(-1);
        }
    }


    @Override
    public int getArrowLeft(){return this.arrowLeft;}

    public void updateArrow(int delta){this.arrowLeft += delta; }

    @Override
    public void interact(Combatant target, InputParser inputParser){
        inputParser.printMessage("You are about to interact with " + target.getName() + " (HP = " + target.getHealthPoints());
        inputParser.printMessage(" , defense = " + target.getDefense() + " )");

        if(this.getArrowLeft()>0){
            this.updateArrow(-1);
            inputParser.printMessage(this.getName() + " used 1 arrow(" + this.getArrowLeft() + " left).");
            this.getArmory().get(0).interaction(this,target,inputParser);
        }
        else {
            inputParser.printMessage("You don't have any arrow left, you use your first.");
            this.getArmory().get(1).interaction(this,target,inputParser);
        }





    }



}

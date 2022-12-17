package com.example.demo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleParser implements InputParser {
        Scanner scan = new Scanner(System.in);

    @Override
    public int getInt() {
        int v=0;
        boolean done = false;

        while (!done) {

            try {
                v = scan.nextInt();
                done = true;
            } catch (InputMismatchException exception) {
                System.out.println("Integers only, please.");
                scan.nextLine();
                done = false;
            }

        }
         return v;

    }

    @Override
    public int getIntRanged(int min, int max) {
        int v=0;
        boolean done = false;

        while (!done) {

            try {
                v = scan.nextInt();
                done = true;
            } catch (InputMismatchException exception) {
                System.out.println("Integers only, please.");
                scan.nextLine();
                done = false;
            }

            if(done && v< min || v> max){
                done = false;
                System.out.println("Between " + min + " and " + max + " please.");
            }

        }
        return v;
    }

    @Override
    public String getString() {
        String v = scan.next();
        return v;
    }
    @Override
    public String getStringRanged(int min,int max) {
        String v = "";
        boolean done =false;
        while (!done){

        v = scan.nextLine();
        if (v.length()<min || v.length()>max){
            System.out.println("between " + min+" and "+max+" characters please.");
        }
        else {
            done = true;
        }


        }
        return v;
    }

    @Override
    public void printMessage(String message){
        System.out.println(message);
    }

    @Override
    public void displayAllWeapon(ArrayList<Weapon> allWeapon) {
        for (int y = 0; y < allWeapon.size(); y++) {
            System.out.println((y+1)+")"+allWeapon.get(y).name);
        }
    }
}

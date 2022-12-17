package com.example.demo;

import java.util.ArrayList;

interface InputParser {


    default  int getInt() {
        return 0;
    }

    default int getIntRanged(int min,int max){
        return min;
    }


    default String getString(){
        return "";
    }
    default String getStringRanged(int min,int max){
        return "";
    }
    default void printMessage(String message){}


    default void displayAllWeapon(ArrayList<Weapon> allWeapon){}
}

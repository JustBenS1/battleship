package com.battleship.util;

public class Display {
    public void printMessage(String message){
        System.out.print(message);
    }

    public void printMessageLine(String message){
        System.out.println(message);
    }

    public void printMenuOptions(String[] menuOptions){
        for(String menuOption: menuOptions){
            printMessageLine(menuOption);
        }
        printMessage("Please choose an option (number required) : ");
    }

}

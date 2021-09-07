package com.battleship.util;

public class Display {
    private static Display single_instance = null;

    private Display() {}

    public static Display getInstance() {
        if (single_instance == null)
            single_instance = new Display();

        return single_instance;
    }

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

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

package com.battleship.util;


import com.battleship.Board;
import com.battleship.Square;

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

    public void printMenuOptions(String[] menuOptions, String message){
        for(String menuOption: menuOptions){
            printMessageLine(menuOption);
        }
        printMessage(message);
    }

    public void clear(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void printBoard(Board board){
        System.out.println();
        Square[][] ocean = board.getOcean();
        String headerLine = "  ";
        for (int i = 1; i <= ocean.length; i++) {
            headerLine += i % 10 + " ";//💥("collision")🌊("wave")☁("cloud")  🟦("blue square"), emoji icon possibilities
        }
        System.out.println(headerLine);
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print(Character.toChars(65 + i));
            System.out.print(" ");
            for (int j = 0; j < board.getSize(); j++) {
                System.out.print(ocean[i][j].getIcon() + " ");
            }
            System.out.print(Character.toChars(65 + i));
            System.out.print(" ");
            System.out.println();
        }
        System.out.println(headerLine);
    }
}

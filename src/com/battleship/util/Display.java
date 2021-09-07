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

    public void printMenuOptions(String[] menuOptions, String message){
        for(String menuOption: menuOptions){
            printMessageLine(menuOption);
        }
        printMessage(message);
    }

    public void ClearConsole(){
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
}

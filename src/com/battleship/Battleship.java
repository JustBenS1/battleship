package com.battleship;

import com.battleship.util.*;

public class Battleship {
    public static Display display = new Display();
    public static Input input = new Input();
    private static final String[] mainMenuOptions = {"New Game : 1", "High Scores : 2", "Exit : 0"};
    private static final String[] restartMenuOptions = {"New Game : 1", "Exit : 0"};

    public static int menuBuilder(String[] menuOptions){
        String userInput = "";
        while(!input.checkMainInput(userInput, menuOptions.length)){
            display.printMenuOptions(menuOptions);
            userInput = input.getInput();
            display.printMessageLine("");
        }
        return Integer.parseInt(userInput);
    }

    public static void main(String[] args) {
        while(true){
            int mainMenuOption = menuBuilder(mainMenuOptions);
            if (mainMenuOption == 0){
                break;
            }
            //game()//while
            if(menuBuilder(restartMenuOptions) == 0){
                break;
            }
        }
        display.printMessageLine("Good bye");
    }
}

package com.battleship;

import com.battleship.util.*;

public class Battleship {
    public static Display display = Display.getInstance();
    public static Input input = Input.getInstance();
    private static final String[] mainMenuOptions = {"New Game : 1", "High Scores : 2", "Exit : 0"};
    private static final String[] restartMenuOptions = {"New Game : 1", "Exit : 0"};
    private static final String[] getBoardSizeText = {"Please provide board size (between 5-25)", "Back (0)"};
    private static final String inputNumberPrompt = "Please choose an option (number required) : ";

    private static final int minSize = 5;
    private static final int maxSize = 25;
    private static boolean endgame = false;
    private static int boardSize;


    public static int menuBuilder(String[] menuOptions){
        String userInput = "";
        while(!input.checkMainInput(userInput, menuOptions.length)){
            display.printMenuOptions(menuOptions, inputNumberPrompt);
            userInput = input.getInput();
            display.printMessageLine("");
            display.clear();
        }
        return Integer.parseInt(userInput);
    }

    public static int sizeBuilder(){
        String userInput;
        int validInput = -1;
        while(validInput == -1){
            display.printMenuOptions(getBoardSizeText, inputNumberPrompt);
            userInput = input.getInput();
            validInput = input.checkSizeInput(userInput, minSize, maxSize);
            display.clear();
        }
        return validInput;
    }

    public static void menuSettings(){
        int mainMenuOption = menuBuilder(mainMenuOptions);
        display.clear();
        if (mainMenuOption == 0){
            endgame = true;
        }else if (mainMenuOption == 1){//newgame
            int sizeOption = sizeBuilder();
            if(sizeOption == 0){
                menuSettings();//mmainmenu
            }else{
                boardSize = sizeOption;
            }
        }else{
            //scoreboard implement needed
            menuSettings();
        }
    }


    public static void main(String[] args) {

        display.clear();
        while(!endgame){
            menuSettings();
            display.clear();
            //game()//while
            Game game = new Game(boardSize);
            display.clear();
            if(menuBuilder(restartMenuOptions) == 0){
                endgame = true;
            }
        }
        display.clear();
        display.printMessageLine("Good bye");
    }
}

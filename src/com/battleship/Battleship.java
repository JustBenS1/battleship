//TODO print hidden enemy board (that we shoot at) shoot , print same board again (input/timer) , print next board
//TODO Sink feature
//TODO same target (hit/miss) shoot again - bad (forever while loop) - BENCE
//TODO endgames
//TODO print hidden enemy board (that we shoot at) shoot , print same board again (input/timer) , print next board
package com.battleship;

import com.battleship.util.*;

public class Battleship {
    public static Display display = Display.getInstance();
    public static Input input = Input.getInstance();
    private static final String[] mainMenuOptions = {"New Game : 1", "High Scores : 2", "Exit : 0"};
    private static final String[] restartMenuOptions = {"Main Menu : 1", "Exit : 0"};
    private static final String[] getBoardSizeText = {"Please provide board size (between 10-25)", "Back (0)"};
    private static final String inputNumberPrompt = "Please choose an option (number required) : ";

    private static final int minSize = 10;
    private static final int maxSize = 25;
    private static Endgame endgame = Endgame.getInstance();

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

    private static int sizeBuilder(){
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

    private static void menuSettings(){
        int mainMenuOption = menuBuilder(mainMenuOptions);
        display.clear();
        if (mainMenuOption == 0){
            endgame.setEndgame(true);
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
        while(!endgame.getIsEndgame()){
            menuSettings();
            display.clear();
            //game()//while
            Game game = new Game(boardSize);
            game.run();
            display.clear();
            if(menuBuilder(restartMenuOptions) == 0){
                endgame.setEndgame(true);
            }else{
                endgame.setEndMatch(false);
            }
        }
        display.clear();
        display.printMessageLine("Good bye");
    }
}

package com.battleship;

import com.battleship.util.*;

public class Battleship {
    public static Display display = new Display();
    public static Input input = new Input();
    private static final String[] mainMenuOptions = {"New Game : 1", "High Scores : 2", "Exit : 0"};
    private static final String[] restartMenuOptions = {"New Game : 1", "Exit : 0"};
    private static final String[] getBoardSizeText = {"Please provide board size (between 5-25) : ", "Back (0) : "};
    private static final int minSize = 5;
    private static final int maxSize = 25;
    private static boolean endgame = false;


    public static int menuBuilder(String[] menuOptions){
        String userInput = "";
        while(!input.checkMainInput(userInput, menuOptions.length)){
            display.printMenuOptions(menuOptions);
            userInput = input.getInput();
            display.printMessageLine("");
        }
        return Integer.parseInt(userInput);
    }

    public static int sizeBuilder(){
        String userInput = "";
        int validInput = -1;
        while(validInput == -1){
            display.printMenuOptions(getBoardSizeText);
            userInput = input.getInput();
            validInput = input.checkSizeInput(userInput, minSize, maxSize);
        }
        return validInput;
    }

    public static void menuSettings(){
        int size = -1;

        int mainMenuOption = menuBuilder(mainMenuOptions);
        if (mainMenuOption == 0){
            endgame = true;
        }else if (mainMenuOption == 1){
            int sizeOption = sizeBuilder();
            if(sizeOption == 0){
                menuSettings();
            }else{
                size = sizeOption;
            }
            Game game = new Game(size);
        }
        //game
    }

    public static void main(String[] args) {
        Board board = new Board(10);

        String icon = SquareStatus.EMPTY.getCharacter();
        System.out.println(icon);
        while(!endgame){

            //game()//while
            if(menuBuilder(restartMenuOptions) == 0){
                break;
            }
        }
        display.printMessageLine("Good bye");
    }
}

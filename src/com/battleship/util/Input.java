package com.battleship.util;

import com.battleship.Directions;

import java.util.Scanner;

public class Input {
    private static Input single_instance = null;
    private Endgame endgame = Endgame.getInstance();
    private Input() {}

    public static Input getInstance() {
        if (single_instance == null)
            single_instance = new Input();

        return single_instance;
    }

    private Scanner scanner = new Scanner(System.in);


    public String getInput(){
        return String.valueOf(scanner.nextLine());
    }

    public boolean checkMainInput(String userInput, int optionCount){
        try {
            int inputValue = Integer.parseInt(userInput);
            return inputValue < optionCount && inputValue > -1;
        }catch(Exception e){
            return false;
        }
    }

    public int checkSizeInput(String userInput, int minSize, int maxSize){
        try {
            int inputValue = Integer.parseInt(userInput);
            if (inputValue == 0 || (inputValue >= minSize && inputValue <= maxSize)){
                return inputValue;
            }else{
                return -1;
            }
        }catch(Exception e){
            return -1;
        }
    }

    public int[] getValidCoordinate(){
        getInput();

        return new int[0];
    }

    public String getPlayerName() {
        return "";
    }

    public boolean isStringOnlySpace (String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean isCoordinateOnBoard (String input, int boardSize) {

        if (input.length() < 2) {
            return false;
        }
        if (inputIsQuit(input)){
            endgame.setEndMatch(true);
            return true;
        }
        String firstChar = input.substring(0, 1).toLowerCase();
        String secondNum = input.substring(1);
        int firstLetter = firstChar.charAt(0) - 97;
        if (firstLetter < 0 || firstLetter >= boardSize ) {
            return false;
        }
        try {
            int numberInput = Integer.parseInt(secondNum);
            if (numberInput < 1 || numberInput > boardSize) {
                return false;
            }
        }  catch (Exception e) {
            return false;
        }
        return true;
    }

    public Coordinates convertToCoordinates(String baseInput){
        String firstChar = baseInput.substring(0, 1).toLowerCase();
        String secondNum = baseInput.substring(1);
        int row = firstChar.charAt(0) - 97;
        int col = Integer.parseInt(secondNum) - 1;
        return new Coordinates(row, col);
    }

    public boolean isValidDirection(String input) {
        if(input.equalsIgnoreCase("quit")){
            endgame.setEndMatch(true);
            return true;
        }
        for (Directions direction :Directions.values()) {
            if (direction.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public boolean inputIsQuit(String userInput){
        if (userInput.equalsIgnoreCase("quit")){
            endgame.setEndMatch(true);
            return true;
        }
        return false;
    }

}

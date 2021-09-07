package com.battleship.util;

import java.util.Scanner;

public class Input {
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

}

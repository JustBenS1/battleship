package com.battleship;

import com.battleship.util.Display;
import com.battleship.util.Input;

import java.util.ArrayList;

public class Player {
    private Display display = Display.getInstance();
    private Input input = Input.getInstance();
    private final ArrayList<Ship> fleet;
    private int currentHP;
    private final String playerName;
    private final String[] choosePlayerNameText = {"Please, provide your name : "};

    public Player(int maxHP, ArrayList<Ship> fleet, int size) {
        this.currentHP = maxHP;
        this.fleet = fleet;
        Board ocean = new Board(size);
        playerName = getPlayerName();
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public String getPlayerName() {
        String userInput;

        display.printMenuOptions(choosePlayerNameText);
        userInput = input.getInput();
        if (input.isStringOnlySpace(userInput) || userInput.equals("")) {
            return "Dummy";
        }
        return userInput;
    }
}

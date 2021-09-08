package com.battleship;

import com.battleship.util.Coordinates;
import com.battleship.util.Display;
import com.battleship.util.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private Display display = Display.getInstance();
    private Input input = Input.getInstance();
    private ArrayList<Ship> fleet;
    private int currentHP;
    private final String playerName;
    private final String[] choosePlayerNameText = {"Please, provide your name : "};
    private Board ocean;

    public Player(int maxHP, ArrayList<Ship> fleet, int size) {
        this.currentHP = maxHP;
        this.fleet = fleet;
        ocean = new Board(size);
        playerName = getPlayerName();
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public Board getOcean() {
        return ocean;
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

    public void setFleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
    }

    public void setOcean(Board ocean) {
        this.ocean = ocean;
    }

    public String getPlayerName() {
        display.printMenuOptions(choosePlayerNameText,"");
        String userInput = input.getInput();
        if (input.isStringOnlySpace(userInput) || userInput.equals("")) {
            return "Dummy";
        }
        return userInput;
    }

    public Ship getShipByCoordinate(Coordinates coordinate) {

        for (Ship ship : fleet) {
            ArrayList<Square> shipSquares = ship.getSquares();
            for (Square square : shipSquares) {
                if (square.getCoordinates() == coordinate) {
                    return ship;
                }
            }
        }
        return new Ship(0);
    }

    public boolean isShipBarelyAlive (Ship ship) {
        int hpCounter = 0;
        for (Square square: ship.getSquares()) {
            if (square.getStatus().name().equals("SHIP")) {
                hpCounter++;
                if (hpCounter > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}

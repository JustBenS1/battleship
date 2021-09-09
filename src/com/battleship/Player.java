package com.battleship;

import com.battleship.util.Coordinates;
import com.battleship.util.Display;
import com.battleship.util.Input;

import java.util.ArrayList;

public class Player {
    private Display display = Display.getInstance();
    private Input input = Input.getInstance();
    private ArrayList<Ship> fleet;
    private int currentHP;
    private final String playerName;
    private final String[] choosePlayerNameText = {"Player"," , please provide pl name : "};
    private Board ocean;

    public Player(ArrayList<Ship> fleet, int maxHP,  int size, int nThPlayer) {
        this.currentHP = maxHP;
        this.fleet = fleet;
        ocean = new Board(size);
        playerName = getSetPlayerName(nThPlayer);
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

    public boolean isPlayerAlive() {
        return currentHP > 0;
    }

    public void setFleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
    }

    public void setOcean(Board ocean) {
        this.ocean = ocean;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getSetPlayerName(int nthPlayer) {
        display.printMessage(choosePlayerNameText[0]+nthPlayer+choosePlayerNameText[1]);
        String userInput = input.getInput();
        display.clear();
        if (input.isStringOnlySpace(userInput) || userInput.equals("")) {
            return "Dummy";
        }
        return userInput;
    }

    public Ship getShipByCoordinate(Coordinates coordinate) {
        Ship shipOut = new Ship(0);
        for (Ship ship : fleet) {
            for (Square square : ship.getSquares()) {
                if (square.getCoordinates().getX() == coordinate.getX() &&
                        square.getCoordinates().getY() == coordinate.getY()) {
                    shipOut = ship;
                    break;
                }
            }
            if (!shipOut.getSquares().isEmpty()){
                break;
            }
        }
        return shipOut;
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

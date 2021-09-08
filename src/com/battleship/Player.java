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
    private final String[] choosePlayerNameText = {"Please, provide your name : "};
    private Board ocean;

    public Player(int maxHP, ArrayList<Ship> fleet, int size) {
        this.currentHP = maxHP;
        this.fleet = fleet;
        ocean = new Board(size);
        playerName = getSetPlayerName();
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
        return playerName;
    }

    public String getSetPlayerName() {
        display.printMenuOptions(choosePlayerNameText,"");
        String userInput = input.getInput();
        if (input.isStringOnlySpace(userInput) || userInput.equals("")) {
            return "Dummy";
        }
        return userInput;
    }

    public Ship getShipByCoordinate(Coordinates coordinate) {
        Ship shipOut = new Ship(0);
        for (Ship ship : fleet) {
            System.out.println(fleet.size()+" fleetsize");
            for (Square square : ship.getSquares()) {
                System.out.println(square.getCoordinates().getX()+"X squarex");
                System.out.println(square.getCoordinates().getY()+"Y squarey");
                System.out.println(coordinate.getX()+"X  : coordx");
                System.out.println(coordinate.getY()+"Y  : coordy");
                if (square.getCoordinates().getX() == coordinate.getX() &&
                        square.getCoordinates().getY() == coordinate.getY()) {
                    System.out.println(ship.getSquares().size() + " ship squares size");
                    shipOut = ship;
                    break;
                }
            }
            if (!shipOut.getSquares().isEmpty()){
                break;
            }
        }
        System.out.println(shipOut.getSquares().size()+"shipout before return");
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

package com.battleship;

import com.battleship.util.*;

import java.util.ArrayList;

public class BoardFactory {
    public Display display = Display.getInstance();
    public Input input = Input.getInstance();
    private ArrayList<Ship> fleet;
    private Board board;
    private Coordinates startCoordinate;
    private Coordinates endCoordinate;
    private int shipSize;
    private String direction;


    public BoardFactory(Board board, ArrayList<Ship> fleet) {
        this.fleet = fleet;
        this.board = board;
    }

    public void placementValidation() {
        boolean isShipOnBoard = false;
        boolean areSurroundingsValid = false;
        while (!isShipOnBoard || !areSurroundingsValid) {

            boolean isValidPlacement = false;
            boolean isValidDirection = false;

            while (!isValidPlacement) {
                isValidPlacement = getValidPlacement();
                if (!isValidPlacement) {
                    display.printMessageLine("Give a valid Coordinate!");
                }
            }
            while (!isValidDirection) {
                isValidDirection = getValidDirection();
                if (! isValidDirection) {
                    display.printMessageLine("Type a valid direction!");
                }
            }
            isShipOnBoard = checkShipInBoard(direction);
            if (! isShipOnBoard) {
                display.printMessageLine("The ship would go off the board!");
                continue;
            }
            areSurroundingsValid = checkSurroundings();
        }
    }

    public boolean getValidPlacement() {
        display.printMessage("Please give a start coordinate (eg.: A1) : ");
        String userInput = input.getInput();
        display.clear();
        if (!input.isCoordinateOnBoard(userInput, board.getSize())) {
            return false;
        }
        Coordinates coordinate = input.convertToCoordinates(userInput);
        if (!board.isSquareEmpty(coordinate) || !board.areNeighboursEmpty(coordinate)) {
            return false;
        }
        //direction
        startCoordinate = coordinate;
        display.clear();
        return true;
    }

    public boolean getValidDirection() {
        display.printMessage("Please give a direction (North, East, South, West) : ");
        String direction = input.getInput().toUpperCase();
        display.clear();
        if (!Input.getInstance().isValidDirection(direction)) {
            return false;
        }
        this.direction = direction;
        display.clear();
        return true;
    }

    public boolean checkShipInBoard(String direction) {
        int newX = startCoordinate.getX();
        int newY = startCoordinate.getY();
        Coordinates checkCoordinate;
        switch (direction) {
            case "NORTH" -> {
                checkCoordinate = new Coordinates(newX, newY - shipSize);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "WEST" -> {
                checkCoordinate = new Coordinates(newX - shipSize, newY);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "SOUTH" -> {
                checkCoordinate = new Coordinates(newX, newY + shipSize);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = checkCoordinate;
            }
            case "EAST" -> {
                checkCoordinate = new Coordinates(newX + shipSize, newY);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = checkCoordinate;
            }
        }
        display.clear();
        return true;
    }

    public boolean checkSurroundings() {
        Coordinates coordinate;
        if (startCoordinate.getX() == endCoordinate.getX()) {
            for (int i = startCoordinate.getY(); i <= endCoordinate.getY(); i++) {
                coordinate = new Coordinates(startCoordinate.getX(), startCoordinate.getY() + i);
                if (!board.areNeighboursEmpty(coordinate)) {
                    return false;
                }
            }
        } else {
            for (int i = startCoordinate.getX(); i <= endCoordinate.getX(); i++) {
                coordinate = new Coordinates(startCoordinate.getX() + i, startCoordinate.getY());
                if (!board.areNeighboursEmpty(coordinate)) {
                    return false;
                }
            }
        }
        // Placement ();
        display.clear();
        return true;
    }

    public boolean isEndOnBoard(Coordinates coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < board.getSize() &&
                coordinate.getY() >= 0 && coordinate.getY() < board.getSize();
    }

    public void run() {
        for (Ship ship : fleet) {
            shipSize = ship.getSquares().size();
            placementValidation();

        }
    }


}

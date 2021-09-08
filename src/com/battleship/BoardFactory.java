package com.battleship;

import com.battleship.util.*;

import java.util.ArrayList;

public class BoardFactory {
    private Player player;
    public Display display = Display.getInstance();
    public Input input = Input.getInstance();
    private ArrayList<Ship> fleet;
    private Board board;
    private Coordinates startCoordinate;
    private Coordinates endCoordinate;
    private int shipSize;
    private String direction;
    private Endgame endgame = Endgame.getInstance();


    public BoardFactory(Player player) {
        this.player = player;
        this.fleet = this.player.getFleet();
        this.board = this.player.getOcean();
    }

    public void placementValidation(Ship ship) {
        boolean isShipOnBoard = false;
        boolean areSurroundingsValid = false;
        while ((!isShipOnBoard || !areSurroundingsValid) && !endgame.getIsEndMatch()) {

            boolean isValidPlacement = false;
            boolean isValidDirection = false;

            while (!isValidPlacement && !endgame.getIsEndMatch()) {
                isValidPlacement = getValidPlacement();
                if (!isValidPlacement) {
                    display.printMessageLine("Give a valid Coordinate!");
                }
            }
            if (endgame.getIsEndMatch()){
                continue;
            }

            while (!isValidDirection && !endgame.getIsEndMatch()) {
                isValidDirection = getValidDirection();
                if (!isValidDirection) {
                    display.printMessageLine("Type a valid direction!");
                }
            }
            if (endgame.getIsEndMatch()){
                continue;
            }

            isShipOnBoard = checkShipInBoard(direction);
            if (! isShipOnBoard) {
                display.printMessageLine("The ship would go off the board!");
                continue;
            }
            areSurroundingsValid = checkSurroundings();
        }
        if (!endgame.getIsEndMatch()){
            placeShipOnBoard(ship);
        }

    }

    public boolean getValidPlacement() {
        display.printMessage("Please give a start coordinate (eg.: A1) : ");
        String userInput = input.getInput();
        if(input.inputIsQuit(userInput)){
           return false;
        }
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
        input.inputIsQuit(direction);
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
            case "WEST" -> {
                checkCoordinate = new Coordinates(newX, newY - shipSize + 1);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "NORTH" -> {
                checkCoordinate = new Coordinates(newX - shipSize + 1, newY);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "EAST" -> {
                checkCoordinate = new Coordinates(newX, newY + shipSize - 1);
                if (!isEndOnBoard(checkCoordinate)) {
                    return false;
                }
                this.endCoordinate = checkCoordinate;
            }
            case "SOUTH" -> {
                checkCoordinate = new Coordinates(newX + shipSize - 1, newY);
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
        display.clear();
        return true;
    }

    public boolean isEndOnBoard(Coordinates coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < board.getSize() &&
                coordinate.getY() >= 0 && coordinate.getY() < board.getSize();
    }

    public void placeShipOnBoard(Ship ship){
        Square newSquare;
        int baseX = startCoordinate.getX();
        int baseY = startCoordinate.getY();
        int endX = endCoordinate.getX();
        int endY = endCoordinate.getY();
        Coordinates coordinate = new Coordinates(baseX, baseY);
        ArrayList<Square> newShipSquares = new ArrayList<>();
        if (baseX == endX) {
            for (int i = 0; i <= endY-baseY; i++) {
                coordinate.setY(baseY + i);
                newSquare = new Square(coordinate);
                board.setOceanField(coordinate, newSquare);
                newShipSquares.add(newSquare);
            }
        } else {
            for (int i = 0; i <= endX-baseX; i++) {
                coordinate = new Coordinates(baseX + i, baseY);
                coordinate.setX(baseX + i);
                newSquare = new Square(coordinate);
                board.setOceanField(coordinate, newSquare);
                newShipSquares.add(newSquare);
            }
        }
        ship.setSquares(newShipSquares);//return ?
    }

    public void run() {
        for (Ship ship : fleet) {
            shipSize = ship.getSquares().size();
            System.out.println(shipSize);
            placementValidation(ship);
            if (endgame.getIsEndMatch()){
                break;
            }
            display.clear();
            display.printBoard(board, false);

        }
        player.setOcean(board);
        player.setFleet(fleet);
        // return ?
    }
}

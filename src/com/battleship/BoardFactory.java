package com.battleship;

import com.battleship.util.*;

import java.util.ArrayList;

public class BoardFactory {
    public Display display= Display.getInstance();
    public Input input= Input.getInstance();
    private ArrayList<Ship> fleet;
    private Board board;
    private Coordinates startCoordinate;
    private Coordinates endCoordinate;
    private int shipSize;


    public BoardFactory(Board board, ArrayList<Ship> fleet) {
        this.fleet = fleet;
        this.board = board;
    }

    public void getValidPlacement(){
        display.printMessage("Please give a start coordinate (eg.: A1) : ");
        String userInput = input.getInput();
        display.clear();
        if(!input.isCoordinateOnBoard(userInput, board.getSize())){
            display.clear();
            getValidPlacement();
        }
        Coordinates coordinate = input.convertToCoordinates(userInput);
        if (!board.isSquareEmpty(coordinate) || !board.areNeighboursEmpty(coordinate)){
            display.clear();
            getValidPlacement();
        }
        //direction
        startCoordinate = coordinate;
        getValidDirection();
    }

    public void getValidDirection() {
        display.printMessage("Please give a direction (North, East, South, West) : ");
        String direction = input.getInput().toUpperCase();
        display.clear();
        if (!Input.getInstance().isValidDirection(direction)) {
            getValidPlacement();
        }
        // Other end In Board
        checkShipInBoard(direction);
    }

    public void checkShipInBoard(String direction) {
        int newX = startCoordinate.getX();
        int newY = startCoordinate.getY();
        Coordinates checkCoordinate;
        switch (direction) {
            case "NORTH" -> {
                checkCoordinate = new Coordinates(newX, newY - shipSize);
                if(!isEndOnBoard(checkCoordinate)) {
                    getValidPlacement();
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "WEST" -> {
                checkCoordinate = new Coordinates(newX - shipSize, newY);
                if(!isEndOnBoard(checkCoordinate)) {
                    getValidPlacement();
                }
                this.endCoordinate = this.startCoordinate;
                this.startCoordinate = checkCoordinate;
            }
            case "SOUTH" -> {
                checkCoordinate = new Coordinates(newX, newY + shipSize);
                if(!isEndOnBoard(checkCoordinate)) {
                    getValidPlacement();
                }
                this.endCoordinate = checkCoordinate;
            }
            case "EAST" -> {
                checkCoordinate = new Coordinates(newX + shipSize, newY);
                if(!isEndOnBoard(checkCoordinate)) {
                    getValidPlacement();
                }
                this.endCoordinate = checkCoordinate;
            }
        }
        checkSurroundings();
    }

    public void checkSurroundings() {
        Coordinates coordinate;
        if (startCoordinate.getX() == endCoordinate.getX()) {
            for (int i = startCoordinate.getY(); i <= endCoordinate.getY() ; i++) {
                coordinate = new Coordinates(startCoordinate.getX(), startCoordinate.getY() + i);
                if (! board.areNeighboursEmpty(coordinate)) {
                    getValidPlacement();
                }
            }
        } else {
            for (int i = startCoordinate.getX(); i <= endCoordinate.getX() ; i++) {
                coordinate = new Coordinates(startCoordinate.getX() + i, startCoordinate.getY());
                if (! board.areNeighboursEmpty(coordinate)) {
                    getValidPlacement();
                }
            }
        }
        // Placement ();
    }

    public boolean isEndOnBoard(Coordinates coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < board.getSize() &&
                coordinate.getY() >= 0 && coordinate.getY() < board.getSize();
    }

    public void run() {
        for (Ship ship : fleet) {
            shipSize = ship.getSquares().size();
            getValidPlacement();

        }
    }


}

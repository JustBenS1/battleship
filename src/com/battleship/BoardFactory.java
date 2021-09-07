package com.battleship;

import com.battleship.util.*;


public class BoardFactory {
    public Display display= Display.getInstance();
    public Input input= Input.getInstance();

    public boolean isShipPlaceable(Board board){
        String userInput = input.getInput();
        if(!input.isCoordinateOnBoard(userInput, board.getSize())){
            display.clear();
            isShipPlaceable(board);
        }
        Coordinates coordinate = input.convertToCoordinates(userInput);
        if (!board.isSquareEmpty(coordinate) || !board.areNeighboursEmpty(coordinate)){
            display.clear();
            isShipPlaceable(board);
        }
        //direction

        return false;
    }
}

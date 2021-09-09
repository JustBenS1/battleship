package com.battleship;

import com.battleship.Square;
import com.battleship.util.Coordinates;

import java.util.ArrayList;

public class Ship {
    private ArrayList<Square> squares = new ArrayList<>();

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public void setShipSquareByCoordinates(Coordinates coordinates, SquareStatus newStatus){
        for (Square square:this.squares) {
            if (square.getCoordinates().getX() == coordinates.getX() &&
                    square.getCoordinates().getY() == coordinates.getY()){
                square.setStatus(newStatus);
                break;
            }
        }
    }

    public Ship(int size){
        for (int i = 0; i < size; i++) {
            squares.add(new Square());
        }
    }
}


package com.battleship.util;

import com.battleship.Square;

import java.util.ArrayList;

public class Ship {
    private ArrayList<Square> squares = new ArrayList<>();

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }
}


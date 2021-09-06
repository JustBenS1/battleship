package com.battleship;

public class Square {
    private int X;
    private int Y;
    private SquareStatus.statuses status;

    public Square(int x, int y) {
        X = x;
        Y = y;
        this.status = SquareStatus.statuses.EMPTY;
    }

    public String getIcon () {
        return status.getCharacter();
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public SquareStatus.statuses getStatus() {
        return status;
    }

}


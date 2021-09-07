package com.battleship;

public class Square {
    private final int X;
    private final int Y;
    private SquareStatus status;

    public Square(int x, int y) {
        X = x;
        Y = y;
        this.status = SquareStatus.EMPTY;
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

    public SquareStatus getStatus() {
        return status;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }
}


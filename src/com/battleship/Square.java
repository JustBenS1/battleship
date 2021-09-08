package com.battleship;

public class Square {
    private int X;
    private int Y;
    private SquareStatus status;

    public Square(){
        this.status = SquareStatus.EMPTY;
    }

    public Square(int x, int y) {
        X = x;
        Y = y;
        this.status = SquareStatus.EMPTY;
    }

    public String getIcon () {
        return status.getCharacter();
    }

    public void setX(int x) {
        X = x;
    }

    public int getX() {
        return X;
    }

    public void setY(int y) {
        Y = y;
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


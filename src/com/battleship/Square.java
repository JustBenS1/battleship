package com.battleship;

import com.battleship.util.Coordinates;

public class Square {
    private Coordinates coordinates;
    private SquareStatus status;

    public Square(){
        this.status = SquareStatus.EMPTY;
    }

    public Square(Coordinates coordinates){
        this.coordinates = new Coordinates(coordinates.getX(), coordinates.getY());
        this.status = SquareStatus.SHIP;
    }

    public Square(int x, int y) {
        this.coordinates = new Coordinates(x,y);
        this.status = SquareStatus.EMPTY;
    }

    public Square(Coordinates coordinates, SquareStatus status) {
        this.coordinates = new Coordinates(coordinates.getX(), coordinates.getY());
        this.status = status;
    }

    public String getHidden () {
        return status.getHiddenIcon();
    }

    public String getShown() {
        return status.getShownIcon();
    }

    public SquareStatus getStatus() {
        return status;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}


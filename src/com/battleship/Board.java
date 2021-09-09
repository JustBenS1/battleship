package com.battleship;

import com.battleship.util.Coordinates;

public class Board {
    private Square[][] ocean;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.ocean = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ocean[i][j] = new Square(i, j);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Square[][] getOcean(){
        return this.ocean;
    }

    public void setOceanSquare(Coordinates coordinates, SquareStatus newStatus){
        this.ocean[coordinates.getX()][coordinates.getY()].setStatus(newStatus);
    }
    public void setOceanSquare(Coordinates coordinates, Square newSquare) {
        this.ocean[coordinates.getX()][coordinates.getY()] = newSquare;
    }

    public Square getOceanSquare(Coordinates coordinate) {
        return this.ocean[coordinate.getX()][coordinate.getY()];
    }

    public boolean isSquareEmpty (Coordinates coordinate) {
        Square square = this.ocean[coordinate.getX()][coordinate.getY()];
        return square.getStatus().name().equals("EMPTY");
    }

    public boolean isSquareShootable (Coordinates coordinate) {
        Square square = this.ocean[coordinate.getX()][coordinate.getY()];
        return square.getStatus().name().equals("EMPTY") || square.getStatus().name().equals("SHIP");
    }

    public boolean areNeighboursEmpty(Coordinates coordinate){
        int[][] differences = new int[][] {
                {1,1},
                {-1,-1},
                {1,-1},
                {-1,1},
        };

        for (int i = 0; i < differences[i].length; i++) {
            try{
                Coordinates checkCoordinate = new Coordinates(coordinate.getX() + differences[i][0], coordinate.getY()+differences[i][1]);
                if (!isSquareEmpty(checkCoordinate)){
                    return false;
                }
            }catch(Exception e){}
        }
        return true;
    }
}

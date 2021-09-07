package com.battleship;

public class Board {
    Square[][] ocean;

    public Board(int size) {
        this.ocean = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ocean[i][j] = new Square(i, j);
            }
        }
    }

    public boolean isSquareEmpty (int row, int col) {
        return this.ocean[row][col].getStatus().name().equals("EMPTY");
    }
}

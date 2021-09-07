package com.battleship;

public enum ShipType {
    DESTROYER(2),
    CRUISER(3),
    SUBMARINE(3),
    BATTLESHIP(4),
    CARRIER(5);
    private int shipLength;

    ShipType (int L) {
        shipLength = L;
    }

    int getLength() {
        return shipLength;
    }
}
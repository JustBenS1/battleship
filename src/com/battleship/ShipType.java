package com.battleship;

public enum ShipType {
    DESTROYER(2),
    CRUISER(3),
    SUBMARINE(3),
    BATTLESHIP(4),
    CARRIER(5);

    private final int shipLength;

    ShipType (int shipLength) {
        this.shipLength = shipLength;
    }

    int getLength() {
        return shipLength;
    }
}
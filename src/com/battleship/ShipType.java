package com.battleship;

public enum ShipType {
    DESTROYER("Destroyer",2),
    CRUISER("Cruiser",3);
//    SUBMARINE("Submarine",3),
  //  BATTLESHIP("Battleship",4),
    //CARRIER("Carrier",5);
    private final int shipLength;
    private final String shipName;
    ShipType ( String shipName, int shipLength) {
        this.shipLength = shipLength;
        this.shipName = shipName;
    }

    int getLength() {
        return shipLength;
    }

    String getShipName(){
        return shipName;
    }
}
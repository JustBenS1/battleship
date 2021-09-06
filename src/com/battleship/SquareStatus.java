package com.battleship;

public enum SquareStatus {

        EMPTY("E"),
        SHIP("O"),
        HIT("H"),
        MISSED("M"),
        SUNK("S");
        private String icon;


        SquareStatus (String i) {
            icon = i;
        }

        String getCharacter() {
            return icon;
        }






}

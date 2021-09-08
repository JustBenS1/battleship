package com.battleship;

public enum SquareStatus {

        EMPTY("E"),
        SHIP("O"),
        HIT("H"),
        MISSED("M"),
        SUNK("S");

        private final String icon;

        SquareStatus (String icon) {
            this.icon = icon;
        }

        String getCharacter() {
            return icon;
        }






}

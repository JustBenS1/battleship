package com.battleship;

public class SquareStatus {

    public enum statuses {
        EMPTY("E"),
        SHIP("O"),
        HIT("H"),
        MISSED("M"),
        SUNK("S");
        private String icon;


        statuses (String i) {
            icon = i;
        }

        String getCharacter() {
            return icon;
        }




    }

}

package com.battleship;

public enum SquareStatus {
        EMPTY("~", "~"),
        SHIP("O", "~"),
        HIT("H", "H"),
        MISSED("M", "M"),
        SUNK("S", "M");

        private final String shown;
        private final String hidden;

        SquareStatus (String shown, String hidden) {
            this.shown = shown;
            this.hidden = hidden;
        }

        String getShownIcon() {
            return shown;
        }

        String getHiddenIcon() {
            return hidden;
        }
}

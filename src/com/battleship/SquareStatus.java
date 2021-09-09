package com.battleship;

public enum SquareStatus {
        EMPTY("\uD83D\uDFE6", "\uD83D\uDFE6"),
        SHIP("\uD83D\uDFEB", "\uD83D\uDFE6"),
        HIT("\uD83D\uDCA2", "\uD83D\uDCA2"),
        MISSED("🌊", "🌊"),
        SUNK("\uD83D\uDCA5", "\uD83D\uDCA5");

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
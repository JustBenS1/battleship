package com.battleship;

public enum Directions {
    NORTH(),
    WEST(),
    SOUTH(),
    EAST();

    public boolean isValidDirection(String input) {
        if(input.equalsIgnoreCase("quit")){
            //endgame.setEndMatch(true);
            return true;
        }
        for (Directions direction :Directions.values()) {
            if (direction.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}

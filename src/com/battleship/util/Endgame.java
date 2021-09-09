package com.battleship.util;

public class Endgame {
    private static Endgame single_instance = null;
    private boolean isEndgame = false;
    private boolean isEndMatch = false;

    private Endgame() {}

    public static Endgame getInstance() {
        if (single_instance == null)
            single_instance = new Endgame();

        return single_instance;
    }

    public boolean getIsEndgame(){
        return this.isEndgame;
    }

    public void setEndgame(boolean isEndgame) {
        this.isEndgame = isEndgame;
    }

    public boolean getIsEndMatch(){
        return this.isEndMatch;
    }

    public void setEndMatch(boolean isEndMatch) {
        this.isEndMatch = isEndMatch;
    }
    //endgame scenarios check
}

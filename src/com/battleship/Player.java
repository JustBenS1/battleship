package com.battleship;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Ship> fleet;
    private int currentHP;

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public Player(int maxHP, ArrayList<Ship> fleet) {
        this.currentHP = maxHP;
        this.fleet = fleet;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

}

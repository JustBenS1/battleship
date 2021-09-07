package com.battleship;

import com.battleship.util.Display;

import java.util.ArrayList;

public class Player {
    private Display display = Display.getInstance();
    private final ArrayList<Ship> fleet;
    private int currentHP;

    public Player(int maxHP, ArrayList<Ship> fleet, int size) {
        this.currentHP = maxHP;
        this.fleet = fleet;
        Board ocean = new Board(size);
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public String getPlayerName() {
        return "";
    }

}

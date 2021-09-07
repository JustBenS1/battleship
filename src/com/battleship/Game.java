package com.battleship;

import java.util.ArrayList;

public class Game {
    private final int size;
    private Player player1;
    private Player player2;
    private int maxHp;

    public int getSize() {
        return size;
    }

    public Game(int size){
        this.size = size;
        ArrayList<Ship> baseFleet = createShipList();

        player1 = new Player(maxHp, baseFleet, getSize());
        player2 = new Player(maxHp, baseFleet, getSize());
    }

    private ArrayList<Ship> createShipList(){
        ArrayList<Ship> fleet= new ArrayList<>();
        int maxHp = 0;

        for (ShipType typeOfShip:ShipType.values()) {
            fleet.add(new Ship(typeOfShip.getLength()));
            maxHp += typeOfShip.getLength();
        }

        this.maxHp = maxHp;

        return fleet;
    }

    public void run(){
        System.out.println("i'm alive");
        // placement phase player1 (baseFleet)
        // placement phase player2


    }

    public void placeShips(Player player) {
        BoardFactory boardFactory = new BoardFactory(player.getOcean(), player.getFleet());

    }

}

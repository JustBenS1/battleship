package com.battleship;

import com.battleship.util.Display;
import com.battleship.util.Endgame;

import java.util.ArrayList;


public class Game {
    private final int size;
    private Player player1;
    private Player player2;
    private int maxHp;
    private Display display = Display.getInstance();
    private Endgame endgame = Endgame.getInstance();

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
        while(!endgame.getIsEndMatch()){
            System.out.println("i'm alive");
            placeShips(player1);
            if (endgame.getIsEndMatch()){
                continue;
            }
            placeShips(player2);
            if (endgame.getIsEndMatch()){
                continue;
            }
            // shooting
        }
    }

    public void placeShips(Player player) {
        BoardFactory boardFactory = new BoardFactory(player);
        boardFactory.run();
        System.out.println("___________");
        display.printBoard(player.getOcean());
    }

}

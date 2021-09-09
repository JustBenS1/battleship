package com.battleship;

import com.battleship.util.Coordinates;
import com.battleship.util.Display;
import com.battleship.util.Endgame;
import com.battleship.util.Input;

import java.util.ArrayList;


public class Game {
    private final int size;
    private Player player1;
    private Player player2;
    private int maxHp;
    private Display display = Display.getInstance();
    private Input input = Input.getInstance();
    private Endgame endgame = Endgame.getInstance();
    private int roundCounter = 0;

    public int getSize() {
        return size;
    }

    public Game(int size) {
        this.size = size;

        player1 = new Player(createShipList(), maxHp, getSize());
        player2 = new Player(createShipList(), maxHp,  getSize());
    }

    private ArrayList<Ship> createShipList() {
        ArrayList<Ship> fleet = new ArrayList<>();
        int maxHp = 0;

        for (ShipType typeOfShip : ShipType.values()) {
            fleet.add(new Ship(typeOfShip.getLength()));
            maxHp += typeOfShip.getLength();
            System.out.println(typeOfShip.getLength());
            System.out.println(typeOfShip);
        }

        this.maxHp = maxHp;

        return fleet;
    }

    public void run() {
        Coordinates targetCoordinate;
        while (!endgame.getIsEndMatch()) {
            System.out.println("i'm alive");
            placeShips(player1);
            if (endgame.getIsEndMatch()) {
                continue;
            }
            placeShips(player2);
            if (endgame.getIsEndMatch()) {
                continue;
            }

            while (!endgame.getIsEndMatch()) {
                if (roundCounter % 2 == 0) {
                    targetCoordinate = getValidShot(player1, player2, "Player 1");
                    hitTarget(player2, targetCoordinate);
                } else {
                    targetCoordinate = getValidShot(player2, player1, "Player 2");
                    hitTarget(player1, targetCoordinate);
                }

                roundCounter++;
            }
        }
    }

    public void placeShips(Player player) {
        BoardFactory boardFactory = new BoardFactory(player);
        boardFactory.run();
        System.out.println("shooting phase starts");
        System.out.println();
    }

    public Coordinates getValidShot(Player shooter, Player target, String currentPlayer) {
        String targetCoordinateInput = "";
        Coordinates targetCoordinate;
        boolean wasTargetValid = true;
        while (true) {
            display.clear();
            display.printBoard(target.getOcean(),false);
            display.printMessageLine(currentPlayer + " : " + shooter.getPlayerName() + "'s turn!");
            if (!wasTargetValid){
                display.printMessageLine("Last input was invalid!");
            }else{
                display.printMessageLine("");
            }
            display.printMessage("Please give a target coordinate (eg.: A1) : ");

            targetCoordinateInput = input.getInput();
            if (input.isCoordinateOnBoard(targetCoordinateInput, target.getOcean().getSize())) {

                targetCoordinate = input.convertToCoordinates(targetCoordinateInput);
                if (target.getOcean().isSquareShootable(targetCoordinate)) {
                    break;
                }
            }
            wasTargetValid = false;
        }
        return targetCoordinate;
    }

    public void hitTarget(Player target, Coordinates targetCoordinate) {
        Square targetSquare = target.getOcean().getOceanSquare(targetCoordinate);
        Board board = target.getOcean();
        if (targetSquare.getStatus().name().equals("EMPTY")) {
            board.setOceanSquare(targetCoordinate, SquareStatus.MISSED);
            target.setOcean(board);
        } else {
            System.out.println(target.getCurrentHP());
            target.setCurrentHP(target.getCurrentHP() - 1);

            Ship targetShip = target.getShipByCoordinate(targetCoordinate);
            System.out.println(target.getCurrentHP());

            if (target.isShipBarelyAlive(targetShip)) {
                Coordinates shipCurrentCellCoordinate;
                for (Square shipSquare:targetShip.getSquares()) {
                    shipCurrentCellCoordinate = new Coordinates(shipSquare.getCoordinates().getX(),shipSquare.getCoordinates().getY());
                    board.setOceanSquare(shipCurrentCellCoordinate, SquareStatus.SUNK);
                    shipSquare.setStatus(SquareStatus.SUNK);
                }
            } else {
                board.setOceanSquare(targetCoordinate, SquareStatus.HIT);
                targetShip.setShipSquareByCoordinates(targetCoordinate,SquareStatus.HIT);
            }
        }
    }

}

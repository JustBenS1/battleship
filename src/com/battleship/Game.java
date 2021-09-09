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
        ArrayList<Ship> baseFleet1 = createShipList();
        ArrayList<Ship> baseFleet2 = createShipList();


        player1 = new Player(maxHp, baseFleet1, getSize());
        player2 = new Player(maxHp, baseFleet2, getSize());
    }

    private ArrayList<Ship> createShipList() {
        ArrayList<Ship> fleet = new ArrayList<>();
        int maxHp = 0;

        for (ShipType typeOfShip : ShipType.values()) {
            fleet.add(new Ship(typeOfShip.getLength()));
            maxHp += typeOfShip.getLength();
        }

        this.maxHp = maxHp;

        return fleet;
    }

    public void run() {
        display.printBoardsHeaders(player1, player2, 2, 10);
        display.printTwoBoards(player1.getOcean(), player2.getOcean(), true, 2, 10);
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

    public Coordinates getValidShot(Player player1, Player player2, String currentPlayer) {
        display.printBoard(player2.getOcean(),false);
        display.printMessageLine(currentPlayer + " : " + player1.getPlayerName() + "'s turn!");
        System.out.println("________________");
        String targetCoordinateInput;
        Coordinates targetCoordinate;
        while (true) {
            targetCoordinateInput = input.getInput();
            if (input.isCoordinateOnBoard(targetCoordinateInput, player2.getOcean().getSize())) {

                targetCoordinate = input.convertToCoordinates(targetCoordinateInput);
                if (player2.getOcean().isSquareShootable(targetCoordinate)) {
                    break;
                }
            }
        }
        return targetCoordinate;
    }

    public void hitTarget(Player target, Coordinates targetCoordinate) {
        Square targetSquare = target.getOcean().getOceanSquare(targetCoordinate);
        Square newSquare;
        Board board;
        if (targetSquare.getStatus().name().equals("EMPTY")) {
            board = target.getOcean();
            board.setOceanSquare(targetCoordinate, SquareStatus.MISSED);
            target.setOcean(board);
        } else {
            target.setCurrentHP(target.getCurrentHP() - 1);
            Ship targetShip = target.getShipByCoordinate(targetCoordinate);
            if (target.isShipBarelyAlive(targetShip)) {
                //Sinking
                System.out.println(targetShip.getSquares().size());
                System.out.println("fail");
            } else {
                board = target.getOcean();
                board.setOceanSquare(targetCoordinate, SquareStatus.HIT);
                target.setOcean(board);


                targetShip.setShipSquareByCoordinates(targetCoordinate,SquareStatus.HIT);
            }
        }
    }
}

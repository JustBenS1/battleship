package com.battleship;

import com.battleship.util.Coordinates;
import com.battleship.util.Display;
import com.battleship.util.Endgame;
import com.battleship.util.Input;
import java.util.ArrayList;


public class Game {
    private final int size;
    private final boolean isRandomPlacement;
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

    public Game(int size, boolean isRandomPlacement) {
        this.size = size;
        this.isRandomPlacement = isRandomPlacement;

        player1 = new Player(createShipList(), maxHp, getSize(), 1);
        player2 = new Player(createShipList(), maxHp, getSize(),2);
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
        while (!endgame.getIsEndMatch()) {

            Coordinates targetCoordinate;
            placeShips(player1, isRandomPlacement);
            if (endgame.getIsEndMatch()) {
                continue;
            }
            placeShips(player2, isRandomPlacement);
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

    public void placeShips(Player player, boolean isRandomPlacement) {
        BoardFactory boardFactory = new BoardFactory(player, isRandomPlacement);
        boardFactory.run();
    }

    public Coordinates getValidShot(Player shooter, Player target, String currentPlayer) {
        String targetCoordinateInput;
        Coordinates targetCoordinate = new Coordinates(0, 0);
        boolean wasTargetValid = true;

        while (!endgame.getIsEndMatch()) {
            display.clear();
            if (shooter.getnThPlayer() == 1) {
                display.printBoardsHeaders(player1, player2, 2, 10);
                display.printTwoBoards(shooter.getOcean(), target.getOcean(), false, false);
            } else if (shooter.getnThPlayer() == 2) {
                display.printBoardsHeaders(player1, player2, 2, 10);
                display.printTwoBoards(target.getOcean(), shooter.getOcean(), false, false);
            }
            display.printMessageLine(currentPlayer + " : " + shooter.getPlayerName() + "'s turn!");
            if (!wasTargetValid){
                display.printMessageLine("Last input was invalid!");
            }else{
                display.printMessageLine("");
            }
            display.printMessage("Please give a target coordinate (eg.: A1) : ");

            targetCoordinateInput = input.getInput();
            if (input.isCoordinateOnBoard(targetCoordinateInput, target.getOcean().getSize())) {
                if (endgame.getIsEndMatch()){
                    continue;
                }
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
            target.setCurrentHP(target.getCurrentHP() - 1);

            Ship targetShip = target.getShipByCoordinate(targetCoordinate);

            if (target.isShipBarelyAlive(targetShip)) {
                Coordinates shipCurrentCellCoordinate;
                for (Square shipSquare:targetShip.getSquares()) {
                    shipCurrentCellCoordinate = new Coordinates(shipSquare.getCoordinates().getX(),shipSquare.getCoordinates().getY());
                    board.setOceanSquare(shipCurrentCellCoordinate, SquareStatus.SUNK);
                    shipSquare.setStatus(SquareStatus.SUNK);
                }
                if (!target.isPlayerAlive()){
                    endgame.setEndMatch(true);
                    endgame.setNewWinner(getWinnerName(target.getPlayerName()), roundCounter);
                }
            } else {
                board.setOceanSquare(targetCoordinate, SquareStatus.HIT);
                targetShip.setShipSquareByCoordinates(targetCoordinate,SquareStatus.HIT);
            }
        }
    }
    public String getWinnerName(String loserName){
        if (!player1.getPlayerName().equals(loserName)){
            return player1.getPlayerName();
        }else{
            return player2.getPlayerName();
        }


    }
}

package com.battleship.util;


import com.battleship.Board;
import com.battleship.Player;
import com.battleship.Square;

public class Display {
    private static Display single_instance = null;

    private Display() {
    }

    public static Display getInstance() {
        if (single_instance == null)
            single_instance = new Display();

        return single_instance;
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printMessageLine(String message) {
        System.out.println(message);
    }

    public void printMenuOptions(String[] menuOptions, String message) {
        for (String menuOption : menuOptions) {
            printMessageLine(menuOption);
        }
        printMessage(message);
    }

    public void clear() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printBoard(Board board, boolean hiddenIcon) {

        int fieldSpace = 2;

        printNumberLine(board.getSize(), fieldSpace);
        System.out.println();

        for (int i = 0; i < board.getSize(); i++) {
            printFieldsAndLetters(board, hiddenIcon, fieldSpace, i);
            System.out.println();
        }

        printNumberLine(board.getSize(), fieldSpace);
        System.out.println();
    }

    public void printTwoBoards(Board shooter, Board target, boolean isHiddenIcon, int fieldSpace, int spacing) {
        printNumberLine(shooter.getSize(), fieldSpace);
        printSpacing(spacing);
        printNumberLine(target.getSize(), fieldSpace);
        System.out.println();

        for (int i = 0; i < shooter.getSize(); i++) {
            printFieldsAndLetters(shooter, true, fieldSpace, i);
            printSpacing(spacing);
            printFieldsAndLetters(target, isHiddenIcon, fieldSpace, i);
            System.out.println();
        }

        printNumberLine(shooter.getSize(), fieldSpace);
        printSpacing(spacing);
        printNumberLine(target.getSize(), fieldSpace);
        System.out.println();
    }

    private void printNumberLine(int length, int fieldSpace) {
        for (int i = 0; i < fieldSpace; i++) {
            System.out.print(" ");
        }
        for (int i = 1; i <= length; i++) {
            System.out.printf("%" + fieldSpace + "s", i % 10);
        }
        printSpacing(fieldSpace);
    }

    private void printSpacing(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }

    private void printFieldsAndLetters(Board board, boolean hiddenIcon, int fieldSpace, int i) {
        Square[][] ocean = board.getOcean();
            String letter = String.valueOf(Character.toChars(65 + i));
            System.out.printf("%" + fieldSpace + "s", letter);

            if (hiddenIcon) {
                for (int j = 0; j < board.getSize(); j++) {
                    System.out.printf("%" + fieldSpace + "s", ocean[i][j].getHidden());
                }
            } else {
                for (int j = 0; j < board.getSize(); j++) {
                    System.out.printf("%" + fieldSpace + "s", ocean[i][j].getShown());
                }
            }
        System.out.printf("%" + fieldSpace + "s", letter);
    }

    public void printBoardsHeaders(Player player1, Player player2, int fieldSpace, int spacing) {
        int boardCharacterSize = player1.getOcean().getSize() * fieldSpace;
        printSpacing(fieldSpace * 2);
        System.out.printf("%-" + (boardCharacterSize)  + "s", player1.getPlayerName());
        printSpacing(spacing);
        printSpacing(fieldSpace * 2);
        System.out.printf("%-" + boardCharacterSize + "s", player2.getPlayerName());
        System.out.println();
    }
}
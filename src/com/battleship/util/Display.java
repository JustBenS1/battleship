package com.battleship.util;


import com.battleship.Board;
import com.battleship.Square;

import java.util.Arrays;

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

        Square[][] ocean = board.getOcean();

        printNumberLine(board.getSize(), fieldSpace);

        for (int i = 0; i < board.getSize(); i++) {
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

            System.out.printf("%" + (fieldSpace) + "s", letter);
            System.out.println();
        }
        printNumberLine(board.getSize(), fieldSpace);
    }

    public void printTwoBoards(Board shooter, Board target, boolean hiddenIcon) {
        int fieldSpace = 2;

        printNumberLine(shooter.getSize(), fieldSpace);

        System.out.println();
        Square[][] ocean = shooter.getOcean();
        printNumberLine(ocean.length, fieldSpace);

        for (int i = 0; i < shooter.getSize(); i++) {
            String letter = String.valueOf(Character.toChars(65 + i));
            System.out.printf("%" + fieldSpace + "s", letter);

            if (hiddenIcon) {
                for (int j = 0; j < shooter.getSize(); j++) {
                    System.out.printf("%" + fieldSpace + "s", ocean[i][j].getHidden());
                }
            } else {
                for (int j = 0; j < shooter.getSize(); j++) {
                    System.out.printf("%" + fieldSpace + "s", ocean[i][j].getShown());
                }
            }

            System.out.printf("%" + (fieldSpace) + "s", letter);
            System.out.println();
        }
        printNumberLine(ocean.length, fieldSpace);
    }

    private void printNumberLine (int length, int fieldSpace) {
        for (int i = 0; i < fieldSpace; i++) {
            System.out.print(" ");
        }
        for (int i = 1; i <= length; i++) {
            System.out.printf("%" + fieldSpace + "s", i % 10);
        }
        System.out.println();
    }
}

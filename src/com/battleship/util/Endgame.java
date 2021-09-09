package com.battleship.util;

import java.util.ArrayList;

public class Endgame {
    private static Endgame single_instance = null;
    private boolean isEndgame = false;
    private boolean isEndMatch = false;
    private Winner winner;
    private int boardSize = 10;
    private ArrayList<Winner> highScores = new ArrayList<>();

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

    public Winner getWinnerData(Winner winner) {
        return winner;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setNewWinner(String winnerName, int roundCount) {
        int score = (boardSize * boardSize - roundCount) * 15;
        this.winner = new Winner(winnerName, score);
        updateHighScores();
    }

    public ArrayList<Winner> getHighScores() {
        return highScores;
    }

    public void setHighScores(ArrayList<Winner> highScores) {
        this.highScores = highScores;
    }

    public void updateHighScores(){
        for (Winner checkedWinner: highScores) {
            if (checkedWinner.getName().equals(winner.getName())){
                if (checkedWinner.getScore() < winner.getScore()){
                    checkedWinner.setScore(winner.getScore());
                }
                break;
            }
        }
        highScores.add(winner);
        sortHighScores(this.highScores);
        if (highScores.size()>10){
            getTopTenHighScores();
        }
    }

    public void sortHighScores(ArrayList<Winner> highScores) {
        int n = highScores.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if (highScores.get(j).getScore() < highScores.get(j + 1).getScore()) {
                    Winner transfer = highScores.get(j);
                    highScores.set(j, highScores.get(j + 1));
                    highScores.set(j + 1, transfer);
                }
            }
        }
        setHighScores(highScores);
    }

    public void getTopTenHighScores(){
        ArrayList<Winner> newHighScores = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            newHighScores.add(highScores.get(i));
        }
        this.highScores = newHighScores;
    }
}

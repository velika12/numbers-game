package com.velika12.numbers;

public class GameModel {

    public GameModel(final String gameId, final int[][] gameField, final int totalScore, final boolean gameOver) {
        this.gameId = gameId;
        this.gameField = gameField;
        this.totalScore = totalScore;
        this.gameOver = gameOver;
    }

    private String gameId;

    private int[][] gameField;

    private int totalScore;

    private boolean gameOver;

    public String getGameId() {
        return gameId;
    }

    public int[][] getGameField() {
        return gameField;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

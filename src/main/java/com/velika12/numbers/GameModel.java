package com.velika12.numbers;

import com.velika12.numbers.game.Game;

public class GameModel {

    public GameModel(final String gameId, final Game game) {
        this.gameId = gameId;
        this.gameField = game.getField();
        this.totalScore = game.getTotalScore();
        this.gameOver = game.isOver();
        this.turnNumber = game.getNumberOfMoves();
    }

    private String gameId;

    private int[][] gameField;

    private int totalScore;

    private boolean gameOver;

    private int turnNumber;

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

    public int getTurnNumber() {
        return turnNumber;
    }
}

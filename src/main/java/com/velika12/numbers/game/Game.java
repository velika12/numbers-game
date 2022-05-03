package com.velika12.numbers.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private int totalScore = 0;
    private int numberOfMoves = 0;
    private boolean over = false;

    private GameField gameField = new GameField(4);

    public Game() {
        display();
    }

    public int[][] getField() {
        return gameField.get();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void play(Direction direction) {
        log.info(++numberOfMoves + " - Moving " + direction);
        totalScore += gameField.move(direction);
        display();
    }

    private void display() {
        log.info("----------------------------");
        log.info("Total score: " + totalScore);
        gameField.draw();
        log.info("----------------------------");
    }

    public boolean isOver() {
        if (!over) {
            over = gameField.isFilled() && gameField.isCollapseImpossible();
        }

        return over;
    }
}


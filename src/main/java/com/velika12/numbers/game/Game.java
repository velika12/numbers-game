package com.velika12.numbers.game;

public class Game {

    private int totalScore = 0;
    private int numberOfMoves = 0;
    private boolean over = false;

    private GameField gameField = new GameField(4);

    public Game() {
        display();
    }

    public void play(Direction direction) {
        System.out.println(++numberOfMoves + " - Moving " + direction);
        totalScore += gameField.move(direction);
        display();
    }

    private void display() {
        System.out.println("----------------------------");
        System.out.println("Total score: " + totalScore);
        gameField.draw();
        System.out.println("----------------------------");
    }

    public boolean isOver() {
        if (!over) {
            over = gameField.isFilled() && gameField.isCollapseImpossible();
        }

        return over;
    }
}


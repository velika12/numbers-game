package com.velika12.numbers.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class GameField {
    private final int size;
    private Random rand = new Random(48);

    private Cell[][] fieldRows;
    private Cell[][] fieldColumns;

    private List<Cell> freeCells = new ArrayList<>();

    public GameField(int size) {
        this.size = size;
        this.fieldRows = new Cell[size][size];
        this.fieldColumns = new Cell[size][size];

        initCells();

        for (int i = 0; i < 2; i++) {
            spawnNumber();
        }
    }

    private void initCells() {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                Cell cell = new Cell(rowIndex, columnIndex);
                fieldRows[rowIndex][columnIndex] = cell;
                fieldColumns[columnIndex][rowIndex] = cell;
                freeCells.add(cell);
            }
        }
    }

    private void spawnNumber() {
        System.out.println("Free cells: " + freeCells.size());

        int freeCellIndex = rand.nextInt(freeCells.size());
        Cell cell = freeCells.get(freeCellIndex);

        int number = generateNumber();
        cell.setValue(number);

        System.out.printf("Spawned number=%d row=%d column=%d\n", number, cell.getRowIndex(), cell.getColumnIndex());
    }

    private int generateNumber() {
        int magicNumber = rand.nextInt(100);
        return magicNumber > 95 ? 4 : 2;
    }

    public void draw() {
        for (final Cell[] row : fieldRows) {
            System.out.println(Arrays.toString(row));
        }
    }

    public boolean isFilled() {
        return freeCells.isEmpty();
    }

    public boolean isCollapseImpossible() {
        for (int i = 0; i < fieldRows.length; i++) {
            for (int j = 0; j < fieldRows.length; j++) {
                Cell current = fieldRows[i][j];
                if (j + 1 < fieldRows.length) {
                    Cell right = fieldRows[i][j + 1];
                    if (current.getValue() == right.getValue()) {
                        return false;
                    }
                }
                if (i + 1 < fieldRows.length) {
                    Cell bottom = fieldRows[i + 1][j];
                    if (current.getValue() == bottom.getValue()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int move(Direction direction) {
        switch (direction) {
            case RIGHT:
                return move(fieldRows, GameEngine::moveRight);
            case LEFT:
                return move(fieldRows, GameEngine::moveLeft);
            case DOWN:
                return move(fieldColumns, GameEngine::moveRight);
            case UP:
                return move(fieldColumns, GameEngine::moveLeft);
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    private int move(Cell[][] field, Function<Cell[], Integer> moveFunc) {
        boolean moved = false;
        int fieldScore = 0;

        for (Cell[] cells : field) {
            Integer cellsScore = moveFunc.apply(cells);
            if (cellsScore != null) {
                fieldScore += cellsScore;
                moved = true;
            }
        }

        if (moved) {
            spawnNumber();
        }

        return fieldScore;
    }

    class Cell {
        private int rowIndex;
        private int columnIndex;
        private int value;

        public Cell(final int rowIndex, final int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.value = 0;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public int getValue() {
            return value;
        }

        public void setValue(final int value) {
            this.value = value;
            if (value == 0) {
                freeCells.add(this);
            } else {
                freeCells.remove(this);
            }
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Cell cell = (Cell) o;
            return rowIndex == cell.rowIndex &&
                    columnIndex == cell.columnIndex &&
                    value == cell.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, columnIndex, value);
        }
    }
}

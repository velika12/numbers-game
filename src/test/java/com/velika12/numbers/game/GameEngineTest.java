package com.velika12.numbers.game;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameEngineTest {

    private static GameField gameField = new GameField(4);

    @ParameterizedTest
    @MethodSource
    public void moveRight(GameField.Cell[] array, GameField.Cell[] expectedArray, Integer expectedResult) {
        Integer result = GameEngine.moveRight(array);

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertArrayEquals(expectedArray, array);
    }

    private static Stream<Arguments> moveRight() {
        return Stream.of(
                Arguments.arguments(createCells(2,2,2,2), createCells(0,0,4,4), 8),
                Arguments.arguments(createCells(2,4,4,2), createCells(0,2,8,2), 8),
                Arguments.arguments(createCells(0,2,2,4), createCells(0,0,4,4), 4),
                Arguments.arguments(createCells(2,2,0,4), createCells(0,0,4,4), 4),
                Arguments.arguments(createCells(2,0,0,4), createCells(0,0,2,4), 0),
                Arguments.arguments(createCells(2,0,0,0), createCells(0,0,0,2), 0),
                Arguments.arguments(createCells(2,2,2,0), createCells(0,0,2,4), 4),
                Arguments.arguments(createCells(2,2,0,0), createCells(0,0,0,4), 4),
                Arguments.arguments(createCells(4,2,0,0), createCells(0,0,4,2), 0),
                Arguments.arguments(createCells(4,4,2,0), createCells(0,0,8,2), 8),
                Arguments.arguments(createCells(2,4,2,0), createCells(0,2,4,2), 0),
                Arguments.arguments(createCells(0,2,4,2), createCells(0,2,4,2), null),
                Arguments.arguments(createCells(0,0,4,2), createCells(0,0,4,2), null),
                Arguments.arguments(createCells(0,0,0,2), createCells(0,0,0,2), null),
                Arguments.arguments(createCells(2,4,2,4), createCells(2,4,2,4), null),
                Arguments.arguments(createCells(0,0,0,0), createCells(0,0,0,0), null)
        );
    }
    
    private static GameField.Cell[] createCells(int... array) {
        GameField.Cell[] cells = new GameField.Cell[array.length];
        for (int i = 0; i < array.length; i++) {
            GameField.Cell cell = gameField.new Cell(0, 0);
            cell.setValue(array[i]);
            cells[i] = cell;
        }
        
        return cells;
    }

    @ParameterizedTest
    @MethodSource
    public void moveLeft(GameField.Cell[] array, GameField.Cell[] expectedArray, Integer expectedResult) {
        Integer result = GameEngine.moveLeft(array);

        Assertions.assertEquals(expectedResult, result);
        Assertions.assertArrayEquals(expectedArray, array);
    }

    private static Stream<Arguments> moveLeft() {
        return Stream.of(
                Arguments.arguments(createCells(2,2,2,2), createCells(4,4,0,0), 8),
                Arguments.arguments(createCells(2,4,4,2), createCells(2,8,2,0), 8),
                Arguments.arguments(createCells(0,2,2,4), createCells(4,4,0,0), 4),
                Arguments.arguments(createCells(2,2,0,4), createCells(4,4,0,0), 4),
                Arguments.arguments(createCells(2,0,0,4), createCells(2,4,0,0), 0),
                Arguments.arguments(createCells(0,0,0,2), createCells(2,0,0,0), 0),
                Arguments.arguments(createCells(2,2,2,0), createCells(4,2,0,0), 4),
                Arguments.arguments(createCells(2,2,0,0), createCells(4,0,0,0), 4),
                Arguments.arguments(createCells(0,0,4,2), createCells(4,2,0,0), 0),
                Arguments.arguments(createCells(4,4,2,0), createCells(8,2,0,0), 8),
                Arguments.arguments(createCells(0,2,4,2), createCells(2,4,2,0), 0),
                Arguments.arguments(createCells(2,0,0,0), createCells(2,0,0,0), null),
                Arguments.arguments(createCells(4,2,0,0), createCells(4,2,0,0), null),
                Arguments.arguments(createCells(2,4,2,0), createCells(2,4,2,0), null),
                Arguments.arguments(createCells(2,4,2,4), createCells(2,4,2,4), null),
                Arguments.arguments(createCells(0,0,0,0), createCells(0,0,0,0), null)
        );
    }
}
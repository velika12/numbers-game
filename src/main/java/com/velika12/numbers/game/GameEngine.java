package com.velika12.numbers.game;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public class GameEngine {

    static Integer moveRight(GameField.Cell[] array) {
        return move(array, array.length - 1, i -> --i, i -> i >= 0);
    }

    static Integer moveLeft(GameField.Cell[] array) {
        return move(array, 0, i -> ++i, i -> i < array.length);
    }

    private static Integer move(GameField.Cell[] array, int startIndex, IntFunction<Integer> nextIndexFunc, Predicate<Integer> stopPredicate) {
        int i = startIndex;
        int j = startIndex;

        boolean movedOrCollapsed = false;
        int score = 0;

        while (stopPredicate.test(i)) {
            if (i == j) {
                i = nextIndexFunc.apply(i);
                continue;
            }
            // search for a non-zero element
            GameField.Cell current = array[i];
            if (current.getValue() == 0) {
                i = nextIndexFunc.apply(i);
                continue;
            }
            // move element
            GameField.Cell collapseTarget = array[j];
            if (collapseTarget.getValue() == 0) {
                collapseTarget.setValue(current.getValue());
                current.setValue(0);

                i = nextIndexFunc.apply(i);
                movedOrCollapsed = true;

                continue;
            }
            // try to collapse
            if (current.getValue() == collapseTarget.getValue()) {
                int collisionValue = collapseTarget.getValue() + current.getValue();
                collapseTarget.setValue(collisionValue);
                current.setValue(0);

                movedOrCollapsed = true;
                score += collisionValue;

                i = nextIndexFunc.apply(i);
                j = nextIndexFunc.apply(j);

                continue;
            }

            j = nextIndexFunc.apply(j);
        }

        return movedOrCollapsed ? score : null;
    }
}

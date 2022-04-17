package com.velika12.numbers;

import com.velika12.numbers.game.Direction;
import com.velika12.numbers.game.Game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        for (int i = 0; i < 100; i++) {
            for (Direction direction : Direction.values()) {
                game.play(direction);
                if (game.isOver()) {
                    System.out.println("Game Over");
                    System.exit(0);
                }
            }
        }
    }
}

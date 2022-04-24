package com.velika12.numbers;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.velika12.numbers.game.Direction;
import com.velika12.numbers.game.Game;

@Service
public class GameService {

    private final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public GameModel startGame() {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game();

        gameMap.put(gameId, game);

        return new GameModel(gameId, game.getField(), game.getTotalScore(), game.isOver());
    }

    public GameModel loadGame(String gameId) {
        Game game = gameMap.get(gameId);

        if (game == null) {
            return null;
        }

        return new GameModel(gameId, game.getField(), game.getTotalScore(), game.isOver());
    }

    public GameModel playGame(String gameId, Direction direction) {
        Game game = gameMap.get(gameId);

        if (game == null) {
            return null;
        }

        if (game.isOver()) {
            return new GameModel(gameId, game.getField(), game.getTotalScore(), game.isOver());
        }

        game.play(direction);

        return new GameModel(gameId, game.getField(), game.getTotalScore(), game.isOver());
    }
}

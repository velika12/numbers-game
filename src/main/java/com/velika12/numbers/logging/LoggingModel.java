package com.velika12.numbers.logging;

import java.time.Clock;
import java.time.Instant;

import com.velika12.numbers.GameModel;
import com.velika12.numbers.game.Direction;

public class LoggingModel {

    private enum Action {
        INIT, RIGHT, LEFT, DOWN, UP
    }

    public LoggingModel(final GameModel gameModel) {
        this(gameModel, Action.INIT);
    }

    public LoggingModel(final GameModel gameModel, Direction direction) {
        this(gameModel, Action.valueOf(direction.name()));
    }

    private LoggingModel(final GameModel gameModel, Action action) {
        this.game = gameModel;
        this.timestamp = Instant.now(Clock.systemUTC()).getEpochSecond();
        this.action = action;
    }

    private final GameModel game;

    private final long timestamp;

    private final Action action;

    public GameModel getGame() {
        return game;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Action getAction() {
        return action;
    }
}

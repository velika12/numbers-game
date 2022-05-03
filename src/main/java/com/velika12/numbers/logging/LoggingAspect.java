package com.velika12.numbers.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velika12.numbers.GameModel;
import com.velika12.numbers.game.Direction;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterReturning(
            pointcut="execution(* com.velika12.numbers.GameService.startGame())",
            returning="gameModel")
    public void logAfterStartGame(GameModel gameModel) throws JsonProcessingException {
        MDC.put("gameId", gameModel.getGameId());
        log.info(objectMapper.writeValueAsString(new LoggingModel(gameModel)));
        MDC.remove("gameId");
    }

    @AfterReturning(
            pointcut="execution(* com.velika12.numbers.GameService.playGame(..)) && args(gameId,direction)",
            argNames="gameId,direction,gameModel",
            returning="gameModel")
    public void logAfterPlayGame(String gameId, Direction direction, GameModel gameModel) throws JsonProcessingException {

        if (gameModel == null) return;

        MDC.put("gameId", gameModel.getGameId());
        log.info(objectMapper.writeValueAsString(new LoggingModel(gameModel, direction)));
        MDC.remove("gameId");
    }
}

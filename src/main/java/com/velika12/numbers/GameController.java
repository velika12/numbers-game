package com.velika12.numbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.velika12.numbers.game.Direction;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String index() {
        return "2048 Game!";
    }

    @PostMapping("/game")
    public GameModel startGame() {
        return gameService.startGame();
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<GameModel> loadGame(@PathVariable String id) {
        GameModel result = gameService.loadGame(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<GameModel> playGame(@PathVariable String id, @RequestBody Direction direction) {

        Boolean gameOver = gameService.isGameOver(id);
        if (gameOver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (gameOver.equals(true)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        GameModel result = gameService.playGame(id, direction);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

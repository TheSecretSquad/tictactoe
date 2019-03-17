package com.disalvo.peter.tictactoe.gameimplementation.exceptions;

public class GameExceptionNotStarted extends RuntimeException {
    public GameExceptionNotStarted() {
        super("Game not started");
    }
}

package com.disalvo.peter.tictactoe.gameimplementation.exceptions;

public class GameExceptionRestartStopped extends RuntimeException {
    public GameExceptionRestartStopped() {
        super("Cannot restart a stopped game.");
    }
}

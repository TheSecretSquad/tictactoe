package com.disalvo.peter.tictactoe.gameimplementation.exceptions;

public class GameExceptionAlreadyStarted extends RuntimeException {
    public GameExceptionAlreadyStarted() {
        super("Game already started.");
    }
}

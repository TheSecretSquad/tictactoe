package com.disalvo.peter.tictactoe.state;

public class GameExceptionAlreadyStarted extends RuntimeException {
    public GameExceptionAlreadyStarted() {
        super("Game already started");
    }
}

package com.disalvo.peter.tictactoe.state;

public class GameExceptionNotStarted extends RuntimeException {
    public GameExceptionNotStarted() {
        super("Game not started");
    }
}

package com.disalvo.peter.tictactoe.state;

public class GameExceptionRestartStopped extends RuntimeException {
    public GameExceptionRestartStopped() {
        super("Cannot restart a stopped game");
    }
}

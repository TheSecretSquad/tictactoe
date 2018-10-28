package com.disalvo.peter.tictactoe;

class GameExceptionRestartStopped extends RuntimeException {
    public GameExceptionRestartStopped() {
        super("Cannot restart a stopped game");
    }
}

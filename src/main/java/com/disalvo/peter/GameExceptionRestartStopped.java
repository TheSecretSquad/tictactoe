package com.disalvo.peter;

class GameExceptionRestartStopped extends RuntimeException {
    public GameExceptionRestartStopped() {
        super("Cannot restart a stopped game");
    }
}

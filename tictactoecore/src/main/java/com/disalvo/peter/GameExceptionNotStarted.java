package com.disalvo.peter;

class GameExceptionNotStarted extends RuntimeException {
    public GameExceptionNotStarted() {
        super("Game not started");
    }
}

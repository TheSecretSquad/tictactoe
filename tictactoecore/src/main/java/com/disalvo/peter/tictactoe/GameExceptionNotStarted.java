package com.disalvo.peter.tictactoe;

class GameExceptionNotStarted extends RuntimeException {
    public GameExceptionNotStarted() {
        super("Game not started");
    }
}

package com.disalvo.peter.tictactoe;

class GameExceptionAlreadyStarted extends RuntimeException {
    public GameExceptionAlreadyStarted() {
        super("Game already started");
    }
}

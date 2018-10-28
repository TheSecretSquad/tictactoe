package com.disalvo.peter.tictactoe;

class GameExceptionAlreadyStopped extends RuntimeException {
    public GameExceptionAlreadyStopped() {
        super("Game already stopped");
    }
}

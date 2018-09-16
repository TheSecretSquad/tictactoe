package com.disalvo.peter;

class GameExceptionAlreadyStopped extends RuntimeException {
    public GameExceptionAlreadyStopped() {
        super("Game already stopped");
    }
}

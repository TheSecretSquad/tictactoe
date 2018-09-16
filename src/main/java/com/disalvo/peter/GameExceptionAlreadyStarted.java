package com.disalvo.peter;

class GameExceptionAlreadyStarted extends RuntimeException {
    public GameExceptionAlreadyStarted() {
        super("Game already started");
    }
}

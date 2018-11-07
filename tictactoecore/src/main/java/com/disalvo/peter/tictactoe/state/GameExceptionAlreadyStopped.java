package com.disalvo.peter.tictactoe.state;

public class GameExceptionAlreadyStopped extends RuntimeException {
    public GameExceptionAlreadyStopped() {
        super("Game already stopped");
    }
}

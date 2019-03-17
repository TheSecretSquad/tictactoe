package com.disalvo.peter.tictactoe.gameimplementation.exceptions;

public class GameExceptionAlreadyStopped extends RuntimeException {
    public GameExceptionAlreadyStopped() {
        super("Already stopped.");
    }
}

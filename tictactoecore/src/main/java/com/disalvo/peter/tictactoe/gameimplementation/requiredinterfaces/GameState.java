package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

public interface GameState {
    GameState started();

    GameState stopped();

    GameState ensureCanPlay();
}

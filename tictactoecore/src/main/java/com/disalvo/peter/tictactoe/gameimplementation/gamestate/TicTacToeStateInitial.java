package com.disalvo.peter.tictactoe.gameimplementation.gamestate;

import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionNotStarted;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameState;

public class TicTacToeStateInitial implements GameState {
    public GameState started() {
        return new TicTacToeStateStarted();
    }

    @Override
    public GameState stopped() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public GameState ensureCanPlay() {
        throw new GameExceptionNotStarted();
    }
}

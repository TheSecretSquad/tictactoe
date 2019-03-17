package com.disalvo.peter.tictactoe.gameimplementation.gamestate;

import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionAlreadyStarted;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameState;

class TicTacToeStateStarted implements GameState {

    @Override
    public GameState started() {
        throw new GameExceptionAlreadyStarted();
    }

    @Override
    public GameState stopped() {
        return new TicTacToeStateStopped();
    }

    @Override
    public GameState ensureCanPlay() {
        return null;
    }
}

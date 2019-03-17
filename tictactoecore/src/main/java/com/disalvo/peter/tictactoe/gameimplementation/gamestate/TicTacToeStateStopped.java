package com.disalvo.peter.tictactoe.gameimplementation.gamestate;

import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionAlreadyStopped;
import com.disalvo.peter.tictactoe.gameimplementation.exceptions.GameExceptionRestartStopped;
import com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces.GameState;

class TicTacToeStateStopped implements GameState {

    @Override
    public GameState started() {
        throw new GameExceptionRestartStopped();
    }

    @Override
    public GameState stopped() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public GameState ensureCanPlay() {
        return null;
    }
}

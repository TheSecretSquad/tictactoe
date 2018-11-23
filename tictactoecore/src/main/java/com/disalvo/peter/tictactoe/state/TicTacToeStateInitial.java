package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.TicTacToeState;

public class TicTacToeStateInitial implements TicTacToeState {
    @Override
    public TicTacToeState start() {
        return new TicTacToeStateStarted();
    }

    @Override
    public TicTacToeState stop() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public TicTacToeState ensureCanPlay() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public TicTacToeState next(GameEndCondition endCondition) {
        throw new GameExceptionNotStarted();
    }
}
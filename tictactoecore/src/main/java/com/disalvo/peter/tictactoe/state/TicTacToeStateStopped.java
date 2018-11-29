package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.TicTacToeState;

public class TicTacToeStateStopped implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionRestartStopped();
    }

    @Override
    public TicTacToeState stop() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public TicTacToeState ensureCanPlay() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public TicTacToeState next(BoardCondition endCondition) {
        throw new GameExceptionAlreadyStopped();
    }
}

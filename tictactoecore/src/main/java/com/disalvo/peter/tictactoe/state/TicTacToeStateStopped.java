package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;

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
    public Turn nextTurn(Turn turn) {
        return turn;
    }
}

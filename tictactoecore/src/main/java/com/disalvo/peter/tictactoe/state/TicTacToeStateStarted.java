package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;

class TicTacToeStateStarted implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionAlreadyStarted();
    }

    @Override
    public TicTacToeState stop() {
        return new TicTacToeStateStopped();
    }

    @Override
    public TicTacToeState ensureCanPlay() {
        return this;
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn.next();
    }
}

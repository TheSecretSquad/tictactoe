package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.TicTacToeState;

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
    public TicTacToeState next(BoardCondition boardCondition) {
        return boardCondition.next(this);
    }
}

package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.TicTacToeState;

import static com.disalvo.peter.tictactoe.state.TicTacToeStateStopped.TicTacToeStateManualStop;

class TicTacToeStateStarted implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionAlreadyStarted();
    }

    @Override
    public TicTacToeState stop() {
        return new TicTacToeStateManualStop();
    }

    @Override
    public TicTacToeState ensureCanPlay() {
        return this;
    }

    @Override
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.continuePlay(mark, position);
        return this;
    }
}

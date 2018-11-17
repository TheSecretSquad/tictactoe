package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.*;

public abstract class TicTacToeStateStopped implements TicTacToeState {

    @Override
    public TicTacToeState start() {
        throw new GameExceptionRestartStopped();
    }

    @Override
    public TicTacToeState stop() {
        throw new GameExceptionAlreadyStopped();
    }

    @Override
    public PlayState play() { throw new GameExceptionAlreadyStopped(); }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn;
    }

    @Override
    public abstract TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position);

    static class TicTacToeStateManualStop extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            return stop();
        }
    }
}

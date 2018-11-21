package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.TicTacToeState;

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
    public TicTacToeState ensureCanPlay() {
        throw new GameExceptionAlreadyStopped();
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

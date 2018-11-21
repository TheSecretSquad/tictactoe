package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
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
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        throw new GameExceptionNotStarted();
    }
}
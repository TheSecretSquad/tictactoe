package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.*;

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
    public PlayState play() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public Turn nextTurn(Turn turn) {
        throw new GameExceptionNotStarted();
    }

    @Override
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        throw new GameExceptionNotStarted();
    }
}
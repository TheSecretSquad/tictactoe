package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;
// TODO: Revisit. Can probably throw exceptions for more of these methods as most are not used/under test
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
    public TicTacToeState won() {
        return stop();
    }

    @Override
    public TicTacToeState stalemate() {
        return stop();
    }

    @Override
    public PlayState play() {
        throw new GameExceptionNotStarted();
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn;
    }

    @Override
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        throw new GameExceptionNotStarted();
    }
}
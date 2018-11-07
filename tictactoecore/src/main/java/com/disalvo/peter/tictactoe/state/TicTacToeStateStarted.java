package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;

import static com.disalvo.peter.tictactoe.state.TicTacToeStateStopped.TicTacToeStateWon;
import static com.disalvo.peter.tictactoe.state.TicTacToeStateStopped.TicTacToeStateStalemate;
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
    public TicTacToeState won() {
        return new TicTacToeStateWon();
    }

    @Override
    public TicTacToeState stalemate() {
        return new TicTacToeStateStalemate();
    }

    @Override
    public PlayState play() {
        return new ProcessingPlayState(this);
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn.next();
    }

    @Override
    public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.continuePlay(mark, position);
        return this;
    }

    private static class ProcessingPlayState implements PlayState {

        private final TicTacToeState startingState;

        public ProcessingPlayState(TicTacToeState startingState) {
            this.startingState = startingState;
        }

        @Override
        public TicTacToeState nextState(GameEndCondition gameEndCondition) {
            return gameEndCondition.nextState(startingState);
        }
    }
}

package com.disalvo.peter.tictactoe;

import static com.disalvo.peter.tictactoe.TicTacToeStateStopped.TicTacToeStateWon;
import static com.disalvo.peter.tictactoe.TicTacToeStateStopped.TicTacToeStateStalemate;
import static com.disalvo.peter.tictactoe.TicTacToeStateStopped.TicTacToeStateManualStop;

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
        public TicTacToeState nextState(BoardCondition boardCondition) {
            return boardCondition.nextState(startingState);
        }
    }
}

package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeStateStopped.TicTacToeStateWon;
import static com.disalvo.peter.TicTacToeStateStopped.TicTacToeStateStalemate;
import static com.disalvo.peter.TicTacToeStateStopped.TicTacToeStateManualStop;

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
    public InPlayState play() {
        return new InPlayState(this);
    }

    @Override
    public Turn nextTurn(Turn turn) {
        return turn.next();
    }

    @Override
    public void announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.continuePlay(this, mark, position);
    }

    private static class InPlayState implements TicTacToeState.PlayState {

        private final TicTacToeState startingState;

        public InPlayState(TicTacToeState startingState) {
            this.startingState = startingState;
        }

        @Override
        public TicTacToeState nextState(WinningEvaluation winningEvaluation, Board board) {
            if (winningEvaluation.isWon()) {
                return new TicTacToeStateWon();
            }

            if (board.isFilled()) {
                return new TicTacToeStateStalemate();
            }

            return startingState;
        }
    }
}

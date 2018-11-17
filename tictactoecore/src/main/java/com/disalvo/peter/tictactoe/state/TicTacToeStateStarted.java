package com.disalvo.peter.tictactoe.state;

import com.disalvo.peter.tictactoe.*;
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
    public PlayState play() {
        return new Playing(this);
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

    private static class Playing implements PlayState {

        private final TicTacToeState currentState;

        public Playing(TicTacToeState currentState) {
            this.currentState = currentState;
        }

        @Override
        public TicTacToeState nextState(GameEndCondition gameEndCondition) {
            return gameEndCondition.nextState(currentState);
        }
    }
}

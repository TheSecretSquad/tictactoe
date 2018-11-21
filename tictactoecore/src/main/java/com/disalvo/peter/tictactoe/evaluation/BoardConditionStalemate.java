package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;
import com.disalvo.peter.tictactoe.state.TicTacToeStateStopped;

class BoardConditionStalemate implements GameEndCondition {
    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return new TicTacToeStateStalemate();
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }

    static class TicTacToeStateStalemate extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.stalemate(mark, position);
            return this;
        }
    }
}

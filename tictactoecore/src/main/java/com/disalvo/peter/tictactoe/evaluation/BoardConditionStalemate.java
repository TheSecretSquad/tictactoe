package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.state.TicTacToeStateStopped;

class BoardConditionStalemate implements GameEndCondition {
    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return new TicTacToeStateStalemate();
    }

    static class TicTacToeStateStalemate extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.stalemate(mark, position);
            return this;
        }
    }
}

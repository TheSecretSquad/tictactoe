package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.board.PositionCollection;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.state.TicTacToeStateStopped;

import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;

class BoardConditionWon implements GameEndCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return new TicTacToeStateWon();
    }

    static class TicTacToeStateWon extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.winningPlay(mark, position);
            return this;
        }
    }
}

package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;
import com.disalvo.peter.tictactoe.board.PositionCollection;
import com.disalvo.peter.tictactoe.state.TicTacToeStateStopped;

class BoardConditionWon implements GameEndCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return new TicTacToeStateWon();
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }

    static class TicTacToeStateWon extends TicTacToeStateStopped {

        @Override
        public TicTacToeState announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
            stateAnnouncer.winningPlay(mark, position);
            return this;
        }
    }
}

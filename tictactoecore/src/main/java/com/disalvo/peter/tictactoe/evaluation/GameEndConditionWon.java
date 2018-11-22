package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;
import com.disalvo.peter.tictactoe.board.PositionCollection;

class GameEndConditionWon implements GameEndCondition {

    private final PositionCollection positionCollection;

    public GameEndConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public TicTacToeState nextState(TicTacToeState currentState) {
        return currentState.stop();
    }

    @Override
    public GameEndCondition announceTo(StateAnnouncer stateAnnouncer, Mark mark, Position position) {
        stateAnnouncer.winningPlay(mark, position);
        return this;
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }
}

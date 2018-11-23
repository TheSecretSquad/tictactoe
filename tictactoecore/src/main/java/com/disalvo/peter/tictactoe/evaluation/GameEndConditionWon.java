package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;
import com.disalvo.peter.tictactoe.board.PositionCollection;

class GameEndConditionWon implements GameEndCondition {

    private final PositionCollection positionCollection;

    public GameEndConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public GameEndCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.winningPlay();
        return this;
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }

    @Override
    public Turn next(Turn turn) {
        return turn;
    }

    @Override
    public TicTacToeState next(TicTacToeState state) {
        return state.stop();
    }
}

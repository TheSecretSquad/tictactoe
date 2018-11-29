package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

class BoardConditionWon implements BoardCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public BoardCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.winningPlay();
        return this;
    }

    @Override
    public BoardCondition printOn(BoardConditionMedia boardConditionMedia) {
        return null;
    }

    @Override
    public TicTacToeState next(TicTacToeState ticTacToeState) {
        return ticTacToeState.stop();
    }

    @Override
    public Turn next(Turn turn) {
        return turn;
    }
}

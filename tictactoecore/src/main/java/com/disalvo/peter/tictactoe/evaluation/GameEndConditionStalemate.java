package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

class GameEndConditionStalemate implements GameEndCondition {

    @Override
    public GameEndCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.stalemate();
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

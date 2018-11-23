package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

public class GameEndConditionNone implements GameEndCondition {

    @Override
    public GameEndCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.continuePlay();
        return this;
    }

    @Override
    public GameEndCondition printOn(GameEndConditionMedia gameEndConditionMedia) {
        return null;
    }

    @Override
    public Turn next(Turn turn) {
        return turn.next();
    }

    @Override
    public TicTacToeState next(TicTacToeState state) {
        return state;
    }
}

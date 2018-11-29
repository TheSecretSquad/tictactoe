package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardConditionMedia;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;

public class BoardConditionNone implements BoardCondition {

    @Override
    public BoardCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.continuePlay();
        return this;
    }

    @Override
    public BoardCondition printOn(BoardConditionMedia boardConditionMedia) {
        return null;
    }

    @Override
    public TicTacToeState next(TicTacToeState ticTacToeState) {
        return ticTacToeState;
    }

    @Override
    public Turn next(Turn turn) {
        return turn.next();
    }
}

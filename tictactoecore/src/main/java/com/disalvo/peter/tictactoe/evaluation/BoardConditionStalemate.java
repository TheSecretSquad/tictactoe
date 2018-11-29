package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardConditionMedia;
import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.Turn;

class BoardConditionStalemate implements BoardCondition {

    @Override
    public BoardCondition announceTo(ConditionAnnouncer conditionAnnouncer) {
        conditionAnnouncer.stalemate();
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

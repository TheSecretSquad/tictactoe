package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardConditionMedia;
import com.disalvo.peter.tictactoe.PositionCollection;

class BoardConditionWon implements BoardCondition {

    private final PositionCollection positionCollection;

    public BoardConditionWon(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public BoardCondition resolve(BoardConditionListener boardConditionListener) {
        boardConditionListener.winningPlay();
        return this;
    }

    @Override
    public BoardCondition printOn(BoardConditionMedia boardConditionMedia) {
        return null;
    }
}

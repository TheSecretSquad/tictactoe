package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardConditionMedia;

class BoardConditionStalemate implements BoardCondition {

    @Override
    public BoardCondition resolve(BoardConditionListener boardConditionListener) {
        boardConditionListener.stalemate();
        return this;
    }

    @Override
    public BoardCondition printOn(BoardConditionMedia boardConditionMedia) {
        return null;
    }
}
